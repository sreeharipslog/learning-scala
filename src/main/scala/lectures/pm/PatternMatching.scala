package pm

import scala.util.Random

object PatternMatching extends App {

  // Pattern Matching (PM) is "switch" on steroids
  val random = new Random
  val x = random.nextInt(10)
  val description = x match {
    case 0 => "This is Zero"
    case 1 => "This is One"
    case 9 => "This is Nine"
    case _ => "Number not found" // _ is WILD card for PM
  }
  println(description) // Type of PM expression is the unified lowest common ancestor TYPE of all cases

  // 1. Decompose values (Extractor pattern for Case Classes) and Guards
  case class Person(name: String, age: Int)

  val aPerson = Person("Bob", 18)
  val greetings = aPerson match { // if aPerson matches case and condition/guard
    case Person(n, a) if a < 21 => s"Hi! This is $n and I can't drink in US" // Guard
    case Person(n, a) => s"Hi! This is $n and I am $a years old"
    case _ => "I don't know who I am"
  }
  println(greetings)

  // PM for Sealed hierarchy
  sealed class Animal

  case class Dog(breed: String) extends Animal

  case class Parrot(greeting: String) extends Animal

  val animal: Animal = Dog("Kangal")
  animal match {
    case Dog(someBreed) => println(s"Matched a dog of $someBreed breed")
    case Parrot(greeting) => println(greeting)
    case _ => println("Not an Animal")
  }

  /* << Remember >>
  1. Do not PM everything, stick to basics and use language constructs
  2. PM works really well with case class, due extractor pattern. Check on how to implement extractors for normal
  classes
   */

  /* << Exercise >>
  1. Write a function to convert an expression into human readable form
      1. Sum(Number(1), Number(5)) = 1 + 5
      2. Sum(Number(9), Sum(Number(8), Number(25))) = 9 + 8 + 25
      3. Product(Sum(Number(18), Number(2)), Sum(Number(3), Number(4))) = (1 + 2) * (3 + 4)
      4. Sum(Product(Number(9), Number(8)), Number(7)) = 9 * 8 + 7
   */
  trait Expression

  case class Number(n: Int) extends Expression

  case class Sum(e1: Expression, e2: Expression) extends Expression

  case class Product(e1: Expression, e2: Expression) extends Expression

  // Recursion
  def showExp(exp: Expression): String = exp match {
    case Number(n) => s"$n"
    case Sum(e1, e2) => s"${showExp(e1)} + ${showExp(e2)}"
    case Product(e1, e2) => {
      def showParenthesis(e: Expression): String = e match {
        case Product(_, _) => showExp(e)
        case Number(_) => showExp(e)
        case _ => s"(${showExp(e)})"
      }

      s"${showParenthesis(e1)} * ${showParenthesis(e2)}"
    }
  }

  println(showExp(Sum(Number(1), Number(5))))
  println(showExp(Sum(Number(9), Sum(Number(8), Number(25)))))
  println(showExp(Product(Sum(Number(18), Number(2)), Sum(Number(3), Number(4)))))
  println(showExp(Sum(Product(Number(9), Number(8)), Number(7))))
}