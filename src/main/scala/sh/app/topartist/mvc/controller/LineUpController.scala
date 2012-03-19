package sh.app.topartist.mvc.controller

import javax.faces.bean._


@ManagedBean @SessionScoped
class LineUpController extends Serializable {
	def process(): String = {
		"processedLineUp"
	}
}