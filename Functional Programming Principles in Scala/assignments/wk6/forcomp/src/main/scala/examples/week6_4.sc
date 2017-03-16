
object test {

  //
  // Maps
  //
  var romanNumerals = Map("I" -> 1, "V" -> 5, "X" -> 10)
  var capitalOfCountry = Map("US" -> "Washington", "Switzerland" -> "Bern")

  capitalOfCountry("US")
  romanNumerals("I")

  capitalOfCountry get "andora"
  capitalOfCountry get "US"

  var fruit = List("apple", "pear", "orange", "pineapple")

  fruit sortWith(_.length < _.length)
  fruit.sorted

  fruit.groupBy(_.head)

  class Poly(var terms0: Map[Int, Double]) {
    def this(bindings: (Int, Double)*) = this(bindings.toMap)
    val terms = terms0 withDefaultValue(0.0)
//    def + (other: Poly) = new Poly(terms ++ (other.terms map adjust))
    def adjust(term: (Int, Double)): (Int, Double) = {
      val (exp, coeff) = term
      exp -> (coeff + terms(exp))
    }
    def + (other: Poly) = new Poly((other.terms foldLeft terms)(addTerm))
    def addTerm(terms: Map[Int, Double], term: (Int, Double)): Map[Int, Double] = {
      val (exp, coeff) = term
      terms + (exp -> (coeff + terms(exp)))
    }
    override def toString: String =
      (for((exp, coeff) <- terms.toList.sorted.reverse)
        yield coeff+"x^"+exp) mkString "+"
  }

  val p1 = new Poly(1->2.0, 3->4.0, 5->6.2)
  val p2 = new Poly(0->3.0, 3->7.0)

  p1+p2
  p1.terms(2)
}

