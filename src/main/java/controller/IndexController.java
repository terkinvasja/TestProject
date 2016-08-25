package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("usersajax")
public class IndexController {

	  @RequestMapping(method = RequestMethod.GET)
	    public String getIndexPage() {
	        return "usersajax";
	    }

}