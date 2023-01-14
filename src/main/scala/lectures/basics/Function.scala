package lectures.basics

/** Chapter: 3, Functions
 */

/* Functions in Scala can be written in 2 syntax 1). Method syntax 2).
 * Function/val syntax
 */

// Using method syntax
object Function extends App {
  println("Functions in Scala")

  // Type infered Function
  def add2Number(a: Int, b: Int): Int = a + b
  // factorial function
  println("factorial of number 25")

  def factorial(num: Int): Int = if (num == 1) 1 else num * factorial(num - 1)

  println(factorial(25))

  // Auxilary functions
  def simpleFunction(str0: String): Unit = {
    println("This is a simple function")

    def aux(str1: String): String = str0 + " " + str1

    println(aux("World"))
  }

  simpleFunction("Hello")

  // Prime checker funciton
  def isPrime(n: Int): Boolean = {
    def isPrimeUntil(num: Int): Boolean =
      if (num <= 1) true else (n % num != 0) && isPrimeUntil(num - 1)

    isPrimeUntil(n / 2)
  }

  println(s"395 * 6546 is prime? Ans: ${isPrime(395 * 6546)}")
}