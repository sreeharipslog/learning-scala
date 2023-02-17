package lectures.oop

import scala.annotation.tailrec

object OOBasics extends App {
  val aPerson = new Person // Instantiation
  println(aPerson)
  // Avenger object
  val im = new Avenger("Iron Man", "Human", 200)
  im.assemble()
  im.addWeight(300)
  println(s"New Iron Man weight is ${im.weight}")
  // Exercise 1: Writer-Novel
  val author = new Writer("Dan", "Brown", 1964)
  println(author.fullName)
  val novel = new Novel("Origin", 2020, author)
  println(novel.authorAge)
  println(novel.isWrittenBy(author))
  val imposter = new Writer("Dan", "Brown", 1964)
  println(novel.isWrittenBy(imposter))
  val originCopy = novel.copy(2022)
  println(originCopy.isWrittenBy(author))
  // Exercise 2: Counter
  val counter = new Counter
  counter.inc.print // The convention is that a method should have parentheses if it has side effects
  counter.inc.inc.inc.inc.print()
  counter.inc(10).print()
  counter.print() // Initial counter never incremented
}

// Class-1: Very Basic Class, without constructor and body
class Person

// Class-2: With Constructor (+Class-Parameters) and Body (+Fields and Method)
// By Default fields or class-params declared as field, have public access
class Avenger(val name: String, power: String = "default", var weight: Int) {
  val genetic: Boolean = false // Field

  def this(name: String) = this(name, weight = 100) // Overloaded constructor

  def assemble(): Unit = println("Avengers assemble")

  def addWeight(weight: Int) = this.weight += weight // Updating fields
}

/**
 * Exercise 1
 * Class-> Writer - firstName, lastName, year (of birth)
 * methods: fullName
 * Class-> Novel - name, year (of release), author (Writer instance)
 * methods: authorAge, isWrittenBy [to validate author], copy(new year of release)
 */
class Writer(firstName: String, lastName: String, val year: Int) {
  def fullName: String = s"$firstName $lastName"
}

class Novel(name: String, yearOfRelease: Int, author: Writer) {
  def authorAge: Int = yearOfRelease - author.year

  def isWrittenBy(author: Writer): Boolean = this.author == author // Reference Comparison #NotIdeal

  def copy(newYear: Int): Novel = new Novel(name, newYear, author)
}

/**
 * Exercise 2
 * Class-> Counter - count
 * methods: currentCount, increment/decrement count, addCount [overload inc/dec count]
 */

class Counter(val count: Int = 0) {
  def inc: Counter = {
    println("incrementing") // logger
    new Counter(count + 1) // In FP fields are generally not modified. Always returns new object
  }

  def dec: Counter = {
    println("decrementing") // logger
    new Counter(count - 1)
  }

  @tailrec
  final def inc(n: Int): Counter = {
    if (n <= 0) this
    else inc.inc(n - 1)
  }

  def print(): Unit = println(s"count = $count") // Parameterless Method with side-effect
}