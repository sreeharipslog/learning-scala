package pm

object PatternsEverywhere extends App {

  // BigIdea #1: "catch" is a MATCH
  val ideaTry = try {
    Some("of just a Code")
  } catch {
    case npe: NullPointerException => None
    case rte: RuntimeException => None
    case _ => Some("This is just fun")
  }
  println(ideaTry)

  /* BigIdea#1 is similar to
  try {
      Some("Some Code")
    } catch (e) { e match {
        case npe: NullPointerException => None
        case rte: RuntimeException => None
        case _ => Some("This is just fun")
      }
    }
   */

  // BigIdea #2: Generators are based on Pattern Matching
  val list: List[Int] = (1 until 7).toList
  val evenOnes = for {
    x <- list if x % 2 == 0
  } yield x * 10
  println(evenOnes)

  val tuples = List((1, 2), (3, 4))
  val filterTuples = for {
    (first, second) <- tuples
  } yield first * second // yield optional for Unit
  println(filterTuples)
  // This can be applied to case classes, :: operators etc

  // BigIdea #3: Multiple value definition based on Pattern Matching
  val tuple = (1, 2, 3)
  val (a, b, c) = tuple
  println(b)
  // List match
  val head :: tail = list // equivalent to list match pattern 'tail :: head'
  println(head)
  println(tail)

  // BigIdea #4: Partial Function literals are based on Pattern matching
  // known as "Pattern Matching Anonymous Function (aka Lambda)"
  val mappedValue = list.map { // Partial Function literal
    case v if v % 2 == 0 => "even number"
    case 1 => "The one"
    case _ => "something else"
  }
  println(mappedValue)

  val alternateMappedValue = list.map(x => x match {
    // Idea suggests: Convert match statement to pattern matching anonymous function
    case v if v % 2 == 0 => "even number"
    case 1 => "The one"
    case _ => "something else"
  })
  println(alternateMappedValue)
}