package harjoitustyo.com.example.Harjoitustyo.domain;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@Entity
public class Platform {
@Id
@GeneratedValue(strategy=GenerationType.AUTO)
private Long platformid;
@NotEmpty
private String name;
@JsonIgnore
@ManyToMany(mappedBy = "platforms")
private List<Game> games = new ArrayList<>();

public Platform() {
	
}
public Platform(String name) {
	this.name = name;
}

public Long getPlatformid() {
	return platformid;
}


public void setPlatformid(Long platformid) {
	this.platformid = platformid;
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
	return name;
}


}
