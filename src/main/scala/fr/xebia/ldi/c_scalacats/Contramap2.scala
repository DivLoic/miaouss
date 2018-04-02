package fr.xebia.ldi.c_scalacats

/**
  * Created by loicmdivad.
  * 3.6.1.2 Exercise: Showing off with Contramap
  */
trait C3Printable[A] {
  def format(value: A): String
  def contramap[B](func: B => A): C3Printable[B] = (value: B) => format(func(value))
}

object C3PrintableInstances {

  implicit val stringC3Printable: C3Printable[String] = (value: String) => "\"" + value + "\""

  implicit val booleanC3Printable: C3Printable[Boolean] = (value: Boolean) => if (value) "yes" else "no"
}

final case class Box[A](value: A)

object Box {
  implicit def boxC3Printable[A](implicit printer: C3Printable[A]) = printer.contramap[Box[A]](_.value)
}

object Contramap2 extends App {

  import C3PrintableInstances._

  def format[A](a: A)(implicit printer: C3Printable[A]): String = printer.format(a)

  println(s"Pure printed value: ${format("hello")}" )
  println(s"Pure printed value: ${format(true)}" )
  println("\n")
  println(s"Value printed from the box: ${format(Box("hello world"))}")
  println(s"Value printed from the box: ${format(Box(true))}")
}