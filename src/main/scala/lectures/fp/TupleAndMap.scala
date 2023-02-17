package lectures.fp

import scala.annotation.tailrec

object TupleAndMap extends App {

  // Tuple: Ordered and finite collection. 22 types
  val aTuple = new Tuple2[String, Int]("Awesome", 2023)
  val anotherRep = Tuple2("Cool", "Companion")
  val justAnotherRep = ("clever", 2023)
  val againAnotherRep = "clever" -> 2025
  // Tuple[A, B] is represent by type (A, B)
  println(aTuple)
  println(anotherRep.toString())
  println(justAnotherRep._1)
  println(againAnotherRep.copy(_2 = 2030)) // _ to index element
  println(anotherRep.swap)

  // Map: Key_Value pair
  val aMap: Map[Int, String] = Map((1, "This is Java"), 2 -> "This is Scala")
  println(aMap)
  val phoneBook = Map("Hari" -> 101, "Emma" -> 102)
  val betterPhoneBook = Map("Hari" -> 101, "Emma" -> 102).withDefaultValue(0)
  println(phoneBook.contains("Rachel"))
  println(phoneBook("Hari"))
  // println(phoneBook("Max")) // java.util.NoSuchElementException
  println(betterPhoneBook("Susan"))
  // add an entry or pairing
  val wiredTuple = phoneBook + "Monica" -> 103 // How a () can change things
  println(s"A wired answer = $wiredTuple")
  val newContact = ("Monica" -> 103)
  println(phoneBook + newContact)

  // Functional on Map
  // map, flatMap, filter
  println(phoneBook.map(pair => pair._1.toLowerCase + pair._2)) // List
  println(phoneBook.map(pair => pair._1.toLowerCase -> pair._2)) // Map ----(2)
  // Filter Keys and Map Values
  println(phoneBook.view.filterKeys(_.startsWith("E")).toMap)
  println(phoneBook.view.mapValues(_ * 10).toMap)
  // Conversions
  println(phoneBook.toList) // list of tuples
  println(List("this is " -> 1, "Cool" -> 2).toMap) // converts list of tuples to map
  val names = List("Hari", "Emma", "James", "Joel", "Rachel", "Sruthy")
  println(names.groupBy(_.charAt(0))) // names.groupBy(name => name.charAt(0))

  // Exercise
  /*
  1. What would happen if phoneBook contained "jim"->1 and "JIM"->2 and we execute (2)
  2. Overly simplified social network based on maps
      Person = String
      - add a person to network
      - friend (mutual)
      - unfriend (mutual)
      - remove

      - number of friends for a person
      - person with most friends
      - how many people have no friends
      - if there is a social connection between 2 people (direct or indirect)
     */
  // Exercise 1: Overrides pairing with latest pairing
  println(Map("jim" -> 101, "JIM" -> 102).map(pair => pair._1.toLowerCase -> pair._2))

  // Exercise 2: Social Network Application using Maps

  /**
   * Add a person to social network platform
   */
  def addPerson(networkApp: Map[String, Set[String]], person: String): Map[String, Set[String]] = networkApp + (person ->
    Set())

  /**
   * Make A and B friends
   */
  def friend(networkApp: Map[String, Set[String]], a: String, b: String): Map[String, Set[String]] = {
    val friendsA = networkApp(a)
    val friendsB = networkApp(b)
    networkApp + (a -> (friendsA + b)) + (b -> (friendsB + a)) // overrides friends list
  }

  /**
   * UnFriend A and B
   */
  def unFriend(networkApp: Map[String, Set[String]], a: String, b: String): Map[String, Set[String]] = {
    val friendsA = networkApp(a)
    val friendsB = networkApp(b)
    networkApp + (a -> (friendsA - b)) + (b -> (friendsB - a)) // overrides friends list
  }

  /**
   * Remove a person from network app
   */
  def remove(networkApp: Map[String, Set[String]], person: String): Map[String, Set[String]] = {
    @tailrec // before removing person, unfriend person from all his mutual friends (dangling friendship removal)
    def removeAux(friends: Set[String], accumulator: Map[String, Set[String]]): Map[String, Set[String]]
    = {
      if (friends.isEmpty) accumulator
      else removeAux(friends.tail, unFriend(accumulator, person, friends.head))
    }

    val unfriended = removeAux(networkApp(person), networkApp) // Mutually cleared network
    unfriended - person // overloaded -(key) // overloaded +(tuple)
  }

  val empty: Map[String, Set[String]] = Map()
  // Points to remember:
  val emptyAnotherRep: String => Set[String] = Map()
  val emptyLikeAnotherRep: String => Set[String] = _ => Set() // represents a Function1[String, Set[String]]
  // Syntactic sugar/Functional style for return type is better suitable for Function types, to avoid confusion

  val network: Map[String, Set[String]] = addPerson(addPerson(empty, "Hari"), "Emma") // Not an ideal way
  println(network)
  val hariEmmaNetwork: Map[String, Set[String]] = friend(network, "Hari", "Emma")
  println(hariEmmaNetwork)
  println(unFriend(hariEmmaNetwork, "Hari", "Emma"))
  println(remove(hariEmmaNetwork, "Hari"))

  // Jim, Bob and Mary
  val people = addPerson(addPerson(addPerson(empty, "Jim"), "Bob"), "Mary")
  val jimBob = friend(people, "Jim", "Bob")
  val testNet = friend(jimBob, "Jim", "Mary")
  println(testNet)

  // Utilities
  def nFriends(network: Map[String, Set[String]], person: String): Int = {
    if (!network.contains(person)) 0
    else network(person).size
  }

  def mostFriends(networkApp: Map[String, Set[String]]): String = {
    networkApp.maxBy(_._2.size)._1 // maxBy is backed by a comparator
  }

  def nPeopleWithNoFriends(networkApp: Map[String, Set[String]]): Int = {
    // networkApp.view.filterKeys(networkApp(_).isEmpty).size
    // networkApp.filter(_._2.isEmpty).size
    networkApp.count(_._2.isEmpty)
  }

  def isConnected(networkApp: Map[String, Set[String]], a: String, b: String): Boolean = {
    @tailrec
    def bfs(target: String, consideredPeople: Set[String], discoveredPeople: Set[String]): Boolean = {
      if (discoveredPeople.isEmpty) false
      else {
        val person = discoveredPeople.head
        if (person == target) true
        else if (consideredPeople.contains(person)) bfs(target, consideredPeople, discoveredPeople.tail)
        else bfs(target, consideredPeople + person, discoveredPeople.tail ++ networkApp(person))
      }
    }

    bfs(b, Set(), networkApp(a) + a)
  }

  println(nFriends(testNet, "Jim"))
  println(mostFriends(testNet))
  println(nPeopleWithNoFriends(testNet))
  println(isConnected(testNet, "Jim", "Mary"))
  println(isConnected(network, "Hari", "Emma"))
}