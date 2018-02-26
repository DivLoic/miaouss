package fr.xebia.ldi.chapterone

import cats.Show

/**
  * Created by loicmdivad.
  */
trait Showable {

  implicit val catShow: Show[Cat] = Show.show(

    cat => s"${cat.name} is a ${cat.age} year-old ${cat.color} cat."
  )
}


object Showable extends App {

  import cats.Show
  import cats.instances.int._    // for Show
  import cats.instances.string._ // for Show
  import cats.syntax.show._

  val cat = Cat("Garfield", 38, "ginger and black")


}