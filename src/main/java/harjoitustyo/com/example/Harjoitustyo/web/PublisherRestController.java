package harjoitustyo.com.example.Harjoitustyo.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import harjoitustyo.com.example.Harjoitustyo.domain.Publisher;
import harjoitustyo.com.example.Harjoitustyo.domain.PublisherRepository;

@RestController
public class PublisherRestController {
	
	@Autowired PublisherRepository repository;
	
	// REST find all publishers
	@RequestMapping(value = "/publishers", method = RequestMethod.GET)
	public @ResponseBody List<Publisher> getPublisherRest() {
		return (List<Publisher>) repository.findAll();
	}

	// REST find publisher by id
	@RequestMapping(value = "/publishers/{id}", method = RequestMethod.GET)
	public @ResponseBody Optional<Publisher> findPubliserRest(@PathVariable("id") long publisherId) {
		return repository.findById(publisherId);
	}

	// REST save publisher
	@RequestMapping(value = "/publishers", method = RequestMethod.POST)
	public @ResponseBody Publisher savePlatformRest(@RequestBody Publisher publisher) {
		return repository.save(publisher);
	}
}
