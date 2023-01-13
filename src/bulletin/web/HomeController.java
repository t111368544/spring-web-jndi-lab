package bulletin.web;

import static org.springframework.web.bind.annotation.RequestMethod.*;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
/*
public class HomeController {

	@RequestMapping(value = {"/", "/homepage"}, method = GET)
	public String home() {
		return "home";
	}

}
*/


@RequestMapping({"/", "/homepage"})
public class HomeController {

	@RequestMapping(method = GET)
	public String home() {
		return "home";
	}

}
