package sh.topartist

class Performance(when: String, where: String) {
  val _when = when
  val _where = where

  override def equals(other: Any): Boolean =
    other match {
      case that: Performance =>
        (that.isInstanceOf[Performance]) &&
          _when == that._when &&
          _where == that._where
      case _ => false
    }

  override def hashCode: Int = 13 + _when.hashCode() + _where.hashCode()

  override def toString = "Performance:: when: " + _when + ", where: " + _where
}