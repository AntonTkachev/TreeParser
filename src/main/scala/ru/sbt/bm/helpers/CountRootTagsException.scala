package ru.sbt.bm.helpers

class EventPushException(message: String, cause: Option[Throwable]) extends Exception(message) {
  cause.foreach(initCause)
}
