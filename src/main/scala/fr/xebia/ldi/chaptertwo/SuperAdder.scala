package fr.xebia.ldi.chaptertwo

/**
  * Created by loicmdivad.
  */
object SuperAdder {

  import cats.Monoid
  import cats.syntax.monoid._
  import cats.instances.int._
  import cats.instances.option._

  def add(items: List[Int]): Int =
    items.foldLeft(Monoid[Int].empty)(_ |+| _)

  def mayBeAdd(items: List[Option[Int]]): Option[Int] =
    items.foldLeft(Monoid[Option[Int]].empty)(_ |+| _)

  def genericAdd[A: Monoid](items: List[A]): A =
    items.foldLeft(Monoid[A].empty)(_ |+| _)



}
