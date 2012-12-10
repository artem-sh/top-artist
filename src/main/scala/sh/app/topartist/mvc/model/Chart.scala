package sh.app.topartist.mvc.model

import reflect.BeanProperty


class Chart(_dataProvider: String, _unitName: String, _artist1: String, _artist1Rating: Int, _artist2: String, _artist2Rating: Int) {
  @BeanProperty val dataProvider = _dataProvider
  @BeanProperty val unitName = _unitName
  @BeanProperty val artist1 = _artist1
  @BeanProperty val artist1Rating = _artist1Rating
  @BeanProperty val artist2 = _artist2
  @BeanProperty val artist2Rating = _artist2Rating
}