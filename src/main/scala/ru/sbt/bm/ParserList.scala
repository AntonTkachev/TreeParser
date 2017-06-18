package WorkingTreeParser

class ParserList {
  var head: ParseNode = _
  var tail: ParseNode = _

  def addNext(rootTag: String, children: List[String] = List()) = {
    val tmp = new ParseNode()
    tmp.rootTag = rootTag
    if (children.nonEmpty) tmp.children = children
    if (head == null) {
      head = tmp
      tail = tmp
    }
    else {
      tail.next = tmp
      tail = tmp
    }
  }

  //TODO write
  def addFirstTag = ???

}
