import patmat.Huffman._

//package worksheet

//trait Function1[-T, +U]{
//  def apply(x: T): U
//}

//trait List[+T]{
//  def isEmpty: Boolean
//  def head: T
//  def tail: List[T]
//  def prepend [U >: T] (elem: U): List[U] = new Cons(elem, this)
//}
//
//class Cons[T](val head: T, val tail: List[T]) extends List[T] {
//  def isEmpty: Boolean = false
//}
//
//object Nil extends List[Nothing] {
//  def isEmpty = true
//  def head: Nothing = throw new NoSuchElementException("Nil.head")
//  def tail: Nothing = throw new NoSuchElementException("Nil.tail")
//}

//trait Expr {
//  def isNumber: Boolean
//  def isSum: Boolean
//  def numValue: Int
//  def leftOp: Expr
//  def rightOp: Expr
//  def eval(e: Expr):  Int = {
//    if(e.isNumber) e.numValue
//    else if (e.isSum) eval(e.e1) + eval(e.e2)
//    else throw new Error("Unknown expression")
//  }
//}
//
//class Number(n: Int) extends Expr {
//  def isNumber: Boolean = true
//  def isSum: Boolean = false
//  def numValue: Int = n
//  def leftOp: Expr = throw new Error("Number.leftOp")
//  def rightOp: Expr = throw new Error("Number.rightOp")
//}
//
//class Sum(e1: Expr, e2: Expr) extends Expr {
//  def isNumber: Boolean = false
//  def isSum: Boolean = true
//  def numValue: Int = throw new Error("Sum.numValue")
//  def leftOp: Expr = e1
//  def rightOp: Expr = e2
//}

object test {
  val x: List[String] = Nil

  def insert(x: Int, xs: List[Int]): List[Int] = xs match {
    case List() => x::List()
    case y :: ys =>
      if (y > x) y :: insert(x, ys)
      else x :: xs
  }

  def isort(xs: List[Int]): List[Int] = xs match {
    case List() => List()
    case y :: ys => {
      insert(y, isort(ys))
    }
  }


  val uns = List(8,3,4,6,2,1,5,7)
  val srt = isort(uns)

  println(uns)
  println(srt)


  val lst = List(('a', 3), ('b', 1), ('c', 4))
  lst.sortBy(i => i._2)


  val l1 = List(Fork(Leaf('e',1),Leaf('t',2),List('e', 't'),3), Leaf('x',4), Leaf('y', 6))

  val frk = l1.head
  val (first, last) = l1.partition (p => p match {
    case Fork(left, right, chList, chWeight) => chWeight < 4
    case Leaf(ch, chWeight) => chWeight < 4
  })
  val ct: CodeTree = createCodeTree(List('a','a','b'))

  val ctbl = List(('a', List(1,0,1)),('b', List(1,1,1)))

  ctbl map (a => (a._1, 0::a._2))

  decodedSecret
}
