package harjoitustyo.com.example.Harjoitustyo.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import harjoitustyo.com.example.Harjoitustyo.domain.Platform;
import harjoitustyo.com.example.Harjoitustyo.domain.PlatformRepository;
@CrossOrigin
@RestController
public class PlatformRestController {
	@Autowired
	PlatformRepository repository;

	// REST find all platforms
	@RequestMapping(value = "/platforms", method = RequestMethod.GET)
	public @ResponseBody List<Platform> getPlatformsRest() {
		return (List<Platform>) repository.findAll();
	}

	// REST find platform by id
	@RequestMapping(value = "/platforms/{id}", method = RequestMethod.GET)
	public @ResponseBody Optional<Platform> findPlatformRest(@PathVariable("id") long platformId) {
		return repository.findById(platformId);
	}

	// REST save platform
	@RequestMapping(value = "/platforms", method = RequestMethod.POST)
	public @ResponseBody Platform savePlatformRest(@RequestBody Platform platform) {
		return repository.save(platform);
	}
}
