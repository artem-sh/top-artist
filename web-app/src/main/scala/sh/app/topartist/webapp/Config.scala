package sh.app.topartist.webapp

class Config {
  private val properties = new java.util.Properties()
  properties.load(getClass.getResourceAsStream("/top-artist.properties"))

  def property(key: String) = properties.getProperty(key)
}