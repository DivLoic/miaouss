package fr.xebia.ldi.d_scalacats

import cats.Eval

/**
  * Created by loicmdivad.
  */
object StackSafe {

  def foldRight[A, B](as: List[A], acc: B)(fn: (A, B) => B): B = as match {
    case head :: tail =>
      fn(head, foldRight(tail, acc)(fn))
    case Nil =>
      acc
  }

  def safeFoldRight[A, B](as: List[A], acc: Eval[B])(fn: (A, Eval[B]) => Eval[B]): Eval[B] = as match {
    case head :: tail =>
      Eval.defer(fn(head, foldRight(tail, acc)(fn)))
    case Nil =>
      acc
  }

  def foldRightEval[A, B](as: List[A], acc: B)(fn: (A, B) => B): B =
    safeFoldRight(as, Eval.now(acc)) { (a, b) =>
      b.map(fn(a, _))
    }.value
}