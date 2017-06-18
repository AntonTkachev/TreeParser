package ru.sbt.bm.helpers

class CountRootTagsException(message: String, cause: Option[Throwable]) extends Exception(message) {
  cause.foreach(initCause)
}
