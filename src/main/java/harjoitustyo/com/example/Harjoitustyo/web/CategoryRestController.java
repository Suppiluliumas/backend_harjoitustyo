package harjoitustyo.com.example.Harjoitustyo.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import harjoitustyo.com.example.Harjoitustyo.domain.Category;
import harjoitustyo.com.example.Harjoitustyo.domain.CategoryRepository;

public class CategoryRestController {
	@Autowired CategoryRepository repository;
	//REST Find category by id
	@RequestMapping(value = "/categories/{id}", method = RequestMethod.GET)
	public @ResponseBody Optional<Category> getCategoryRest(@PathVariable("id") Long categoryId) {
		return repository.findById(categoryId);
	}
	//REST Save to categories
	@RequestMapping(value="/categories", method = RequestMethod.POST)
    public @ResponseBody Category saveCategoryRest(@RequestBody Category category) {	
    	return repository.save(category);
    }
	//REST Find all categories
	@RequestMapping(value = "/categories", method = RequestMethod.GET)
	public @ResponseBody List<Category> getCategoriesRest() {
		return (List<Category>) repository.findAll();
	}
}
