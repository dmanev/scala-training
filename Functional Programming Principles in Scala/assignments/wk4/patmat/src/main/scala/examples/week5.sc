object week5 {
   def mapFun[T, U](xs: List[T], f: T => U): List[U] =
  //(xs foldRight List[U]())(f(_) :: _)
    (xs foldRight List[U]())((A, B) => f(A) :: B)

  mapFun[Int, Int](List(1,2,3,4), (x => x % 2))

  def lengthFun[T](xs: List[T]): Int =
    (xs foldRight 0)( (_, sum) => sum + 1 )

  lengthFun(List(1,2,3,4,5))

  List(1,2,3,4) reduceLeft (_ + _)
  (List(1,2,3,4) foldLeft 0)(_ + _)
  (List(1,2,3,4) foldLeft 1)(_ * _)


  def concat[T](xs: List[T], ys: List[T]): List[T] =
    // (xs foldLeft ys) (_ :: _) - NOK: (B, A)=>(B)
    // i.e. ys:List[T] does not have :: operation
    (xs foldRight ys) (_ :: _)


}