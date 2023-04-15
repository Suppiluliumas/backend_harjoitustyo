package harjoitustyo.com.example.Harjoitustyo;



import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import harjoitustyo.com.example.Harjoitustyo.domain.Category;
import harjoitustyo.com.example.Harjoitustyo.domain.CategoryRepository;
import harjoitustyo.com.example.Harjoitustyo.domain.Game;
import harjoitustyo.com.example.Harjoitustyo.domain.GameRepository;
import harjoitustyo.com.example.Harjoitustyo.domain.Platform;
import harjoitustyo.com.example.Harjoitustyo.domain.PlatformRepository;
import harjoitustyo.com.example.Harjoitustyo.domain.Publisher;
import harjoitustyo.com.example.Harjoitustyo.domain.PublisherRepository;
import harjoitustyo.com.example.Harjoitustyo.domain.User;
import harjoitustyo.com.example.Harjoitustyo.domain.UserRepository;


@SpringBootApplication
public class HarjoitustyoApplication {
	private static final Logger log = LoggerFactory.getLogger(HarjoitustyoApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(HarjoitustyoApplication.class, args);
	}
		
		

		@Bean
		public CommandLineRunner demo(GameRepository gameRepository, CategoryRepository categoryRepository,
				PublisherRepository publisherRepository, UserRepository userRepository,
				PlatformRepository platformRepository) {
			return (args) -> {
				log.info("save some games");
				Category category1 = new Category("Sandbox");
				Category category2 = new Category("Real-time strategy");
				Category category3 = new Category("Shooters (FPS and TPS)");
				Category category4 = new Category("Role-playing (RPG, ARPG, and More)");
				Category category5 = new Category("Simulation and sports");

				categoryRepository.save(category1);
				categoryRepository.save(category2);
				categoryRepository.save(category3);
				categoryRepository.save(category4);
				categoryRepository.save(category5);

				Publisher publisher1 = new Publisher("Valve");
				Publisher publisher2 = new Publisher("Microsoft");
				Publisher publisher3 = new Publisher("Sony");

				publisherRepository.save(publisher1);
				publisherRepository.save(publisher2);
				publisherRepository.save(publisher3);

				Platform platform1 = new Platform("PC");
				Platform platform2 = new Platform("Playstation");
				Platform platform3 = new Platform("Xbox");

				platformRepository.save(platform1);
				platformRepository.save(platform2);
				platformRepository.save(platform3);
				
				List<Platform> gamePlatforms = new ArrayList<>();
				gamePlatforms.add(platform1);
				gamePlatforms.add(platform2);
				
				SimpleDateFormat fdate = new SimpleDateFormat("dd.MM.yyyy");
				

				Game game1 = new Game("Example Game", "This is an example game",
						fdate.parse("31.08.1992"), 59.99, category1, gamePlatforms, publisher1);
				gameRepository.save(game1);
				
				

				User user1 = new User("jesse", "$2a$10$MOmFIJrv8dUpLq2XvGRcRu5YWjzo1homIcGu78STZbbzKcHFXT1Yq", "ADMIN");
				User user2 = new User("vieras", "$2a$10$YsnJj5xnL0vCtALJGnzEn.CN4NExjFyJjNviT6HWyVToHRxeQxuHa", "USER");
				userRepository.save(user1);
				userRepository.save(user2);

				log.info("fetch all games");
				for (Game game : gameRepository.findAll()) {
					log.info(game.toString());
				}
			};
		}
	}

