package sh.app.topartist

import org.scalatest.FunSuite
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner


@RunWith(classOf[JUnitRunner])
class PerformanceTest extends FunSuite {

  test("equals(), true") {
    val p1 = new Performance("1.1", "nowhere")
    val p2 = new Performance("1.1", "nowhere")
    assert(p1 === p2)
  }

  test("equals(), false") {
    val p1 = new Performance("1.1", "nowhere")
    val p2 = new Performance("1.12", "nowhere")
    assert(!p1.eq(p2))
  }
}