package harjoitustyo.com.example.Harjoitustyo.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import harjoitustyo.com.example.Harjoitustyo.domain.Platform;
import harjoitustyo.com.example.Harjoitustyo.domain.PlatformRepository;
import jakarta.validation.Valid;

@CrossOrigin
@Controller
public class PlatformController {
	@Autowired
	private PlatformRepository prepository;

	// Get all platforms
	@RequestMapping(value = "/platformlist", method = RequestMethod.GET)
	public String platformList(Model model) {
		model.addAttribute("platforms", prepository.findAll());
		return "platformlist";
	}

	// Add new platform
	@RequestMapping(value = "/addplatform", method = RequestMethod.GET)
	public String addPlatform(Model model) {
		model.addAttribute("platform", new Platform());
		return "addplatform";
	}

	// Save platform
	@RequestMapping(value = "/saveplatform", method = RequestMethod.POST)
	public String save(@Valid Platform platform, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "addplatform";
		}
		prepository.save(platform);
		return "redirect:platformlist";
	}

	// Delete platform
	@RequestMapping(value = "/deleteplatform/{id}", method = RequestMethod.GET)
	public String deletePlatfrom(@PathVariable("id") long platformId, Model model) {
		prepository.deleteById(platformId);
		return "redirect:../platformlist";
	}
}
