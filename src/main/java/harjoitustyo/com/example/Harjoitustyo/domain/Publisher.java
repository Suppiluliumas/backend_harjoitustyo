package harjoitustyo.com.example.Harjoitustyo.domain;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Publisher {
@Id
@GeneratedValue(strategy=GenerationType.AUTO)
private Long publisherid;
private String name;

@OneToMany(cascade=CascadeType.ALL, mappedBy = "publisher")
@JsonIgnoreProperties("publisher")
private List<Game>games;

public Publisher() {
	
}

public Publisher(String name) {
	super();
	this.name = name;
}


public Long getPublisherid() {
	return publisherid;
}

public void setPublisherid(Long publisherid) {
	this.publisherid = publisherid;
}

public String getName() {
	return name;
}

public void setName(String name) {
	this.name = name;
}

public List<Game> getGames() {
	return games;
}

public void setGames(List<Game> games) {
	this.games = games;
}

@Override
public String toString() {
	return "Publisher [publisherid=" + publisherid + ", name=" + name + "]";
}

}
