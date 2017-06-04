package com.nefilim.nitro

import scala.annotation.tailrec

/**
  * Created by peter.vanrensburg on 5/31/17.
  */
object Josephus extends App {
  sealed trait Solver
  case object RecursiveSolver extends Solver
  case object TailRecursiveSolver extends Solver

  def solve(n: Int, k: Int, solver: Solver = TailRecursiveSolver): Int = {
    @tailrec
    def go_tail(i: Int, k: Int, accumulator: Int): Int = {
      i match {
        case 0 => accumulator
        case _ => go_tail(i - 1, k, ((accumulator + k - 1) % (n - i + 1)) + 1)
      }
    }

    def go(i: Int, k: Int): Int = {
      i match {
        case 1 => 1
        case _ => ((go(i - 1, k) + k - 1) % i) + 1
      }
    }

    if (n < 1 || k < 1)
      return 0

    solver match {
      case TailRecursiveSolver => go_tail(n, k, 1)
      case RecursiveSolver => go(n, k)
    }
  }

  if (args.length > 1) {
    val n = args(0).toInt
    val k = args(1).toInt
    solve(n, k)
  } else
    println("Usage: sbt run n k")
}
