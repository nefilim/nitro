package com.nefilim.nitro

/**
  * Created by peter.vanrensburg on 5/31/17.
  */
object Josephus extends App {

  def go(n: Int, k: Int): Int = {
    if (n < 1 || k < 1)
      0
    else if (n == 1)
      1
    else {
      ((go(n - 1, k) + k - 1) % n) + 1
    }
  }

  if (args.length > 1) {
    val n = args(0).toInt
    val k = args(1).toInt
    println(go(n, k))
  } else
    println("Usage: sbt run n k")
}
