import scala.io.Source


object test {

  //
  // Putting it all together
  //

  val in = Source.fromURL("http://lamp.epfl.ch/files/content/sites/lamp/files/teaching/progfun/linuxwords.txt")

  val words = in.getLines.toList filter (_.forall(_.isLetter))

  val mnem = Map(
    '2'->"ABC", '3'->"DEF", '4'->"GHI", '5'->"JKL",
    '6'->"MNO", '7'->"PQRS", '8'->"TUV", '9'->"WXYZ")

  val charCode: Map[Char, Char] =
    for{
      (digit, letters) <- mnem
      ch <- letters
    } yield ch -> digit

  def wordCode(word: String): String =
    word.toUpperCase map charCode

  wordCode("Java")

  val wordsFromNum: Map[String, Seq[String]] =
    words groupBy wordCode withDefaultValue Seq()

  def encode(number: String): Set[List[String]] =
    if(number.isEmpty) Set(List())
    else
      (for {
        split <- 1 to number.length
        word <- wordsFromNum(number take split)
        rest <- encode(number drop split)
      } yield word::rest).toSet

  encode("7225247386")

  def translate(number: String): Set[String] =
    encode(number) map (_ mkString " ")

  translate("7225247386")
}

