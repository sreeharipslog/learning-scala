package lectures.fp

import scala.util.Random

object Options extends App {

  /* Options are wrappers to handle NPE. Option is a sealed abstract class. Some is a case class and None is a case
  object
   */
  val nonEmpty: Option[String] = Some("This is a wrapper for String") // some requires a valid value
  val empty: Option[String] = None
  println(nonEmpty)
  println(empty)

  // Work wih unsafe APIs
  def unsafeMethod: String = null

  // val result = Some(unsafeMethod) // WRONG, NPE
  val result = Option(unsafeMethod) // Some OR None
  println(result.getOrElse("Call backup"))

  // Chained method
  def backupMethod: String = "This is a backup method"

  val chainedResult = Option(unsafeMethod).orElse(Option(backupMethod))

  // Design unsafe APIs
  def betterUnsafeMethod: Option[String] = None

  def betterBackupMethod: Option[String] = Some("This is a better backup method")

  val betterResult: Option[String] = betterUnsafeMethod orElse betterBackupMethod
  println(betterResult.getOrElse("Buggy API"))

  // Functions
  println(betterResult.isEmpty)
  println(betterResult.get) // UNSAFE never use without isEmpty. USE -> getOrElse
  // map, filter, flatmap :: all return Option
  val some20 = Some(20)
  println(some20.map(_ * 20))
  println(some20.filter(_ > 10)) // filter(x => x > 10)
  println(some20.flatMap(x => Option(x * 20)))

  // For Comprehensions :: See exercise

  //Exercise
  /**
   * Establish a connection. If established call connect method.
   */
  val config: Map[String, String] = Map(
    // fetched from else where, unsure of value presence
    "host" -> "10.119.136.12", "port" -> "80"
  )

  class Connection {
    def connect: String = "Connected"
  }

  object Connection {
    val random = new Random(System.nanoTime())

    def apply(host: String, port: String): Option[Connection] = {
      if (Random.nextBoolean()) Some(new Connection())
      else None
    }
  }

  println("<< Exercise solution >>")
  val host = config.get("host")
  val port = config.get("port")
  val connection = host.flatMap(host => port.flatMap(port => Connection.apply(host, port)))
  val connectionStatus = connection.map(_.connect)
  println(connectionStatus)
  connectionStatus.foreach(println)

  // Chained call OR Shorthand version
  config.get("host")
    .flatMap(host => config.get("port")
      .flatMap(port => Connection(host, port)))
    .map(_.connect)
    .foreach(println)

  // For comprehension version
  val forComprehendedConnection = for {
    host <- config.get("host") // given a host from right
    port <- config.get("port") // given a port from right
    connection <- Connection(host, port) // given a connection from right
  } yield connection.connect // iff host, port, connection are non null return otherwise None
  forComprehendedConnection.foreach(println)
}