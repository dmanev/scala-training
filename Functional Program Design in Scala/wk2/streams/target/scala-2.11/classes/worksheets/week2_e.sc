object test{
  val v = Vector(Vector('a','b'), Vector('c','d'), Vector('e','f'))

  v.indexWhere(l => l.indexWhere(ch => ch == 'c') >= 0)
}