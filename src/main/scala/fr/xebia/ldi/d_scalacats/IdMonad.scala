package fr.xebia.ldi.d_scalacats

/**
  * Created by loicmdivad.
  */
object IdMonad extends App {

  type IdMonad[A] = A

  def pure[A](value: A): IdMonad[A] = value

  def map[A, B](value: IdMonad[A])(func: A => B): IdMonad[B] = func(value)

  def flatMap[A, B](value: IdMonad[A])(func: A => IdMonad[B]): IdMonad[B] = func(value)

  println(s"pure(123) -> ${pure(123)}")

  println(s"map(123)(_ * 2) -> ${map(123)(_ * 2)}")

  println(s"flatMap(123)(_ * 2) -> ${flatMap(123)(_ * 2)}")
}