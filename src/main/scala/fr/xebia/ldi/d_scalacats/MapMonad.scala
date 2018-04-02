package fr.xebia.ldi.d_scalacats

import scala.language.higherKinds
/**
  * Created by loicmdivad.
  */
trait MapMonad[F[_]] {

  def pure[A](a: A): F[A]

  def flatMap[A, B](value: F[A])(func: A => F[B]): F[B]

  def map[A, B](value: F[A])(func: A => B): F[B] = flatMap(value)(v => pure(func(v)))
}