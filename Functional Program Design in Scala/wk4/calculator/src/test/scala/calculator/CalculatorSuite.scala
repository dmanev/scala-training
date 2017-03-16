package calculator

import org.scalatest.FunSuite

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

import org.scalatest._

import TweetLength.MaxTweetLength

@RunWith(classOf[JUnitRunner])
class CalculatorSuite extends FunSuite with ShouldMatchers {

  /******************
   ** TWEET LENGTH **
   ******************/

  def tweetLength(text: String): Int =
    text.codePointCount(0, text.length)

  test("tweetRemainingCharsCount with a constant signal") {
    val result = TweetLength.tweetRemainingCharsCount(Var("hello world"))
    assert(result() == MaxTweetLength - tweetLength("hello world"))

    val tooLong = "foo" * 200
    val result2 = TweetLength.tweetRemainingCharsCount(Var(tooLong))
    assert(result2() == MaxTweetLength - tweetLength(tooLong))
  }

  test("tweetRemainingCharsCount with a supplementary char") {
    val result = TweetLength.tweetRemainingCharsCount(Var("foo blabla \uD83D\uDCA9 bar"))
    assert(result() == MaxTweetLength - tweetLength("foo blabla \uD83D\uDCA9 bar"))
  }


  test("colorForRemainingCharsCount with a constant signal") {
    val resultGreen1 = TweetLength.colorForRemainingCharsCount(Var(52))
    assert(resultGreen1() == "green")
    val resultGreen2 = TweetLength.colorForRemainingCharsCount(Var(15))
    assert(resultGreen2() == "green")

    val resultOrange1 = TweetLength.colorForRemainingCharsCount(Var(12))
    assert(resultOrange1() == "orange")
    val resultOrange2 = TweetLength.colorForRemainingCharsCount(Var(0))
    assert(resultOrange2() == "orange")

    val resultRed1 = TweetLength.colorForRemainingCharsCount(Var(-1))
    assert(resultRed1() == "red")
    val resultRed2 = TweetLength.colorForRemainingCharsCount(Var(-5))
    assert(resultRed2() == "red")
  }

  test("computeDelta() testing") {
    assert(Polynomial.computeDelta(Var(1), Var(1), Var(1))() === -3)
    assert(Polynomial.computeDelta(Var(1), Var(0), Var(-1))() === 4)
    assert(Polynomial.computeDelta(Var(5), Var(0), Var(0))() === 0)
  }

  test("computeSolutions() delta > 0 testing ") {
    val List (a,b,c) = List(new Var(1.0), new Var(0.0), new Var(-1.0))
    val delta = Polynomial.computeDelta(a, b, c)
    assert(Polynomial.computeSolutions(a,b,c,delta)() === Set(-1.0, 1.0))
  }

  test("computeSolutions() delta == 0 testing ") {
    val List (a,b,c) = List(new Var(6.0), new Var(0.0), new Var(0.0))
    val delta = Polynomial.computeDelta(a, b, c)
    assert(Polynomial.computeSolutions(a,b,c,delta)() === Set(0.0))
  }

  test("computeSolutions() delta < 0 testing ") {
    val List (a,b,c) = List(new Var(1.0), new Var(1.0), new Var(1.0))
    val delta = Polynomial.computeDelta(a, b, c)
    assert(Polynomial.computeSolutions(a,b,c,delta)() === Set())
  }
}
