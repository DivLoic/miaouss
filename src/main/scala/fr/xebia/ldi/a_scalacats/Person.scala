package fr.xebia.ldi.a_scalacats

import JsonSyntax._
import JsonWriterInstances._

/**
  * Created by loicmdivad.
  */
final case class Person(name: String, email: String)

object Person extends App {

  val foo = Person("foo", "foo@exemple.com")
  val bar = Person("bar", "bar@exemple.com")
  val baz = Person("baz", "foo@exemple.com")

  println(foo.toJson)

  println(JsonObject.toJson(bar))

}