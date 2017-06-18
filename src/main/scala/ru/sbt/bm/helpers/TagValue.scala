package ru.sbt.bm.helpers

case class TagValue(tag: String, value: String) {
  def getTag: String = tag

  def getValue: String = value
}

