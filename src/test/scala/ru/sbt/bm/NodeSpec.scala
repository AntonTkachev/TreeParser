package ru.sbt.bm

import org.scalatest.{FlatSpec, Matchers}
import ru.sbt.bm.helpers.{CountRootTagsException, ParserHelper, TagValue, ValueHelper}

class NodeSpec extends FlatSpec with Matchers with ParserHelper with ValueHelper {

  val newNode = new Node()

  def parser(str: String) = newNode.parser(str)

  behavior of "Tree Parser"

  it should "return exception" in {
    a[CountRootTagsException] shouldBe thrownBy(parser(exampleWithManyRootTag))
  }

  it should "return string " in {
    val result = parser(exampleWithOneRootTag)
    result.listToString.replaceAll("\\s", "") shouldEqual exampleWithOneRootTag.replaceAll("\\s", "")
  }

  it should "return all children" in {
    val result = parser(exampleWithOneRootTag)
    result.getAllChildren shouldEqual List(TagValue("first", "52"), TagValue("first1", "11"), TagValue("first2", ""), TagValue("second", "1"), TagValue("second1", ""))
  }

  it should "return children of rootTag" in {
    val result = parser(exampleWithOneRootTag)
    result.getRootTagChildren shouldEqual List(TagValue("first", "52"), TagValue("first1", "11"), TagValue("first2", ""))
  }

  it should "return children of tag TAG2" in {
    val result = parser(exampleWithOneRootTag)
    result.getTagChildren("TAG2") shouldEqual List(TagValue("second", "1"), TagValue("second1", ""))
  }

  it should "return root tag" in {
    val result = parser(exampleWithOneRootTag)
    result.returnRootTag shouldEqual "TAG"
  }

  it should "save structure????" in {
    val result = parser(exampleStartWithNonRootTag)
    result
  }

  it should "return two last root tag without children" in {
    val result = parser(exampleEndTwoTag)
    result
  }
}