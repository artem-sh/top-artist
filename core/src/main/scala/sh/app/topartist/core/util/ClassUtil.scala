package sh.app.topartist.core.util

object ClassUtil {

  def getCallerClass(callDepth: java.lang.Integer): Class[_] = {
    sun.reflect.Reflection.getCallerClass(callDepth)
  }
}
