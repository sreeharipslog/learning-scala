package lectures.basics

import java.time.Year

/**
 * Chapter: 5, Call By Value vs Call be Name
 */
object CBVAndCBN extends App {

  // Example 1
  // In Call by Value, value of the expression is passed to argument
  private def callByValue(time: Long): Unit = {
    println(s"Call By Value: Time1 = $time")
    println(s"Call By Value: Time2 = $time")
    // Time1 == Time2
  }

  callByValue(System.nanoTime())

  // In Call by Name, Expression is passed as it is and executed with needed. i.e. Lazy Evaluation
  // => Universal Transformer
  private def callByName(time: => Long): Unit = {
    println(s"Call By Name: Time1 = $time")
    println(s"Call By Name: Time2 = $time")
    // Time3 != Time4
  }

  callByName(System.nanoTime())

  // Example 2 for Call by Name and Working of Lazy Evaluation
  private def infinite(): Int = 1 + infinite()

  private def printFirst(first: Int, second: => Int): Unit = println(first)

  // printFirst(infinite(), Year.now().toString.toInt) // Stack overflow
  printFirst(Year.now().toString.toInt, infinite())
}