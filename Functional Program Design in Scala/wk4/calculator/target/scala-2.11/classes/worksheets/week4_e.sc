import calculator._

object test{
  val List (a,b,c) = List(new Var(1.0), new Var(1.0), new Var(1.0))
  val delta = Polynomial.computeDelta(a, b, c)

  Polynomial.computeSolutions(a,b,c,delta)().toString
}