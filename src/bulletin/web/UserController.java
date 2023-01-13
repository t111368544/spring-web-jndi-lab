package bulletin.web;

import static org.springframework.web.bind.annotation.RequestMethod.*;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import bulletin.User;
import bulletin.UserForm;
import bulletin.data.UserDao;

@Controller
@RequestMapping("/user")
public class UserController {

	private UserDao userDao;

	@Autowired
	public UserController(UserDao userDao) {
		this.userDao = userDao;
	}

	@RequestMapping(value = "/register", method = GET)
	public String showRegistrationForm() {
		return "registerForm";
	}

	@RequestMapping(value = "/register", method = POST)	//without validation
	public String _processRegistration(UserForm user) {	
		userDao.save(user);
		return "redirect:/user/" + user.getUsername();
	}

	//@RequestMapping(value = "/register", method = POST)	//with validation
	public String processRegistration(@Valid User user, Errors errors) {
		if (errors.hasErrors()) {
			return "registerForm";
		}
		userDao.save(user);
		return "redirect:/user/" + user.getUsername();
	}

	@RequestMapping(value = "/{username}", method = GET)
	public String showUserProfile(@PathVariable String username, Model model) {
		User user = userDao.findByUsername(username);
		model.addAttribute(user);
		return "profile";
	}

}
