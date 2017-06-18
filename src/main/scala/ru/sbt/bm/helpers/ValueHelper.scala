package ru.sbt.bm.helpers

trait ValueHelper {

  val exampleWithManyRootTag: String =
    """TAG
      |	first =52
      |	first1 =11
      |	first2
      |
      |TAG1
      |	second
      |	second1
      |
      |	TAG2
      |		fourth =1
      |		third1
      |
      |			TAG3
      |				fourth =11
      |				fourth1 =213
      |				fourth2
      |				fourth3
      |
      |TAG4
      |	fifth
      |	fifth1
      | """.stripMargin

  val exampleWithOneRootTag: String =
    """TAG
      |	first =52
      |	first1 =11
      |	first2
      |
      |	TAG2
      |		second =1
      |		second1
      |
      |		TAG4
      |			fourth =11
      |			fourth1 =213
      |			fourth2
      |			fourth3
      |
      |""".stripMargin

  val exampleStartWithNonRootTag: String =
    """	TAG2
      |		second =1
      |		second1
      |
      |TAG
      |	first =52
      |	first1 =11
      |	first2
      |
      |		TAG4
      |			fourth =11
      |			fourth1 =213
      |			fourth2
      |			fourth3
      | """.stripMargin

  val exampleEndTwoTag: String = //TODO incorrectly example???
    """TAG
      |	first =52
      |	first1 =11
      |	first2
      |
      |	TAG2
      |		second =1
      |		second1
      |
      |		TAG4
      |			fourth =11
      |			fourth1 =213
      |			fourth2
      |			fourth3
      |
      |		TAG4.1
      |
      |		TAG4.2
      | """.stripMargin

  val exampleLessEnd: String = //TODO spec with this
    """TAG
      |	first =52
      |	first1 =11
      |	first2
      |
      |	TAG2
      |		second =1
      |		second1
      |
      |		TAG4
      |			fourth =11
      |			fourth1 =213
      |			fourth2
      |			fourth3
      |
      |		TAG4.1
      |			fifth
      |		TAG4.2
      | """.stripMargin

}
