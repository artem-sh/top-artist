package sh.topartist.util.raisin

/**
 * See http://naturalsoftware.blogspot.com/2009/06/more-scala-using-raisin.html
 */

trait Disposable {
  def dispose()
}

object Raisin {
  def using[T <% Disposable](resource: T)(block: T => Unit) {
    try {
      block(resource)
    }
    finally {
      resource.dispose()
    }
  }

  def using[T <% Disposable, U <% Disposable] (resource1: T, resource2: => U)(block: (T, U) => Unit) {
    try {
      try {
        block(resource1, resource2)
      }
      finally {
        resource2.dispose()
      }
    }
    finally {
      resource1.dispose()
    }
  }
}