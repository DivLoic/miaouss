package fr.xebia.ldi.a_scalacats

/**
  * Created by loicmdivad.
  * 1.3 Exercise: Printable Library
  */
trait Printable[A] {
  def format(a: A): String
}

case class Cat(name: String, age: Int, color: String)

object PrintableInstances {

  implicit val printableInt = new Printable[Int] {
    override def format(a: Int): String = a.toString
  }

  implicit val printableString = new Printable[String] {
    override def format(a: String): String = a
  }

  implicit val printableCat = new Printable[Cat] {
    def format(cat: Cat) = {
      val name  = Printable.toFormat(cat.name)
      val age   = Printable.toFormat(cat.age)
      val color = Printable.toFormat(cat.color)
      s"$name is a $age year-old $color cat."
    }
  }

}

object Printable {

  def toFormat[A](a: A)(implicit printer: Printable[A]): String =
    printer.format(a)

  implicit class PrinterOps[A](value: A){
    def toFormat(implicit printer: Printable[A]): String =
      printer.format(value)
  }

}

object Cats extends App {

  import PrintableInstances._
  import Printable._

  val cat1 = Cat("Garfield", 38, "ginger and black")
  val cat2 = Cat("Miaouss", 14, ", a pokemon")

  println(Printable.toFormat(cat1))

  println(cat2.toFormat)

}
