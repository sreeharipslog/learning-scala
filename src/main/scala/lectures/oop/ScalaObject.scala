package oops

// Singleton Object
object ScalaObject {
  // Scala doesn't have "static" or Class level functionality
  // Scala application is a Scala Object with a main method signature: def main(args: Array[String]): Unit
  def main(args: Array[String]): Unit = {
    // Class level functionality
    println(Person.REPEAT_TIMES)
    println(Person.isPerson)
    // Multiple Instances
    val hari = new Person("Sreehari")
    val emma = new Person("Emma")
    println(hari == emma)
    // Singleton
    val person1 = Person
    val person2 = Person
    println(person1 == person2)
    // Factory method
    val child = Person.apply(emma, hari)
    println(child.name)
    val child2 = Person(emma, hari) // Object callable like a function, widely used
    println(child2.name)
  }

  // Singleton Object and Class with Same name --> COMPANIONS
  // Companions can access each others private method
  object Person { // Companion Object
    val REPEAT_TIMES = 100

    // Factory methods or Utility methods in Scala Object
    def isPerson: Boolean = true

    // Normally factory methods are named apply
    // def from(mother: Person, father: Person): Person = new Person("Child")
    def apply(mother: Person, father: Person): Person = new Person(s"Child of ${mother.name} and ${father.name}")
  }

  class Person(val name: String) {
    // Companion Class
  }

  // Scala ensures that all code we write is accessible via objects
}