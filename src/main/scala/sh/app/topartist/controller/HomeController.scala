package sh.app.topartist.controller

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
class HomeController {

  @RequestMapping(value = Array("/"))
  def home(): String = {
    System.out.println("HomeController: Passing through...");
    "WEB-INF/views/home.jsp";
  }
}
