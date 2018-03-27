package fr.xebia.ldi.chapterone

/**
  * Created by loicmdivad.
  * 1.4.6 Exercise: Cat Show
  */
object Showable extends App {

  import cats.Show
  import cats.syntax.show._

  implicit val catShow: Show[Cat] = Show.show(
    cat => s"${cat.name} is a ${cat.age} year-old ${cat.color} cat."
  )

  val cat = Cat("Garfield", 38, "ginger and black")

  println(catShow.show(cat))

  println(cat.show)

}



