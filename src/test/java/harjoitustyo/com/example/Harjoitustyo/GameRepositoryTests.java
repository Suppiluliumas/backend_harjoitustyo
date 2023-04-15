package harjoitustyo.com.example.Harjoitustyo;

import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import harjoitustyo.com.example.Harjoitustyo.domain.Game;
import harjoitustyo.com.example.Harjoitustyo.domain.GameRepository;


@ExtendWith(SpringExtension.class)
@DataJpaTest
public class GameRepositoryTests {

	@Autowired
	private GameRepository repository;


	@Test // Test for findByTitle() - methods functionality
	public void findByTitleShouldReturnGame() {
		List<Game> games = repository.findByTitle("Example Game");

		assertThat(games).hasSize(1);
		assertThat(games.get(0).getDescription()).isEqualTo("This is an example game");
	}

	@Test // Test for saving Game and deleting
	public void testDeleteById() {

		// Save a game
		Game game = new Game();
		game.setTitle("Test Game");
		game.setDescription("Test");
		game.setPlatforms(null);
		game.setPrice(5);
		repository.save(game);

		// Delete the game by ID
		repository.deleteById(game.getId());

		// Verify that the game no longer exists
		assertFalse(repository.existsById(game.getId()));
	}

	@Test // Test for saving Game
	public void testSaveGame() {

		// Save a game
		Game game = new Game();
		game.setTitle("Test Game");
		game.setDescription("Test");
		game.setPlatforms(null);
		game.setPrice(5);
		repository.save(game);

		assertThat(game.getId()).isNotNull();
	}
}
