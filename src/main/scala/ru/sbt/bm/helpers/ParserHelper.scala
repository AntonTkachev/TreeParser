package ru.sbt.bm.helpers

import ru.sbt.bm.ParserList

import scala.collection.mutable.ListBuffer
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
      list.head.rootTag.trim
    }

    def getAllChildren: List[TagValue] = {
      var res = ListBuffer.empty[List[TagValue]]
      var head = list.head
      while (head.hasNext) {
        res += head.children.map(_.split("=") match {
          case Array(k, v) => TagValue(k.trim, v.trim)
          case Array(k) => TagValue(k.trim, "")
        })
        head = head.next
      }
      res.flatten.toList
    }

    def getRootTagChildren: List[TagValue] = {
      try {
        list.head.children.map(_.split("=") match {
          case Array(k, v) => TagValue(k.trim, v.trim)
          case Array(k) => TagValue(k.trim, "")
        })
      }
      catch {
        case ex: Throwable => throw new IllegalArgumentException(ex.getMessage)
      }
    }

    def getTagChildren(rootTag: String): List[TagValue] = {
      var res = ListBuffer.empty[List[TagValue]]
      var firstElem = list.head
      try {
        while (firstElem.hasNext) {
          if (firstElem.rootTag.trim == rootTag) {
            res += firstElem.children.map(_.split("=") match {
              case Array(k, v) => TagValue(k.trim, v.trim)
              case Array(k) => TagValue(k.trim, "")
            })
          }
          firstElem = firstElem.next
        }
        res.flatten.toList
      }
      catch {
        case ex: Throwable => throw new IllegalArgumentException(ex.getMessage)
      }
    }

  }

}
