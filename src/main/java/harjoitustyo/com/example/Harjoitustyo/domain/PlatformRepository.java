package harjoitustyo.com.example.Harjoitustyo.domain;

import org.springframework.data.repository.CrudRepository;

public interface PlatformRepository extends CrudRepository<Platform, Long> {
	Platform findByName(String name);
}
