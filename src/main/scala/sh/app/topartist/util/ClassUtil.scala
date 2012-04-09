package sh.app.topartist.util

object ClassUtil {

  def getCallerClass(callDepth: java.lang.Integer): Class[_] = {
    sun.reflect.Reflection.getCallerClass(callDepth)
  }
}
