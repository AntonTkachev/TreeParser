package ru.sbt.bm.helpers

import WorkingTreeParser.ParserList

import scala.compat.Platform

trait ParserHelper {

  implicit class rich(list: ParserList) {

    def listToString: String = {
      var firstElem = list.head
      val str = new StringBuilder
      while (firstElem != null) {
        str.append(firstElem.rootTag + Platform.EOL)
        firstElem.children.foreach(t => str.append(t + Platform.EOL))
        if (firstElem.next != null) str.append(Platform.EOL)
        firstElem = firstElem.next
      }
      str.toString()
    }

    def returnRootTag: String = {
      list.head.rootTag
    }

    def getChildren(rootTag: String = ""): List[String] = {
      var res: List[String] = List.empty[String]
      var firstElem = list.head
      if (rootTag.isEmpty) res = firstElem.children
      else {
        while (firstElem != null) {
          if (firstElem.rootTag.replaceAll("\\W", "") == rootTag) res = firstElem.children
          firstElem = firstElem.next
        }
      }
      if (res.isEmpty) throw new IllegalArgumentException
      else res.map(_.replaceAll("\t", ""))
    }

    def hasNext: Boolean = {
      list.head.rootTag.nonEmpty
    }

  }

}
