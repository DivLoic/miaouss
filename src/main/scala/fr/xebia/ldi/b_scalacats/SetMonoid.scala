package fr.xebia.ldi.b_scalacats

/**
  * Created by loicmdivad.
  */
object SetMonoid {

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

  implicit def setUnion[A] = new Monoid[Set[A]] {
    def empty: Set[A] = Set()
    def combine(x: Set[A], y: Set[A]): Set[A] = x union y
  }

  implicit def setIntersect[A] = new Monoid[Set[A]] {
    def empty: Set[A] = Set()
    def combine(x: Set[A], y: Set[A]): Set[A] = x intersect y
  }

  implicit def setSsymDiff[A] = new Monoid[Set[A]] {
    override def empty: Set[A] = Set()
    override def combine(x: Set[A], y: Set[A]): Set[A] =
      (x diff y) union (y diff x)
  }
}


