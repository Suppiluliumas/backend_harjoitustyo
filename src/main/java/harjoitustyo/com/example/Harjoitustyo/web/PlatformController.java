package harjoitustyo.com.example.Harjoitustyo.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import harjoitustyo.com.example.Harjoitustyo.domain.Platform;
import harjoitustyo.com.example.Harjoitustyo.domain.PlatformRepository;


@Controller
public class PlatformController {
	@Autowired
	private PlatformRepository prepository;

	@RequestMapping(value = "/platformlist", method = RequestMethod.GET)
	public String platformList(Model model) {
		model.addAttribute("platforms", prepository.findAll());
		return "platformlist";
	}

	@RequestMapping(value = "/platforms", method = RequestMethod.GET)
	public @ResponseBody List<Platform> getPlatformsRest() {
		return (List<Platform>) prepository.findAll();
	}

	@RequestMapping(value = "/platforms/{id}", method = RequestMethod.GET)
	public @ResponseBody Optional<Platform> getPlatformRest(@PathVariable("id") Long platformId) {
		return prepository.findById(platformId);
	}

	@RequestMapping(value = "/platforms", method = RequestMethod.POST)
	public @ResponseBody Platform savePlatformRest(@RequestBody Platform platform) {
		return prepository.save(platform);
	}

	@RequestMapping(value = "/addplatform", method = RequestMethod.GET)
	public String addPlatform(Model model) {
		model.addAttribute("platform", new Platform());
		return "addplatform";
	}

	@RequestMapping(value = "/saveplatform", method = RequestMethod.POST)
	public String save(Platform platform) {
		prepository.save(platform);
		return "redirect:platformlist";
	}

	@RequestMapping(value = "/deleteplatform/{id}", method = RequestMethod.GET)
	public String deletePlatfrom(@PathVariable("id") long platformId, Model model) {
		prepository.deleteById(platformId);
		return "redirect:../platformlist";
	}
}
