package harjoitustyo.com.example.Harjoitustyo.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import harjoitustyo.com.example.Harjoitustyo.domain.Category;
import harjoitustyo.com.example.Harjoitustyo.domain.CategoryRepository;



@Controller
public class CategoryController {
	@Autowired
	private CategoryRepository repository;
	
	@RequestMapping(value="/categorylist", method= RequestMethod.GET)
	public String categoryList(Model model) {
		model.addAttribute("categories", repository.findAll());
		return "categorylist";
	}
	@RequestMapping(value="/addcategory")
	public String addBook(Model model) {
		model.addAttribute("category", new Category());
		return "addcategory";
}
	@RequestMapping(value= "/savecategory", method = RequestMethod.POST)
	public String save(Category category) {
		repository.save(category);
		return "redirect:categorylist";
	}
}
