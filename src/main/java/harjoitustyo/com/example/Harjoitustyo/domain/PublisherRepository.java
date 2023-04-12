package harjoitustyo.com.example.Harjoitustyo.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface PublisherRepository extends CrudRepository<Publisher, Long> {
	List<Publisher> findByName(String name);
}
