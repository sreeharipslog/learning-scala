package lectures.basics

/** Chapter: 1, Variables and Types
 */

/*
// Procedure syntax in version 2, **deprecated
object VariablesAndTypes {
    def main(args: Array[String]) {
    }
}
*/

// New Syntax in v2
object VariableAndType {

  def main(args: Array[String]): Unit = {
    val aBoolean = true
    println(s"Boolean = $aBoolean")
    val aByte: Byte = 1
    println(s"Byte = $aByte")
    val aShort: Short = 145
    println(s"Short = $aShort")
    val aInt = 12568 // Type Inference, Default to Int
    println(s"Integer = $aInt")
    val aLong: Long = 12356564654646L
    println(s"Long = $aLong")
    val aFloat: Float = 3.14f
    println(s"Float = $aFloat")
    val aDouble = 3.14 // Default to Double
    println(s"Double = $aDouble")
    val aChar: Char = 'a'
    println(s"Char = $aChar")
    val aString: String = "This is Scala"
    println(s"String = $aString")
    val bigInt = BigInt(1235445687456465L)
    println(s"BigInt = $bigInt")
    val bigDecimal = BigDecimal(123456895646545.6546546)
    println(s"BigDecimal = $bigDecimal")
  }
}