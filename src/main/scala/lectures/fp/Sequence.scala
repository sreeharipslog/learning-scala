package lectures.fp

import scala.util.Random

/**
 * Collection: Sequence
 */
object Sequence extends App {

  // Sequence
  val aSequence = Seq(3, 4, 2, 1) // List(3, 4, 2, 1)
  println(aSequence)
  println(aSequence.reverse)
  println(aSequence(2)) // aSequence.apply(2), retrieves element at index 2
  println(aSequence ++ Seq(9, 6, 8)) // concatenation , List(3, 4, 2, 1, 9, 6, 8)
  println(aSequence.sorted) // type by-default ordered

  // Range
  val aRange: Seq[Int] = 1 to 10 // inclusive range
  val anotherRange: Seq[Int] = 1 until 100 // exclusive range
  aRange.foreach(print)
  // times equivalency
  (1 until 5).foreach(x => print(s" hello $x "))
  // List
  // fast O(1) head, tail and isEmpty methods
  println("List")
  val aList = List(1, 2, 3, 4)
  val prependedList = 99 :: aList // also +:
  println(100 +: prependedList :+ 0) // prepend and append
  val aFill = List.fill(5)("Orange")
  println(aFill)
  println(aFill.mkString("?"))

  // Arrays, equivalent to Java arrays T[], extends Serializable and Cloneable, not Seq
  // Random Access
  val numbers = Array(1, 2, 3)
  val threeNumbers = Array.ofDim[Int](3)
  println(threeNumbers)
  threeNumbers.foreach(println) // default value is printed
  Array.ofDim[String](2).foreach(println)
  // mutation
  numbers(2) = 0 // syntactic sugar to numbers.update(2, 0),  only for mutable collections
  println(numbers.mkString("-"))
  // Arrays and Seq
  val arraySequence: Seq[Int] = numbers // Implicit Conversions, advanced
  println(arraySequence) // ArraySeq or WrappedArray

  // Vectors, fast element addition
  val aVector: Vector[Int] = Vector(1, 2, 3)
  println(aVector)

  // List vs Vector using insertion time comparator program
  val max_runs = 1000
  val max_capacity = 1000000

  def getWriteTime(collection: Seq[Int]): Double = {
    val r = new Random
    /*val time = for {
      it <- 1 to max_runs
    } yield {
      val currentTime = System.nanoTime()
      collection.updated(r.nextInt(max_capacity), r.nextInt())
      System.nanoTime() - currentTime
    }
    time.sum / max_runs
    */
    val time = (1 to max_runs).map(_ => {
      val currentTime = System.nanoTime()
      collection.updated(r.nextInt(max_capacity), r.nextInt()) // insert anywhere < max_capacity
      System.nanoTime() - currentTime
    }).sum
    time / max_runs
  }

  val range = 1 to max_capacity
  val numberList = range.toList
  val numberVector = range.toVector
  println("Test")
  // Ad: keep reference to tails
  // DisAd: updating in middle takes longer
  println(s"List time = ${getWriteTime(numberList)}")

  // Ad: depth of tree is small
  // DisAd: Need to replace entire 32-element chunk
  println(s"Vector time = ${getWriteTime(numberVector)}")
}