package WorkingTreeParser

class ParseNode {
  var rootTag: String = _

  var children: List[String] = _

  var next: ParseNode = _

  def hasNext: Boolean = {
    next != null
  }
}
