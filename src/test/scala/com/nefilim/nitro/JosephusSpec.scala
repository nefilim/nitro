package com.nefilim.nitro

import org.scalatest.{FlatSpec, Matchers}
import Josephus._

/**
  * Created by peter.vanrensburg on 5/31/17.
  */
class JosephusSpec extends FlatSpec with Matchers {

  "k=2" should "match known solution" in {
    val solution = List[Int](1, 1, 3, 1, 3, 5, 7, 1, 3, 5, 7, 9, 11, 13, 15, 1)
    (1 to 16).map(go(_, 2)) should be (solution)
  }

  "Invalid parameters" should "return 0" in {
    go(-1, 1) should be (0)
    go(1, 0) should be (0)
    go(5, -2) should be (0)
  }

  "f(k, k) " should "never be k" in {
    (2 to 1024).foreach { i =>
      go(i, i) should not be (i)
    }
  }

  "n = 3 and k = 2" should "work" in {
    go(3, 2) should be (3)
  }

  "n is very large but k is 2" should "throw a StackOverflowException" in {
    an [StackOverflowError] should be thrownBy go(1024 * 1024, 2)
  }

}
