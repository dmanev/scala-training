package patmat

import org.scalatest.FunSuite

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

import patmat.Huffman._

@RunWith(classOf[JUnitRunner])
class HuffmanSuite extends FunSuite {
	trait TestTrees {
		val t1 = Fork(Leaf('a',2), Leaf('b',3), List('a','b'), 5)
		val t2 = Fork(Fork(Leaf('a',2), Leaf('b',3), List('a','b'), 5), Leaf('d',4), List('a','b','d'), 9)
	}


  test("weight of a larger tree") {
    new TestTrees {
      assert(weight(t1) === 5)
      assert(weight(t2) === 9)
    }
  }


  test("chars of a larger tree") {
    new TestTrees {
      assert(chars(t1) === List('a','b'))
      assert(chars(t2) === List('a','b','d'))
    }
  }


  test("string2chars(\"hello, world\")") {
    assert(string2Chars("hello, world") === List('h', 'e', 'l', 'l', 'o', ',', ' ', 'w', 'o', 'r', 'l', 'd'))
  }

  test("times() testing") {
    assert(times(string2Chars("abc")).zip(List(('a', 1),('b',1),('c',1))).forall {case (x, y) => x == y } )
    assert(times(string2Chars("abca")) === List(('a', 2),('b',1),('c',1)))
    assert(times(string2Chars("abcab")) === List(('a', 2),('b',2),('c',1)))
    assert(times(string2Chars("abcabf")) === List(('a', 2),('b',2),('c',1),('f',1)))
  }

  test("makeOrderedLeafList for some frequency table") {
    assert(makeOrderedLeafList(List(('t', 2), ('e', 1), ('x', 3))) === List(Leaf('e',1), Leaf('t',2), Leaf('x',3)))
    assert(makeOrderedLeafList(List(('a', 4), ('b', 2), ('c', 5))) === List(Leaf('b', 2), Leaf('a', 4), Leaf('c', 5)))
  }

  test("singleton() testing") {
    val leaflist = List(Leaf('e', 1), Leaf('t', 2), Leaf('x', 4))
    assert(singleton(leaflist) === false)
    val l1 = List(Fork(Leaf('e',2),Leaf('t',3),List('e','t'),5))
    assert(singleton(l1))
    // This one is arguable
    assert(singleton(List()) === false)
  }

  test("combine of some leaf list") {
    val leaflist = List(Leaf('e', 1), Leaf('t', 2), Leaf('x', 4))
    assert(combine(leaflist) === List(Fork(Leaf('e',1),Leaf('t',2),List('e', 't'),3), Leaf('x',4)))
    val ll2 = List(Leaf('e', 2), Leaf('t', 3), Leaf('x', 4), Leaf('c', 7))
    assert(combine(ll2) === List(Leaf('x', 4),Fork(Leaf('e',2),Leaf('t',3),List('e','t'),5),Leaf('c', 7)))
    val ll3 = List(Leaf('e', 2), Leaf('t', 3), Leaf('x', 4))
    assert(combine(ll3) === List(Leaf('x', 4),Fork(Leaf('e',2),Leaf('t',3),List('e','t'),5)))
  }

  test("until()() testing") {
    val leaflist = List(Leaf('e', 1), Leaf('t', 2), Leaf('x', 4))
    assert(singleton(until(singleton, combine)(leaflist)))
  }

  test("decode() testing") {
    val l1 = List('a','b')
    val c1 = createCodeTree(l1)
    assert(decode(createCodeTree(l1), List(0,1)) === l1)
    val l2 = List('a','a', 'b')
    assert(decode(createCodeTree(l2), List(1,1,0)) === l2)
  }

  test("decode and encode a very short text should be identity") {
    new TestTrees {
      assert(decode(t1, encode(t1)("ab".toList)) === "ab".toList)
    }
  }

  test("codeBits()() testing") {
    val ct: CodeTable = List(('a', List(1,0,1,1)),('b', List(0,1,0,0)))
    assert(codeBits(ct)('a') === List(1,0,1,1))
    assert(codeBits(ct)('b') === List(0,1,0,0))
    assert(codeBits(ct)('c') === List())
    val ct1: CodeTable = List(('c', List(1,0,1,1)))
    assert(codeBits(ct1)('c') === List(1,0,1,1))
    assert(codeBits(ct1)('a') === List())
  }

  test("convert() testing") {
    val l1 = List('a', 'b')
    assert(convert(createCodeTree(l1)) === List(('a',List(0)), ('b',List(1))))
    val l2 = List('a', 'a', 'b')
    assert(convert(createCodeTree(l2)) === List(('b',List(0)), ('a',List(1))))
    val l3 = List('a', 'b', 'c')
    assert(convert(createCodeTree(l3)) === List(('c',List(0)), ('a',List(1, 0)), ('b',List(1, 1))))
  }

  test("quickEncode() testing") {
    val l1 = List('a', 'b')
    val t1 = createCodeTree(l1)
    assert(decode(t1, quickEncode(t1)("ab".toList)) === "ab".toList)
    val l2 = List('a', 'a', 'b')
    val t2 = createCodeTree(l2)
    assert(decode(t2, quickEncode(t2)("ab".toList)) === "ab".toList)
    val l3 = List('a', 'a', 'b', 'c')
    val t3 = createCodeTree(l3)
    assert(decode(t3, quickEncode(t3)("abc".toList)) === "abc".toList)
    // Cross-check with encode()
    assert(decode(frenchCode, quickEncode(frenchCode)(decodedSecret)) === decodedSecret)
  }
}
