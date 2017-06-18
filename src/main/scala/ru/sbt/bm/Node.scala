package WorkingTreeParser

import com.typesafe.scalalogging.StrictLogging
import ru.sbt.bm.helpers.CountRootTagsException

import scala.collection.mutable.ListBuffer
import scala.reflect.macros.ParseException

class Node extends StrictLogging {

  def parser(parseFile: String) = {

    val myStructure = parseFile.split("\n")
    val listChildren = new ListBuffer[String]()
    var rootTag: String = ""
    val parserList = new ParserList()

    val listRootTags = myStructure.flatMap({ elem =>
      if (elem.split("|").head.matches("\\w+")) Some(elem)
      else None
    })

    listRootTags.length match {
      case 1 =>
        val clearArray = myStructure.filter(elem => elem != "\r" && elem != " ")
        iterator(clearArray)
      case _ => throw new CountRootTagsException(s"Root tag should be one", None)
    }

    def iterator(str: Array[String]): Unit = {
      if (str.head.split("|").head.matches("\\w+")) {
        rootTag = str.head
        iterator(str.tail)
      }
      else if (numOfTabulation(str.head) == numOfTabulation(str(1))) {
        if (str.tail.length != 1) {
          listChildren += str.head
          iterator(str.tail)
        }
        else {
          if (str.head == rootTag) {
            parserList.addNext(rootTag)
            parserList.addNext(str(1))
          }
          else {
            listChildren += str.head
            listChildren += str(1)
            parserList.addNext(rootTag, listChildren.toList)
          }
        }
      }
      else if (numOfTabulation(str.head) < numOfTabulation(str(1))) {
        //        listChildren += str.head
        if (rootTag.nonEmpty) parserList.addNext(rootTag, listChildren.toList)
        rootTag = str.head
        listChildren.clear()
        if (str.tail.length != 1) iterator(str.tail)
        else {
          parserList.addNext(rootTag = str.head)
          parserList.addNext(rootTag = str(1))
        }

      }
      else if (numOfTabulation(str.head) > numOfTabulation(str(1))) {
        listChildren += str.head
        parserList.addNext(rootTag, listChildren.toList)
        listChildren.clear()
        rootTag = str(1)
        iterator(str.tail)
      }
    }

    parserList
  }

  private def numOfTabulation(strFind: String): Int = {
    strFind.split("|").count(_.equals("\t"))
  }
}