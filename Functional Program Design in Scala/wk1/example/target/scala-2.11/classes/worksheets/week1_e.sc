object test{
  val lst = List(-3,-6,-5)
  lst.foldLeft(0)((r,c)=> r+c)
  lst.foldLeft(lst.head)((r,c)=> math.max(r, c))
}