package sh.app.topartist.util

import ResourceUtil._
import org.scalatest.FunSuite
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner


@RunWith(classOf[JUnitRunner])
class ResourceUtilTest extends FunSuite {
  test("resourceNextToMe(), no such file") {
    intercept[NullPointerException] {resourceNextToMe("dumb")}
  }

  test("resourceNextToMe") {
    assert("dumb!" === resourceNextToMe("dumb_resource.txt").getLines().next())
  }
}
