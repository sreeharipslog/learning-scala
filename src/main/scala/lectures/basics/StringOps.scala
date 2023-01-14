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
  println(s"This is an example of 's' interpolator in scala = $intString")
  println(s"This is an example of 's' interpolator in scala = ${intString + aString}")
  // F Interpolator. Similar to S interpolator, applies formatted string on top. printf style formatting
  println(f"This is an example of 'f' interpolator. Int = ${42}%d and Double = ${65.654654}%4.2f")
  // RAW Interpolator. Similar to S interpolator but print string literally. Variables and Values escaped
  println(raw"This is an example of 'raw' interpolator \n\n\n\n\n")
  val escapedString = "This is 2nd example of 'raw' interpolator \n\n Welcome to the Jungle"
  println(raw"$escapedString") // escapes
}