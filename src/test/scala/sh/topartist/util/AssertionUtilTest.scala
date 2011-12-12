package sh.topartist.util

import org.scalatest.FunSuite
import sh.topartist.util.AssertionUtil.checkParamIsNotNull

class AssertionUtilTest extends FunSuite {

  test("checkParamIsNotNull()") {
    checkParamIsNotNull("qw")

    intercept[NullPointerException] {
      checkParamIsNotNull(null)
    }
  }
}