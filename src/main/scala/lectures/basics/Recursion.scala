package lectures.basics

import scala.annotation.tailrec

/** Chapter: 4, Recursion
 */
object Recursion extends App {
  println("Exercise for Scala Recursion functionality \n")

  // Stack Recursion

  /**
   * Factorial using Stack Recursion
   *
   * @param num number
   * @return factorial of num
   */
  private def factorial(num: Int): Int = {
    if (num <= 1) 1
    else {
      println(s"Computing factorial for $num -- calling factorial of ${num - 1}")
      val result = num * factorial(num - 1)
      println(s"Computed factorial of $num")
      result
    }
  }
  // println(factorial(500)) // Stack Overflow due to Stack Recursion
  println(s"Factorial of 4 = ${factorial(4)}")

  // Tail recursion

  /**
   * Factorial using Tail Recursion
   *
   * @param num number
   * @return
   */
  private def anotherFactorial(num: Int): BigInt = {
    // Auxiliary function for better clarity
    @tailrec // Compiler will check for tail recursion. Error if stack recursion is found
    def trRecFactorial(x: Int, accumulator: BigInt = 1): BigInt = {
      if (x <= 1) accumulator
      else trRecFactorial(x - 1, x * accumulator) // Recursive call in Tail Position
      // Accumulator stores intermediate result
      // Call stack is reused
    }

    trRecFactorial(num)
  }

  println(s"Factorial of 5 = ${anotherFactorial(5)}")
  println(s"Factorial of 2000 = ${anotherFactorial(2000)}")

  // INSTEAD of LOOPS, USE TAIL RECURSION

  /*
  > Define Functions for the following
  1. Concatenate a string n times
  2. Check if a number is prime
  3. Find the 'nth Fibonacci Number
   */

  // Concatenate a string n times
  @tailrec
  private def concat(aString: String, times: Int, acc: String = ""): String = {
    if (times == 0) acc
    else concat(aString, times - 1, aString + acc)
  }

  println(concat("This is me, Scala", 3))

  // Check if a number is prime
  private def isPrime(num: Int): Boolean = {
    @tailrec
    def isStillPrime(acc: Boolean = true, x: Int): Boolean = {
      if (!acc) false
      else if (x == 1) true
      else isStillPrime(num % x != 0 && acc, x - 1)
    }

    // Named arguments when using default arguments. Check problem when using default argument. This method solves it
    isStillPrime(true, num / 2)
  }

  println(s"Is Prime 2003? Ans = ${isPrime(2003)}")
  println(s"Is Prime 1729? Ans = ${isPrime(1729)}")

  // Find the 'nth Fibonacci Number
  private def nthFibonacci(limit: Int): Int = {
    @tailrec
    def trFibonacci(curr: Int, last: Int, nextToLast: Int): Int = {
      if (curr > limit) last
      else trFibonacci(curr + 1, last + nextToLast, last)
    }

    if (limit <= 2) 1 else trFibonacci(3, 1, 1)
  }

  println(s"4nd Fibonacci Number = ${nthFibonacci(4)}")
  println(s"8th Fibonacci Number = ${nthFibonacci(8)}")
  println(s"16th Fibonacci Number = ${nthFibonacci(16)}")
}