package sh.app.topartist.util

import org.scalatest.FunSuite
import sh.app.topartist.util.AssertionUtil.checkParamIsNotNull


class AssertionUtilTest extends FunSuite {

  test("checkParamIsNotNull()") {
    checkParamIsNotNull("qw")

    intercept[NullPointerException] {
      checkParamIsNotNull(null)
    }
  }
}