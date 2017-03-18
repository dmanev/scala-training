

object test{
  import kmeans._
  object KM extends KMeans
  import KM._
  import scala.collection._

  val points: GenSeq[Point] = IndexedSeq()
  val mean = new Point(1, 1, 1)
  val means: GenSeq[Point] = IndexedSeq(mean)
  val expected = GenMap[Point, GenSeq[Point]]((mean, GenSeq()))

  classify(points, means)

  val cl = points.par.groupBy(findClosest(_,means))

  cl ++ means.filter(! cl.contains(_)).map(_ -> List()).toMap

  val newMeans: GenSeq[Point] = IndexedSeq(new Point(0,0,0))

  means zip newMeans map {case (a,b) => a.squareDistance(b)} isEmpty
}
