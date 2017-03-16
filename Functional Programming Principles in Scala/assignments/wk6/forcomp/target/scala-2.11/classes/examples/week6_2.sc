
object test {

  //
  // Combinational search and for-expression
  //
  def scalarProduct(xs: List[Double], ys: List[Double]): Double =
//    (xs zip ys).map{case (x,y) => x * y}.sum
    (for{
      (x,y) <- (xs zip ys)
    } yield (x*y)).sum

  def isPrime(n: Int): Boolean =
    2 until n forall (p => n % p != 0)

  1 to 10 map (p => isPrime(p))

  def ijFun(n: Int): List[(Int, Int)] =
//      ((1 until n) flatMap (i => (1 until i) map (j => (i, j)))).filter{
//        case (k,l) => isPrime(k+l)}.toList
   for{
      i <- List.range(1,n)
      j <- List.range(1,i)
      if isPrime(i+j)
    } yield (i, j)

  ijFun(5)
}