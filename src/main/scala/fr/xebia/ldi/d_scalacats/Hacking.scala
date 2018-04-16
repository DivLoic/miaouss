package fr.xebia.ldi.d_scalacats

import cats.data.Reader
import cats.syntax.applicative._

/**
  * Created by loicmdivad.
  */
object Hacking extends App {

  case class Db(usernames: Map[Int, String], passwords: Map[String, String])

  type DbReader[T] = Reader[Db, T]

  def findUsername(userId: Int): DbReader[Option[String]] =
    Reader[Db, Option[String]](db => db.usernames.get(userId))

  def checkPassword(username: String, password: String): DbReader[Boolean] =
    Reader[Db, Boolean](db => db.passwords.get(username).contains(password))

  def checkLogin(userId: Int, password: String): DbReader[Boolean] = for {
    mayBeName <- findUsername(userId)
    validCredential <- mayBeName.map(checkPassword(_, password)).getOrElse(false.pure[DbReader])
  } yield validCredential

  val users = Map(
    1 -> "dade",
    2 -> "kate",
    3 -> "margo"
  )

  val passwords = Map(
    "dade"  -> "zerocool",
    "kate"  -> "acidburn",
    "margo" -> "secret"
  )

  val db = Db(users, passwords)
  println(checkLogin(1, "zerocool").run(db))
  // res10: cats.Id[Boolean] = true
  println(checkLogin(4, "davinci").run(db))
  // res11: cats.Id[Boolean] = false

}
