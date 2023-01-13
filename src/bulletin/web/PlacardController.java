package bulletin.web;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import bulletin.Placard;
import bulletin.data.PlacardDao;

@Controller
@RequestMapping("/placards")
public class PlacardController {

	public static final int DEFAULT_MAX_ID = 99999;
	
//	@Autowired
	private PlacardDao placardDao;

	@Autowired
	public PlacardController(PlacardDao placardDao) {
		this.placardDao = placardDao;
	}

	@RequestMapping(method = RequestMethod.GET)
	public String placards1(Model model) {
		model.addAttribute(placardDao.findPlacards(DEFAULT_MAX_ID));//List<Placard>
		return "placards";
	}

	//@RequestMapping(method = RequestMethod.GET)
	public String placards2(Model model) {
		model.addAttribute("placardList", placardDao.findPlacards(DEFAULT_MAX_ID));
		return "placards";
	}
	
	//@RequestMapping(method = RequestMethod.GET)
	public String placards3(Map<String, Object> model) {
		model.put("placardList", placardDao.findPlacards(DEFAULT_MAX_ID));
		return "placards";
	}
	
	//@RequestMapping(method=RequestMethod.GET)
	public List<Placard> placards4() {
		return placardDao.findPlacards(DEFAULT_MAX_ID);
	}

	//@RequestMapping(method = RequestMethod.GET)
	public List<Placard> placards5(
			@RequestParam(value = "max", defaultValue = "99999") int max) {
		List<Placard> ps = placardDao.findPlacards(max);
		return ps;
	}


	@RequestMapping(value = "/show", method = RequestMethod.GET)
	public String showPlacard1(@RequestParam("placard_id") int placardId, Model model) {
		model.addAttribute(placardDao.findBy(placardId));
		return "placard";
	}

	@RequestMapping(value = "/{placardId}", method = RequestMethod.GET)
	public String showPlacard2(@PathVariable("placardId") int id, Model model) {
		model.addAttribute(placardDao.findBy(id));
		return "placard";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String savePlacard(Placard form) throws Exception {
		placardDao.save(new Placard(null, form.getMessage(), new Date()));
		return "redirect:/placards";
	}

}
