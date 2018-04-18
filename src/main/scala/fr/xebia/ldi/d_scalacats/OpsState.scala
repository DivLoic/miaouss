package fr.xebia.ldi.d_scalacats

/**
  * Created by loicmdivad.
  */
object OpsState extends App {

  import cats.data.State
  import cats.syntax.applicative._

  type CalcState[A] = State[List[Int], A]

  def evalOne(sym: String): CalcState[Int] = sym match {
    case "+" => operator(_ + _)
    case "-" => operator(_ - _)
    case "*" => operator(_ * _)
    case "/" => operator(_ / _)
    case num => operand(num.toInt)
  }

  def operator(func: (Int, Int) => Int) =
    State[List[Int], Int] {
      case a :: b :: tail =>
        val ans = func(a, b)
        (ans :: tail, ans)
      case _ =>
        sys.error("Fail!")
    }


  def operand(num: Int): CalcState[Int] =
    State[List[Int], Int] { stack =>
      (num :: stack, num)
    }

  def evalAll(input: List[String]): CalcState[Int] = input.foldLeft(0.pure[CalcState]){
    (state, symb) => state.flatMap(_ => evalOne(symb))
  }

  val program1 = for {
    _   <- evalOne("1")
    _   <- evalOne("2")
    ans <- evalOne("+")
  } yield ans

  println(program1.runA(Nil).value)

  val program2 = evalAll(List("1", "2", "+", "3", "*"))

  println(program2.runA(Nil).value)

  val program3 = for {
    _   <- evalAll(List("1", "2", "+"))
    _   <- evalAll(List("3", "4", "+"))
    ans <- evalOne("*")
  } yield ans

  println(program3.runA(Nil).value)


  def evalInput(input: String): Int = evalAll(input.split(" ").toList).runA(Nil).value

  println(evalInput("1 2 + 3 4 + *"))

}
