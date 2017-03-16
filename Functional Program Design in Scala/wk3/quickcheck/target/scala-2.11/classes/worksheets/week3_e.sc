import quickcheck._

object test{

  class testHeap extends BinomialHeap with IntHeap {
    def toStream(hp: H): Stream[A] =
      if (isEmpty(hp)) Stream()
      else {
        val a = findMin(hp)
        a #:: toStream(deleteMin(hp))
      }

    def isStreamSorted(s: Stream[A]): Boolean = s match {
      case Stream() => true
      case x #:: Stream() => true
      case x #:: ys => x < ys.head && isStreamSorted(ys)
    }

    def runTest(): Any = {
      val h = insert(5, insert(2, insert(4, empty)))

      toStream(insert(3, h)).take(5).toList

      isStreamSorted(toStream(h))

      h
    }
  }

  val h = new testHeap

  h.runTest()
}