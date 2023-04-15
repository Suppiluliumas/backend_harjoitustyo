package harjoitustyo.com.example.Harjoitustyo.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import harjoitustyo.com.example.Harjoitustyo.domain.Game;
import harjoitustyo.com.example.Harjoitustyo.domain.Publisher;
import harjoitustyo.com.example.Harjoitustyo.domain.PublisherRepository;
import jakarta.validation.Valid;

@Controller
public class PublisherController {
	@Autowired
	private PublisherRepository pubrepository;

	@RequestMapping(value = "/publisherlist", method = RequestMethod.GET)
	public String publisherList(Model model) {
		model.addAttribute("publishers", pubrepository.findAll());
		return "publisherlist";
	}

	@RequestMapping(value = "/addpublisher")
	@PreAuthorize("hasAuthority('ADMIN')")
	public String addPublisher(Model model) {
		model.addAttribute("publisher", new Publisher());
		return "addpublisher";
	}

	@RequestMapping(value = "/savepublisher", method = RequestMethod.POST)
	@PreAuthorize("hasAuthority('ADMIN')")
	public String save(@Valid Publisher publisher, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "addpublisher";
		}
		pubrepository.save(publisher);
		return "redirect:publisherlist";
	}

	@RequestMapping(value = "/deletepublisher/{id}", method = RequestMethod.GET)
	@PreAuthorize("hasAuthority('ADMIN')")
	public String deletePublisher(@PathVariable("id") long publisherid, Model model) {
		pubrepository.deleteById(publisherid);
		return "redirect:../publisherlist";
	}
}
