package lectures.basics

/**
 * Chapter:6
 * More on Scala String operation + String Interpolations
 */
object StringOps extends App {

  val aString = "This is a beautiful long string to test the power of String in Scala"
  // From Java
  println(aString.charAt(12))
  println(aString.substring(10, 25))
  println(aString.split(" ").toList)
  println(aString.replace(" ", "_"))
  println(aString.startsWith("This"))
  println(aString.toLowerCase())
  println(aString.length)
  // From Scala
  var intString = "421596564"
  println(intString.toInt)
  println(intString.reverse)
  println(intString.take(3))
  // Precede and Append
  // println("COOL" +: aString :+ "MATRIX")
  println('a' +: "2" :+ 'z')

  // String Interpolator
  // S interpolator
  // s-strings are strings which have variable directly inside of them
  println(s"This is an example of 's' interpolator in scala = $intString")
  println(s"This is an example of 's' interpolator in scala = ${intString + aString}")
  // F Interpolator. Similar to S interpolator, applies formatted string on top. printf style formatting
  // f strings are simple formatted strings
  println(f"This is an example of 'f' interpolator. Int = ${42}%d and Double = ${65.654654}%4.2f")
  // RAW Interpolator. Similar to S interpolator but print string literally. Variables and Values escaped
  // raw string: No escaping of literals as in s strings
  println(raw"This is an example of 'raw' interpolator \n\n\n\n\n")
  val escapedString = "This is 2nd example of 'raw' interpolator \n\n Welcome to the Jungle"
  println(raw"$escapedString") // escapes

  // Theory
  /*
  * Interpolation works by using StringContext case class. All interpolated strings are processed
  * strings that calls `id` (s, f, raw) method in StringsContext class with literal strings as their arguements.
  * eg: s"Hello, This is $name"  --> StringContext("Hello, This is ", "").s(name)
  * To create custom interpolator add an implicit class that adds new interpolator to StringContext.
   */
}