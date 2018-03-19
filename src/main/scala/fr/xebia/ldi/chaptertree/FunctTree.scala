package fr.xebia.ldi.chaptertree

import cats.Functor
import cats.syntax.functor._
import fr.xebia.ldi.chaptertree.FunctTree.{Branch, Leaf, Tree}

import scala.annotation.tailrec

/**
  * Created by loicmdivad.
  */
object FunctTree extends App {

  sealed trait Tree[+A]

  final case class Branch[A](left: Tree[A], right: Tree[A]) extends Tree[A]

  final case class Leaf[A](value: A) extends Tree[A]

  @tailrec
  implicit val treeFonctor: Functor[Tree] = new Functor[Tree] {
    override def map[A, B](fa: Tree[A])(f: A => B): Tree[B] = fa match {
      case Branch(left, right) => Branch(map(left)(f), map(right)(f))
      case Leaf(value) => Leaf(f(value))
    }
  }

  def rMapping(word: String) = word.split("-").lastOption.map(_.toInt).getOrElse(0)

  val tree1 = Tree.branch(Leaf("R-1"), Branch(Leaf("R-21"), Leaf("R-22")))
  val branch1 = Tree.branch(Tree.leaf("R-21"), Tree.leaf("R-22"))
  val leaf1 = Tree.leaf("R-1")

  println(tree1)
  println(treeFonctor.map(tree1)(rMapping))
  println(tree1.map(rMapping))

  println(branch1)
  println(treeFonctor.map(branch1)(rMapping))
  println(branch1.map(rMapping))

  println(leaf1)
  println(treeFonctor.map(leaf1)(rMapping))
  print(leaf1.map(rMapping))
}


object Tree {

  def branch[A](left: Tree[A], right: Tree[A]): Tree[A] = Branch(left, right)

  def leaf[A](value: A): Tree[A] = Leaf(value)
}