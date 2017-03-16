import java.util.NoSuchElementException

abstract class IntSet {
  def incl(x: Int): IntSet
  def contains(x: Int): Boolean
  def union(other: IntSet): IntSet
}

object Empty extends IntSet {
  def contains(x: Int): Boolean = false
  def incl(x: Int): IntSet = new NonEmpty(x, Empty, Empty)
  override def toString = "."
  def union(other: IntSet): IntSet = other
}

class NonEmpty(elem: Int, left: IntSet, right: IntSet) extends IntSet {

  def contains(x: Int): Boolean =
    if (x < elem) left contains x
    else if (x > elem) right contains x
    else true

  def incl(x: Int): IntSet =
    if (x < elem) new NonEmpty(elem, left incl x, right)
    else if (x > elem) new NonEmpty(elem, left, right incl x)
    else this

  override def toString = "{" + left + elem + right + "}"

  def union(other: IntSet): IntSet =
    ((left union right) union other) incl elem
}

def error(msg: String) = throw new Error(msg)


trait List[T] {
  def isEmpty: Boolean
  def head: T
  def tail: List[T]
  def nth(index: Int): T
}

class Cons[T](val head:T, val tail:List[T]) extends List[T] {
  def isEmpty = false
  def nth(index: Int): T =
    if(isEmpty) throw new IndexOutOfBoundsException("index is out of bounds")
    else if(index == 0) head
    else
      tail.nth(index - 1)
}

class Nil[T] extends List[T] {
  def isEmpty = true
  def head: Nothing = throw new NoSuchElementException("Nil.head")
  def tail: Nothing = throw new NoSuchElementException("Nil.tail")
  def nth(index: Int): Nothing = throw new NoSuchElementException("Nil.nth")
}

println("intset worksheet")
val t1 = new NonEmpty(3, Empty, Empty)
val t2 = t1 incl 4
if (true) 1 else false

val l1 = new Cons(4, new Cons(2, new Cons(1, new Nil)))

l1 tail

l1 nth 0