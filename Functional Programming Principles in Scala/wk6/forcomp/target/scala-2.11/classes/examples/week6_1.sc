
object test {

  //
  // Other collections
  //
  val xs = Array(1,2,3,44)

  println(xs)

  xs map (x => x*2)

  println(xs)

  val s = "Hello World"

  s filter (c => c.isUpper)


  val r: Range = 1 until 5
  val ss: Range = 1 to 5
  val rr: Range = 1 to 10 by 3
  val rs: Range = 6 to 2 by -2

  s exists (c => c.isUpper)
  s forall (c => c.isUpper)

  val pairs = List(1,2,3) zip s

  pairs.unzip

  s flatMap (c => List('.',c))
}