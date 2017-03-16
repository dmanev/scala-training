import forcomp.Anagrams._

object test {

  val lst = List(('a', 3), ('b', 3))

  def genAll(ocs: Occurrences): Occurrences =
    for {
      (ch, cnt) <- ocs
      i <- 1 to cnt
    } yield (ch, i)

  def getAllSlices(occurrences: Occurrences): List[(Occurrences, Occurrences)] =
    (for {
      split <- 0 to occurrences.length
    } yield occurrences.splitAt(split)).toList

  lst.toSet.subsets().toList map (_.toList)

  combinations(lst)

  def subtract(x: Occurrences, y: Occurrences): Occurrences = {
    def subFreq(oc: Map[Char, Int], other: (Char, Int)): Map[Char,Int] = {
      val (ch, freq) = other
      oc + (ch -> (oc(ch) - freq))
    }
    ((y.toMap foldLeft x.toMap)(subFreq)).toList
  }

  subtract(lst, List(('b', 1)))

  def occurrencesToWord(occurrences: Occurrences): Any =
    (for{
      (ch, freq) <- occurrences
      i <- 1 to freq
    } yield ch).mkString

  occurrencesToWord(List(('l',1),('i',1),('e',1)))

  List("abc","dca").mkString

  val l14 = List(1,2,3,4)
  (for{
    (x, i) <- l14.view.zipWithIndex

  } yield (l14 drop i+1)).mkString(" ")

}
