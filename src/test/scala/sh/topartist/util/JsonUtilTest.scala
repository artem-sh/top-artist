package sh.topartist.util

import org.scalatest.FunSuite;
import sh.topartist.util.JsonUtil._
import net.liftweb.json.JsonAST.{JInt, JString}


class JsonUtilTest extends FunSuite {
  test("extractStringValue()") {
    assert("testMe" === extractStringValue(JString("testMe")))
    assert("123" === extractStringValue(JInt(123)))
  }

  test("extractIntValue()") {
    assert(-143 === extractIntValue(JString("-143")))
    assert(143 === extractIntValue(JInt(143)))
    intercept[NumberFormatException] {extractIntValue(JString(""))}
  }
}