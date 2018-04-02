package fr.xebia.ldi.c_scalacats

/**
  * Created by loicmdivad.
  * 3.6.1.1 Exercise: Showing off with Contramap
  */
trait C2Printable[A] {
  self =>
  def format(value: A): String
  def contramap[B](func: B => A): C2Printable[B] = new C2Printable[B] {
    def format(value: B): String = self.format(func(value))
  }
}

object C2PrintableInstances {

  implicit val stringC2Printable: C2Printable[String] = new C2Printable[String] {
      def format(value: String): String = "\"" + value + "\""
    }

  implicit val booleanC2Printable: C2Printable[Boolean] = new C2Printable[Boolean] {
    def format(value: Boolean): String =
      if(value) "yes" else "no"
  }
}


object Contramap extends App {

  import C2PrintableInstances._

  def format[A](a: A)(implicit printer: C2Printable[A]): String = printer.format(a)

  println(format("hello"))
  println(format(true))
}


