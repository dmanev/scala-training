package quickcheck

import org.scalacheck.Arbitrary._
import org.scalacheck.Gen._
import org.scalacheck.Prop._
import org.scalacheck._

abstract class QuickCheckHeap extends Properties("Heap") with IntHeap {

  lazy val genHeap: Gen[H] = for {
    a <- arbitrary[A]
    h <- oneOf(const(empty), genHeap)
  } yield insert(a, h)

  implicit lazy val arbHeap: Arbitrary[H] = Arbitrary(genHeap)

  def toStream(hp: H): Stream[A] =
    if (isEmpty(hp)) Stream()
    else {
      val a = findMin(hp)
      a #:: toStream(deleteMin(hp))
    }

  property("gen1") = forAll { (h: H) =>
    val m = if (isEmpty(h)) 0 else findMin(h)
    findMin(insert(m, h)) == m
  }

  property("min1") = forAll { a: Int =>
    val m = insert(a, empty)
    findMin(m) == a
  }

  property("min2 - hint1") = forAll { (a: Int, b: Int) =>
    val minEl = math.min(a, b)
    val m = insert(b, insert(a, empty))
    findMin(m) == minEl
  }

  property("del1 - hint2") = forAll { a: Int =>
    val m = insert(a, empty)
    deleteMin(m) == empty
  }

  property("sorted1 - hint3") = forAll { h: H =>
    val sh = toStream(h)
    sh == sh.sorted
  }

  property("meld1 - hint4") = forAll { (h1: H, h2: H) =>
    findMin(meld(h1, h2)) == math.min(findMin(h1), findMin(h2))
  }

  // Take two arbitrary heaps, meld together.
  // Then remove min from 1 and insert into 2, meld the results.
  // Compare two melds by comparing sequences of ranks.
  property("meld2") = forAll { (h1: H, h2: H) =>
    val meld1 = meld(h1, h2)
    val m1 = findMin(h1)
    val h12 = deleteMin(h1)
    val h22 = insert(m1, h2)
    val meld2 = meld(h12, h22)

    toStream(meld1) == toStream(meld2)
  }
}
