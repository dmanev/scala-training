import common._

object test{

  def abcd(i: Int): Int = i * i

  val (a, b) = parallel(abcd(2), abcd(3))


  val s = "(if (zero? x) max (/ 1 x))\nI told him (that it's not (yet) done). (But he wasn't listening)"

  s.filter(p => p == '(' || p == ')')

  "(o_()\n:-)\n())(".filter(p => p =='(' || p == ')')

  def balance(chars: Array[Char]): Boolean =  {
    def traverse(s: String): Boolean =
      if(s.isEmpty) true
      else if(s.replace("()", "") == s) false
      else
        traverse(s.replace("()", ""))

    traverse(chars.filter(p => p == '(' || p == ')').toString)
  }

  balance(s.toCharArray)
}