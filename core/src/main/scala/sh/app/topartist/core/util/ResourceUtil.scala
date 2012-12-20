package sh.app.topartist.core.util

import io.Source


object ResourceUtil {
  def resourceNextToMe(fileName: String): Source = {
    Source.fromURL(ClassUtil.getCallerClass(3).getResource(fileName), "UTF-8")
  }
}