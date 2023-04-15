package harjoitustyo.com.example.Harjoitustyo;


import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import harjoitustyo.com.example.Harjoitustyo.domain.Category;
import harjoitustyo.com.example.Harjoitustyo.domain.CategoryRepository;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class CategoryRepositoryTests {
	
	@Autowired CategoryRepository repository;
	
	@Test // Test for findById() - method
	public void findByNameShouldReturnCategory() {
		List<Category> categories = repository.findByName("Sandbox");
		
			assertThat(categories).hasSize(1);
			assertThat(categories.get(0).getCategoryid()).isEqualTo(1);
	}
	
	@Test // Test for saving Category and deleting
	public void testDeleteById() {
		//Save category
		Category category = new Category();
		category.setName("Test category");
		
		repository.save(category);
		
		// Delete the category by ID
		repository.deleteById(category.getCategoryid());
		
		//Verify that the category no longer exists
		assertFalse(repository.existsById(category.getCategoryid()));
		
	}
	
	@Test // Test for saving category
	public void testSaveCategory() {
		
		//Save Category
		Category category = new Category();
		category.setName("Test category");
		repository.save(category);
		
		assertThat(category.getCategoryid()).isNotNull();
	}
}
