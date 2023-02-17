package lectures.oop

import scala.language.postfixOps

/**
 * Syntactic Sugar or Method Notations
 */
object MethodNotations extends App {
  // Class Person
  class Person(val name: String, favoriteMovie: String, val age: Int = 0) {
    def likes(movie: String): Boolean = this.favoriteMovie == movie

    def hangoutWith(other: Person): String = s"$name hangs out with ${other.name}"

    def +(other: Person): String = s"$name loves ${other.name}" // Overloaded +

    def unary_! : String = s"$name, what the heck?!" // Unary !. Watch the space.

    def isAlive: Boolean = true

    def apply(): String = s"Hi, my name is $name and I like $favoriteMovie"

    // Exercise
    def +(nickName: String): Person = new Person(s"$name $nickName", favoriteMovie)

    def unary_+ : Person = new Person(name, favoriteMovie, age + 1)

    def learns(subject: String): String = s"$name is learning $subject"

    def learnScala: String = this learns "Scala"

    def apply(n: Int): String = s"$name watched $favoriteMovie $n times"
  }

  /*
    Testing
   */
  val hari = new Person("Sreehari", "Dark Knight")
  // Infix Notation: Applies to methods with single parameter
  println(hari likes "Dark Knight") // hari.likes("Dark Knight)
  println(hari likes "Cinderella")
  val emma = new Person("Emma", "Interstellar")
  println(hari hangoutWith emma) // Syntactic sugars makes Scala closer to Natural Language
  println(hari + emma) // hari.+(emma)
  println(1 + 2) // 1.+(2) -> All Operators are methods in Scala
  // Prefix Notation
  val x = -1 // prefix - delegates to unary_-
  val y = 1.unary_-
  println(x == y)
  // Unary Operator: Applies to following operators -> +, -, !, ~
  println(!hari)
  // Postfix Notation:  Postfix operator isAlive needs to be enabled. Rarely used
  println(hari isAlive) // hari.isAlive
  // apply method: Important
  println(hari.apply())
  println(emma()) // object() delegates to apply() method

  /* Exercise
    1. overload + operator to return a new person with a nickname.
    2. add age to Person class and write a unary + operator to increment age by 1
    3. Add a learns method that returns: 'name' learning 'subject'
       Add learnScala method, calls learns method with course "Scala"
    4. overload apply method to return: 'name' watched 'favouriteMovie' 'n' times
   */
  println((hari + "The Batman")())
  println((+hari).age)
  println(hari learnScala)
  println(emma learns "Haskel")
  println(hari(10))
  println(emma(20))
}