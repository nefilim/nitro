package com.nefilim.nitro

import org.scalatest.{FlatSpec, Matchers}
import Josephus._

/**
  * Created by peter.vanrensburg on 5/31/17.
  */
class JosephusSpec extends FlatSpec with Matchers {

  "k=2" should "match known solution" in {
    val solution = List[Int](1, 1, 3, 1, 3, 5, 7, 1, 3, 5, 7, 9, 11, 13, 15, 1)
    (1 to 16).map(solve(_, 2, TailRecursiveSolver)) should be (solution)
    (1 to 16).map(solve(_, 2, RecursiveSolver)) should be (solution)
  }

  "Invalid parameters" should "return 0" in {
    solve(-1, 1) should be (0)
    solve(0, 0) should be (0)
    solve(1, 0) should be (0)
    solve(5, -2) should be (0)
  }

  "f(k, k) " should "never be k" in {
    (2 to 1024).foreach { i =>
      solve(i, i) should not be (i)
    }
  }

  "n = 3 and k = 2" should "work" in {
    solve(3, 2) should be (3)
  }

  "n is very large but k is 2 for non tail recursive" should "throw a StackOverflowException" in {
    an [StackOverflowError] should be thrownBy solve(1024 * 1024, 2, RecursiveSolver)
  }

  "n is very large but k is 2 for tail recursive" should "NOT throw a StackOverflowException" in {
    noException should be thrownBy solve(1024 * 1024, 2, TailRecursiveSolver)
  }
}
