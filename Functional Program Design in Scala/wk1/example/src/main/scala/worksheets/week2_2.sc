object test {
  val xs = Stream.cons(1, Stream.cons(2, Stream.Empty))
  val ys = Stream(1,2,3)
  (1 to 1000).toStream

  def streamRange(lo: Int, hi: Int): Stream[Int] = {
    print(lo+" ")
    if (lo > hi) Stream.empty
    else
      Stream.cons(lo, streamRange(lo+1, hi))
  }

  streamRange(1, 10).take(3).toList
}