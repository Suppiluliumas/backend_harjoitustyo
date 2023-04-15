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

import harjoitustyo.com.example.Harjoitustyo.domain.Game;
import harjoitustyo.com.example.Harjoitustyo.domain.GameRepository;

@CrossOrigin
@RestController
public class GameRestController {
	@Autowired
	GameRepository repository;

	// REST find all games
	@RequestMapping(value = "/games", method = RequestMethod.GET)
	public @ResponseBody List<Game> getGamesRest() {
		return (List<Game>) repository.findAll();
	}
	// REST find game by id
	@RequestMapping(value = "/games/{id}", method = RequestMethod.GET)
	public @ResponseBody Optional<Game> findGameRest(@PathVariable("id") long gameId) {
		return repository.findById(gameId);
	}
	//REST save game
	@RequestMapping(value = "/games", method = RequestMethod.POST)
	public @ResponseBody Game saveGameRest(@RequestBody Game game) {
		return repository.save(game);
	}
}
