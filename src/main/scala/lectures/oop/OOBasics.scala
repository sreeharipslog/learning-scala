package lectures.oop

object OOBasics extends App {
  // Class-1: Very Basic Class, without constructor and body
  class Person

  // Instantiation
  val aPerson = new Person
  println(aPerson)

  // Class-2: With Constructor (+Class-Parameters) and Body (+Fields and Method)
  // By Default, fields or class-params declared as field, have public access
  class Avenger(val name: String, power: String = "default", weight: Int) {
    val genetic: Boolean = false // Field

    def this(name: String) = this(name, weight = 100) // Overloaded constructor

    def assemble(): Unit = println("Avengers assemble")

    def addWeight(weight: Int) = this.weight += weight
  }

  val im = new Avenger("Iron Man", "Human", 200)
  im.assemble()
}