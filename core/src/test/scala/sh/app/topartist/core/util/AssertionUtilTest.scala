package sh.app.topartist.core.util

import org.scalatest.FunSuite
import sh.app.topartist.core.util.AssertionUtil.checkParamIsNotNull
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner


@RunWith(classOf[JUnitRunner])
class AssertionUtilTest extends FunSuite {
  test("checkParamIsNotNull()") {
    checkParamIsNotNull("qw")

    intercept[NullPointerException] {
      checkParamIsNotNull(null)
    }
  }
}