package sh.topartist.util


object AssertionUtil {

  def checkParamIsNotNull(obj: AnyRef) {
    if (obj == null) throw new NullPointerException
  }
}