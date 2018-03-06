package fr.xebia.ldi.chaptertwo

/**
  * Created by loicmdivad.
  */
object SuperAdder extends App {

  import cats.Monoid
  import cats.instances.int._
  import cats.syntax.monoid._

  def add(items: List[Int]): Int =
    items.foldLeft(Monoid[Int].empty)(_ |+| _)

  def generalAdd[A](items: List[A])(implicit monoid: Monoid[A]): A =
    items.foldLeft(monoid.empty)(_ |+| _)

  def genericAdd[A: Monoid](items: List[A]): A =
    items.foldLeft(Monoid.empty)(_ |+| _)

  case class Order(totalCost: Double, quantity: Double)

  implicit val monoOrder = new Monoid[Order] {
    override def empty: Order = Order(0, 0)
    override def combine(x: Order, y: Order): Order = Order(
      x.totalCost + y.totalCost,
      x.quantity + y.quantity
    )
  }
}

