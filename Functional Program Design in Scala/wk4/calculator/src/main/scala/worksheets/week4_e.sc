import calculator._

object test{
  val List (a,b,c) = List(new Var(1.0), new Var(1.0), new Var(1.0))
  val delta = Polynomial.computeDelta(a, b, c)

  Polynomial.computeSolutions(a,b,c,delta)().toString

  val m = Map(1 -> 'a', 2 -> 'b', 3 -> 'c')

  m.contains(3)

  val s = Signal( a() + b() + c() )

  s()
}