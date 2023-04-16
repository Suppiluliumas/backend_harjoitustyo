package harjoitustyo.com.example.Harjoitustyo.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


import harjoitustyo.com.example.Harjoitustyo.domain.SignupForm;
import harjoitustyo.com.example.Harjoitustyo.domain.User;
import harjoitustyo.com.example.Harjoitustyo.domain.UserRepository;

@CrossOrigin
@Controller
public class UserController {
	@Autowired
	private UserRepository repository;

	@RequestMapping(value = "/login")
	public String login() {
		return "login";
	}
	
	@RequestMapping(value = "/userlist", method = RequestMethod.GET)
	public String userList(Model model) {
		model.addAttribute("users", repository.findAll());
		return "userlist";
	}

	@RequestMapping(value = "/signup")
	public String addStudent(Model model) {
		model.addAttribute("signupform", new SignupForm());
		return "signup";
	}
	
	// Delete user by id
		@RequestMapping(value = "/deleteuser/{id}", method = RequestMethod.GET)
		@PreAuthorize("hasAuthority('ADMIN')")
		public String deleteUser(@PathVariable("id") long userId, Model model) {
			repository.deleteById(userId);
			return "redirect:../userlist";
		}
	@RequestMapping(value = "/saveuser", method = RequestMethod.POST)
    public String save(@ModelAttribute("signupform") SignupForm signupForm, BindingResult bindingResult) {
    	if (!bindingResult.hasErrors()) { // validation errors
    		if (signupForm.getPassword().equals(signupForm.getPasswordCheck())) { // check password match		
	    		String pwd = signupForm.getPassword();
		    	BCryptPasswordEncoder bc = new BCryptPasswordEncoder();
		    	String hashPwd = bc.encode(pwd);
	
		    	User newUser = new User();
		    	newUser.setPasswordHash(hashPwd);
		    	newUser.setUsername(signupForm.getUsername());
		    	newUser.setRole("USER");
		    	if (repository.findByUsername(signupForm.getUsername()) == null) { // Check if user exists
		    		repository.save(newUser);
		    	}
		    	else {
	    			bindingResult.rejectValue("username", "err.username", "Username already exists");    	
	    			return "signup";		    		
		    	}
    		}
    		else {
    			bindingResult.rejectValue("passwordCheck", "err.passCheck", "Passwords does not match");    	
    			return "signup";
    		}
    	}
    	else {
    		return "signup";
    	}
    	return "redirect:/userlist";    	
    }    
    
}

