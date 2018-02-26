package fr.xebia.ldi.chapterone

/**
  * Created by loicmdivad.
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

  implicit val printablecat = new Printable[Cat] {
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

  val cat = Cat("Garfield", 38, "ginger and black")
  // cat: Cat = Cat(Garfield,38,ginger and black)

  Printable.toFormat(cat)
  // Garfield is a 38 year-old ginger and black cat.

  println(cat.toFormat)
}