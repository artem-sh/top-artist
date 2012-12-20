package sh.app.topartist.core

class Config {
  private val properties = new java.util.Properties()
  properties.load(getClass.getResourceAsStream("/top-artist.properties"))

  def property(key: String) = properties.getProperty(key)
}