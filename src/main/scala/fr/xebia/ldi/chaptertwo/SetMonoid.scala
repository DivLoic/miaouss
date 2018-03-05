package fr.xebia.ldi.chaptertwo

/**
  * Created by loicmdivad.
  */
object SetMonoid {

  implicit def MonoSet[A] = new Monoid[Set[A]] {
    def empty: Set[A] = Set()
    def combine(x: Set[A], y: Set[A]): Set[A] = x union y
  }

  trait Semigroup[A] {
    def combine(x: A, y: A): A
  }

  trait Monoid[A] extends Semigroup[A] {
    def empty: A
  }

  object Monoid {
    def apply[A](implicit monoid: Monoid[A]) =
      monoid
  }
}


