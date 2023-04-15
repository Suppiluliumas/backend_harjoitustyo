package harjoitustyo.com.example.Harjoitustyo.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import harjoitustyo.com.example.Harjoitustyo.domain.Category;
import harjoitustyo.com.example.Harjoitustyo.domain.CategoryRepository;
import harjoitustyo.com.example.Harjoitustyo.domain.Game;
import jakarta.validation.Valid;
@CrossOrigin
@Controller
public class CategoryController {
	@Autowired
	private CategoryRepository repository;

	// Get list of categories
	@RequestMapping(value = "/categorylist", method = RequestMethod.GET)
	public String categoryList(Model model) {
		model.addAttribute("categories", repository.findAll());
		return "categorylist";
	}
	
	// Add new category
	@RequestMapping(value = "/addcategory")
	public String addCategory(Model model) {
		model.addAttribute("category", new Category());
		return "addcategory";
	}
	// Save category
	@RequestMapping(value = "/savecategory", method = RequestMethod.POST)
	public String save(@Valid Category category, BindingResult bindingResult) {
	    if (bindingResult.hasErrors()) {
	        return "addcategory";
	    }
	    repository.save(category);
	    return "redirect:categorylist";
	}
	
	// Delete category by id
	@RequestMapping(value = "/deletecategory/{id}", method = RequestMethod.GET)
	@PreAuthorize("hasAuthority('ADMIN')")
	public String deleteCategory(@PathVariable("id") long categoryid, Model model) {
		repository.deleteById(categoryid);
		return "redirect:../categorylist";
	}
}
