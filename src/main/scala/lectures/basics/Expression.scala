package lectures.basics


/**
 * Chapter:2, Expressions
 */

/* Expression vs Instruction/Statement. Expressions always return value whereas Statements execute or do something.
In Scala except few rest all are treated as expressions. Side effects are expressions that return 'Unit'
  */

object Expression extends App {
  println("Expressions in Scala")
  var aBoolean = true
  // If expression
  val aCondition = if (aBoolean) 50 else 100
  println(aCondition)
  // Code Block is an expression
  val aCodeBlock = {
    var x = 20
    var y = 200
    if (x > y) "a code block if" else "a code block else"
  }
  println(aCodeBlock)
  // While loop
  var iterate = 1
  var aLoop = while (iterate < 3) {
    print("a while loop to -> ")
    iterate += 1
  }
  println(aLoop)
  // print Function
  val aUnit = println("a println side effect in Scala")
  println(s"the value of above println side effect is $aUnit")
}