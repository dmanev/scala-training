package reductions

import java.util.concurrent._
import scala.collection._
import org.scalatest.FunSuite
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import common._
import java.util.concurrent.ForkJoinPool.ForkJoinWorkerThreadFactory

@RunWith(classOf[JUnitRunner]) 
class LineOfSightSuite extends FunSuite {
  import LineOfSight._
  test("lineOfSight should correctly handle an array of size 4") {
    val output = new Array[Float](4)
    lineOfSight(Array[Float](0f, 1f, 8f, 9f), output)
    assert(output.toList == List(0f, 1f, 4f, 4f))
  }

  test("lineOfSight should correctly handle an array of size 8") {
    val output = new Array[Float](8)
    lineOfSight(Array[Float](0f, 1f, 8f, 9f, 14f, 10f, 12f, 35f), output)
    assert(output.toList == List(0.0, 1.0, 4.0, 4.0, 4.0, 4.0, 4.0, 5.0))
  }

  test("upsweepSequential should correctly handle the chunk 1 until 4 of an array of 4 elements") {
    val res = upsweepSequential(Array[Float](0f, 1f, 8f, 9f), 1, 4)
    assert(res == 4f)
  }


  test("downsweepSequential should correctly handle a 4 element array when the starting angle is zero") {
    val output = new Array[Float](4)
    downsweepSequential(Array[Float](0f, 1f, 8f, 9f), output, 0f, 1, 4)
    assert(output.toList == List(0f, 1f, 4f, 4f))
  }

  test("parLineOfSight should correctly handle an array of size 4") {
    val output = new Array[Float](4)
    parLineOfSight(Array[Float](0f, 1f, 8f, 9f), output, 2)
    assert(output.toList == List(0f, 1f, 4f, 4f))
  }

  test("parLineOfSight should correctly handle an array of size 5, threshold 1") {
    val output = new Array[Float](5)
    parLineOfSight(Array[Float](0f, 1f, 8f, 9f, 14f), output, 1)
    assert(output.toList == List(0f, 1f, 4f, 4f, 4f))
  }

  test("parLineOfSight should correctly handle an array of size 8") {
    val output = new Array[Float](8)
    parLineOfSight(Array[Float](0f, 1f, 8f, 9f, 14f, 10f, 12f, 35f), output, 4)
    assert(output.toList == List(0.0, 1.0, 4.0, 4.0, 4.0, 4.0, 4.0, 5.0))
  }

  test("parLineOfSight should correctly handle an array of size 9") {
    val output = new Array[Float](9)
    parLineOfSight(Array[Float](0f, 1f, 8f, 9f, 14f, 10f, 12f, 35f, 25f), output, 4)
    assert(output.toList == List(0.0, 1.0, 4.0, 4.0, 4.0, 4.0, 4.0, 5.0, 5.0))
  }
}

