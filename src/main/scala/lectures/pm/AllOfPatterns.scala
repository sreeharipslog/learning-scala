package lectures.pm

object AllOfPatterns extends App {

  // 1. Constants
  val literal: Any = "something"
  val literalMatch = literal match {
    case 1 => "One" // an Integer Literal pattern
    case "string" => "The Scala" // A string literal pattern
    case true => "The Truth" // A boolean literal pattern
    case AllOfPatterns => "Singleton" // singleton class pattern
  }

  // 2. Match Anything (Wild card matching)
  val wildCardMatching = literal match {
    case _ => "Wild card pattern" // PM using wild card
  }
  val anythingMatchAnother = literal match {
    case variable => s"$variable" // Ad: Variable can be used in return expression
  }

  // 3. Tuple Matching
  val aTuple = 1 -> 2
  val tupleMatch = aTuple match {
    case (1, 1) => "Tuple of literals pattern" // Tuple of literals
    case (something, 2) => s"Nested pattern $something" // Nested Extractor PM
  }
  val nestedTuple = (1, (3, 4))
  val nestedTupleMatch = nestedTuple match {
    case (_, (variable, 4)) => s"Nested tuple pattern $variable" // Nested tuple PM via Wild card and variable
  }
  /*
    // 4. Case Class PM (Constructor Pattern)
    // PM can be nested with Case classes as well
    val aList: MyList[Int] = Cons(1, Cons(5, Empty))
    val ccMatch = aList match {
      case Empty => "Case class direct match"
      case Cons(head, Cons(subHead, subTail)) => "some nested case class pattern"
    }

    // 5. List Matching
    val aStandardList = List(1, 2, 3, 4)
    val standardListMatch = aStandardList match {
      case List(1, _, _, _) => "wild card list matching" // Extractor Pattern
      case List(1, _*) => "Var arg matching" // VarArg Pattern (List of arbitrary length)
      case 1 :: List(_) => "Infix pattern" // Infix pattern
      case List(1, 2, 3) :+ 4 => "Another infix pattern rep"
    }

    // 6. Type Specifiers
    val unknown: Any = 2
    val unknownMatch = unknown match {
      case list: List[Int] => "explicit type specification pattern"
      case _ => "Unknown"
    }

    // 7. Name Binding

    val nameBindMatch = aList match {
      case nonEmpty@Cons(_, _) => "Name bind pattern" // name usable in return expression
      case Cons(_, rest@Cons(2, _)) => "nested binding inside nested patterns"
    }

    // 8. Multi-Pattern

    val multiPatternMatch = aList match {
      case Empty | Cons(0, _) => "Compound pattern" // Compound pattern (multi)
    }

    // 9. if guards
    val ifGuardMatch = aList match {
      case Cons(_, Cons(secondElement, _)) if secondElement % 2 == 0 => "If guarded pattern"
    }*/

  // Erasure Problem in JVM (Generic backward compatibility bug)
  val newList = List(1, 2, 3)
  val newListMatch = newList match {
    case strList: List[String] => "list of strings"
    case numList: List[Int] => "List of integers"
    case _ => "Unknown"
  }
  println(newListMatch)
  // fruitless type test: a value of type List[Int] cannot also be a List[String](but still might match its erasure)
}