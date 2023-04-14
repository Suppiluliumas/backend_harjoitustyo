package harjoitustyo.com.example.Harjoitustyo;

import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
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
	
	@Test //Test for findByTitle() - methods functionality
	public void findByTitleShouldReturnGame() {
		List<Game> games = repository.findByTitle("Example Game");
		
		assertThat(games).hasSize(1);
		assertThat(games.get(0).getDescription()).isEqualTo("This is an example game");
	}
	
}
