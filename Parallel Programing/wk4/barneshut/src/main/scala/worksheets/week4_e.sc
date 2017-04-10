object test{
  val lst = List(4,3,2,1,3,4,5)

  lst.zipWithIndex.min._2

  val l2 = lst

  l2.updated(2, 0)

  l2

  lst
}