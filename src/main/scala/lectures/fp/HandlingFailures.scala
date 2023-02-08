package fp

import scala.util.{Failure, Random, Success, Try}

object HandlingFailures extends App {

  // Create Success and Failure
  val aSuccess = Success(7)
  val aFailure = Failure(new RuntimeException("Super Failure")) // failure holds a throwable
  println(aSuccess)
  println(aFailure)

  // Unsafe APIs
  def unsafeMethod(): String = throw new RuntimeException("Failed. Step up")

  val potentialFailure: Try[String] = Try(unsafeMethod())
  potentialFailure.foreach(println)

  // Syntactic sugar
  val anotherPotentialFailure = Try {
    println("This computation is failing")
    throw new RuntimeException("Another potential failure")
  }
  println(anotherPotentialFailure)

  // Utility methods
  println(aFailure.isSuccess)
  println(aSuccess.isFailure)

  // orElse
  def backupMethod(): String = "Backup method"

  val fallbackTry = Try(unsafeMethod()).orElse(Try(backupMethod()))

  // If designing APIs: Wrap result in Try
  def betterUnsafeMethod(): Try[String] = Failure(new RuntimeException("Better Failure"))

  def betterBackupMethod(): Try[String] = Success("Hurray! you succeeded")

  val betterFallbackTry = betterUnsafeMethod() orElse betterBackupMethod()
  println(betterFallbackTry)

  // map, flatMap and filter
  aSuccess.map(_ * 20).foreach(println)
  // aFailure.flatMap(x => Success(x)).foreach(println)
  aSuccess.flatMap(x => Success(x * 35)).foreach(println)
  println(aSuccess.filter(_ % 2 == 0)) // filter can transform success to failure

  // For Comprehensions :: See exercise

  // Exercise
  val host = "hostName"
  val port = "8080"

  def renderHTML(page: String): Unit = println(page)

  class Connection {
    private val random = new Random(System.nanoTime())

    def get(url: String): String = {
      if (random.nextBoolean()) "<html>...</html>"
      else throw new RuntimeException("Connection interrupted. Try again")
    }

    def getSafe(url: String): Try[String] = Try(get(url))
  }

  object HttpService {
    val random = new Random(System.nanoTime())

    def getConnection(host: String, port: String): Connection = {
      if (random.nextBoolean()) new Connection
      else throw new RuntimeException("Port is used by another process")
    }

    def getSafeConnection(host: String, port: String): Try[Connection] = Try(getConnection(host, port))
  }

  // Q: If your get an HTML page from connection, print it to console. i.e. call renderHTML

  println("<< Exercise Solutions >>")
  // Normal version
  val possibleConnection = HttpService.getSafeConnection(host, port)
  val possiblePage = possibleConnection.flatMap(conn => conn.getSafe("/home"))
  possiblePage.foreach(renderHTML)

  // Shorthand version
  HttpService.getSafeConnection(host, port)
    .flatMap(con => con.getSafe("/home"))
    .foreach(renderHTML)
  // For Comprehension version
  for {
    connection <- HttpService.getSafeConnection(host, port) // for a successful connection
    page <- connection.getSafe("/home") // for a successful page using a successful connection
  } renderHTML(page) // render the successful page

  // In an Imperative programming language above for comprehension will be equivalent to::
  /*
  try{
    connection = HttpService.getConnection(..)
    try {
      page = connection.get(..)
      renderHTML(page)
    } catch(some other exception){
    .....
    }
  } catch (Exception e){
    .....
  }
  and so on
   */


}