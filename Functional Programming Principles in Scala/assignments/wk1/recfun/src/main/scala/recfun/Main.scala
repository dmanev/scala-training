package recfun

object Main {
  def main(args: Array[String]) {
    println("Pascal's Triangle")
    for (row <- 0 to 10) {
      for (col <- 0 to row)
        print(pascal(col, row) + " ")
      println()
    }
  }

  /**
   * Exercise 1
   */
    def pascal(c: Int, r: Int): Int = {

      if (c == 0 || r == c) 1
      else
        pascal(c-1, r-1) + pascal(c, r-1)
    }
  
  /**
   * Exercise 2
   */
    def balance(chars: List[Char]): Boolean = {
      def inBrace(chars: List[Char], balance: Int): Boolean = {
        if(chars.isEmpty) {
          if( balance == 0) true
          else false
        }
        else
          if(chars.head == '(') inBrace(chars.tail, balance+1)
        else if(chars.head == ')' && balance > 0) inBrace(chars.tail, balance-1)
          else if(chars.head == ')' && balance <= 0) false
          else
            inBrace(chars.tail, balance)
      }

      inBrace(chars, 0)
  }
  
  /**
   * Exercise 3
   */
    def countChange(money: Int, coins: List[Int]): Int = {

      def crowl(S: List[Int], m: Int, n: Int): Int = {
        if(n == 0) 1
        else if(n < 0) 0
        else if(m <= 0 && n >= 1) 0
        else
          crowl( S, m-1, n) + crowl(S, m, n-S(m-1))
      }

      crowl(coins.sorted, coins.size, money)
    }
  }
