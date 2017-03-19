import scala.collection.mutable.ArrayBuffer

object test{
  import kmeans._
  object KM extends KMeans
  import KM._
  import scala.collection._

//  val points: GenSeq[Point] = IndexedSeq()
//  val mean = new Point(1, 1, 1)
//  val means: GenSeq[Point] = IndexedSeq(mean)
//  val expected = GenMap[Point, GenSeq[Point]]((mean, GenSeq()))
//
//  classify(points, means)
//
//  val cl = points.par.groupBy(findClosest(_,means))
//
//  cl ++ means.filter(! cl.contains(_)).map(_ -> List()).toMap

//  val newMeans: GenSeq[Point] = IndexedSeq(new Point(0,0,0))

//  means zip newMeans map {case (a,b) => a.squareDistance(b)} isEmpty

  val seq1to99 = 1 to 99 map(_.toDouble)
//  seq1to99.take(3)
  val oldMeans = ((seq1to99, seq1to99, seq1to99) zipped) map(new Point(_,_,_))

  val newMeans = oldMeans.dropRight(1) ++ Seq(new Point(99.0, 99.0, 99.01))
  val eta = 0.01

  oldMeans zip newMeans map {case (a, b) => a.squareDistance(b)} filter(_ > eta) isEmpty

  val points = Seq((0.0, 0.0, 1.0),(0.0, 0.0, -1.0),(0.0, 1.0, 0.0),(0.0, 10.0, 0.0)) map {case (x,y,z) => new Point(x,y,z)}
  val means = Seq((0, -1, 0), (0, 2, 0)) map {case (x,y,z) => new Point(x,y,z)}

  kMeans(points, means, 12.25 )

  (new Point(1,2,3)).eq(new Point(1,2,3))
}
