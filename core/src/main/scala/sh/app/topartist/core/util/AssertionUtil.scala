package sh.app.topartist.core.util

object AssertionUtil {

  def checkParamIsNotNull(obj: AnyRef) {
    if (obj == null) throw new NullPointerException
  }
}