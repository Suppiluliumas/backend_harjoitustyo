package harjoitustyo.com.example.Harjoitustyo.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import harjoitustyo.com.example.Harjoitustyo.domain.CategoryRepository;
import harjoitustyo.com.example.Harjoitustyo.domain.Game;
import harjoitustyo.com.example.Harjoitustyo.domain.GameRepository;
import harjoitustyo.com.example.Harjoitustyo.domain.PlatformRepository;
import harjoitustyo.com.example.Harjoitustyo.domain.PublisherRepository;

@CrossOrigin
@Controller
public class GameController {
	@Autowired
	private GameRepository gameRepository;
	@Autowired
	private CategoryRepository categoryRepository;
	@Autowired
	private PlatformRepository platformRepository;
	@Autowired
	private PublisherRepository publisherRepository;

	@RequestMapping(value = "/gamelist", method = RequestMethod.GET)
	public String gameList(Model model) {
		model.addAttribute("games", gameRepository.findAll());
		return "gamelist";
	}

	@RequestMapping(value = "/games", method = RequestMethod.GET)
	public @ResponseBody List<Game> getGamesRest() {
		return (List<Game>) gameRepository.findAll();
	}

	@RequestMapping(value = "/games/{id}", method = RequestMethod.GET)
	public @ResponseBody Optional<Game> findGameRest(@PathVariable("id") long gameId) {
		return gameRepository.findById(gameId);
	}

	@RequestMapping(value = "/games", method = RequestMethod.POST)
	public @ResponseBody Game saveGameRest(@RequestBody Game game) {
		return gameRepository.save(game);
	}


	@RequestMapping(value = "/add")
	@PreAuthorize("hasAuthority('ADMIN')")
	public String addGame(Model model) {
		model.addAttribute("game", new Game());
		model.addAttribute("categories", categoryRepository.findAll());
		model.addAttribute("platforms", platformRepository.findAll());
		model.addAttribute("publishers", publisherRepository.findAll());
		return "addgame";
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	@PreAuthorize("hasAuthority('ADMIN')")
	public String save(Game game) {
		gameRepository.save(game);
		return "redirect:gamelist";
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	@PreAuthorize("hasAuthority('ADMIN')")
	public String deleteGame(@PathVariable("id") long gameId, Model model) {
		gameRepository.deleteById(gameId);
		return "redirect:../gamelist";
	}

	@RequestMapping(value = "/editgame/{id}", method = RequestMethod.GET)
	@PreAuthorize("hasAuthority('ADMIN')")
	public String editGame(@PathVariable("id") Long gameId, Model model) {
		Game game = gameRepository.getGameById(gameId);
		model.addAttribute("categories", categoryRepository.findAll());
		model.addAttribute("platforms", platformRepository.findAll());
		model.addAttribute("publishers", publisherRepository.findAll());
		model.addAttribute("game", game);
		return "editgame";
	}
}
