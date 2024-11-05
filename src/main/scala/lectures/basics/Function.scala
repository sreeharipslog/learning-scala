package lectures.basics

import scala.annotation.tailrec

/** Chapter: 3, Functions
  */

/* Functions in Scala can be written in two syntax 1). Method syntax 2).
 * Function/val syntax
 */

// Using method syntax
object Function extends App {
  println("Functions in Scala")

  // Type inferred Function
  def add2Number(a: Int, b: Int): Int = a + b

  // factorial function
  println("factorial of number 25")
  // 1. Regular recursive function, results in StackOverflow exception
  def factorial(num: Int): BigInt = if (num == 1) 1 else num * factorial(num - 1)

  // Tail recursive implementation of factorial
  @tailrec
  def tailFactorial(n: Int, accumulator: BigInt = 1): BigInt =
    if (n <= 0) accumulator else tailFactorial(n - 1, n * accumulator)
  println(factorial(25))
  println(tailFactorial(25))

  // Auxiliary functions
  def simpleFunction(str0: String): Unit = {
    println("This is a simple function")
    def aux(str1: String): String = str0 + " " + str1
    println(aux("World"))
  }
  simpleFunction("Hello")

  // Prime checker function
  def isPrime(n: Int): Boolean = {
    @tailrec
    // This becomes a tail recursive function because of the short circuit && operator
    // Scala compiler optimizes short circuit operation without the need of extra stack frames.
    def isPrimeUntil(num: Int): Boolean =
      if (num <= 1) true else (n % num != 0) && isPrimeUntil(num - 1)
    isPrimeUntil(n / 2)
  }
  println(s"395 * 6546 is prime? Ans: ${isPrime(395 * 6546)}")
}
