package sh.app.topartist.core.util


object CollectionsUtil {

  def addValueToListInMap[K, V](map: java.util.Map[K, java.util.List[V]], key: K, value: V) {
    if (map.get(key) == null) {
      val values = new java.util.ArrayList[V]()
      values.add(value)
      map.put(key, values)
    } else
      map.get(key).add(value)
  }
}