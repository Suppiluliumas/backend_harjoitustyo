package harjoitustyo.com.example.Harjoitustyo.domain;

import java.util.List;


import org.springframework.data.repository.CrudRepository;

public interface GameRepository extends CrudRepository<Game, Long> {
	List<Game> findByTitle(String title);
	Game getGameById(Long gameId);
	void deleteById(Long gameId);

	
	
}
