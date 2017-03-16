
object test {

  //
  // Combinational search example
  //
  val fruit = Set("apple", "banana", "pear")
  val st = (1 to 6).toSet

  st map (_ + 2)

//  fruit.filter(_.startsWith == "app")

  //
  // N-Queens example
  //

  def isSafe(col: Int, queens: List[Int]): Boolean = {
    val row = queens.length
    val queensWithRows = (row-1 to 0 by -1) zip queens

    queensWithRows forall {
      case (r, c) => (c != col) && (math.abs(col - c) != row - r)
    }
  }

  def queens(n: Int): Set[List[Int]] = {
    def placeQueens(k: Int): Set[List[Int]] =
      if(k == 0) Set(List())
      else
        for{
          queens <- placeQueens(k - 1)
          col <- 0 until n
          if isSafe(col, queens)
        } yield col :: queens
    placeQueens(n)
  }

  queens(4)

  //
}