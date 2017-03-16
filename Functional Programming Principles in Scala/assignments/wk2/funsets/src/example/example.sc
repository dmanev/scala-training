def sum(f: Int => Int, a:Int, b:Int): Int =
  if (a > b) 0
  else f(a) + sum(f, a+1, b)

sum(x => x, 1, 4)

def tsum(f: Int => Int)(a: Int, b:Int): Int = {
  def loop(a: Int, acc: Int): Int = {
    if (a > b) acc
    else
      loop(a+1, f(a) + acc)
  }
  loop(a, 0)
}

tsum(x => x)( 1, 3)

def prod(f: Int => Int)(a: Int, b: Int): Int = {
  if(a > b) 1
  else f(a) * prod(f)(a+1, b)
}

prod(x => x)(1, 4)

import math.abs

val tolerance=0.0001

def isCloseEnough(x: Double, y:Double) =
  abs((x - y) / x )/x < tolerance

def fixedPoint(f: Double => Double)(firstGuess: Double) = {
  def iterate(guess: Double): Double = {
    val next = f(guess)
    if(isCloseEnough(guess, next)) next
    else
      iterate(next)
  }
  iterate(firstGuess)
}

def averageDamp(f: Double => Double)(x: Double) =
  (x + f(x))/2

fixedPoint(x => 1 + x/2)(1)

def sqrt(x: Double) =
  //fixedPoint(z => (z + x/z)/2)(x)
  fixedPoint(averageDamp(z => x/z))(1)

//averageDamp(x => 2/x)(2)

sqrt(4)

class Rational(x: Int, y: Int){
  def numer =x
  def denum =y
}

val r = new Rational(1,2)

r.numer

