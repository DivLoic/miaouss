package fr.xebia.ldi.chapterone

/**
  * Created by loicmdivad.
  * 1.5.5 Exercise: Equality, Liberty, and Felinity
  */
object Equality extends App {

  import cats.Eq
  import cats.syntax.eq._
  import cats.instances.option._

  final case class Cat(name: String, age: Int, color: String)

  implicit val catEq = Eq.instance[Cat](
    (cat1, cat2) => cat1 == cat2
  )

  val cat1 = Cat("Garfield",   38, "orange and black")
  val cat2 = Cat("Heathcliff", 33, "orange and black")

  val maybeCat1 = Option(cat1)
  val maybeCat2 = Option.empty[Cat]

  println("cat1 === cat1 is %s".format(cat1 === cat1))
  println("cat1 === cat2 is %s".format(cat1 === cat2))
  println("maybeCat1 === maybeCat2 is %s".format(maybeCat1 === maybeCat2))

}
