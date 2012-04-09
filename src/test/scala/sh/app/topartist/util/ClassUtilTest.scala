package sh.app.topartist.util

import ClassUtil._
import org.scalatest.FunSuite


class ClassUtilTest extends FunSuite {
  test("getCallerClass()") {
    assert(getCallerClass(1).getName.contains(ClassUtil.getClass.getName))
    assert(getCallerClass(2).getName.contains(this.getClass.getName))
  }
}