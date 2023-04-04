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
public class Platform {
@Id
@GeneratedValue(strategy=GenerationType.AUTO)
private Long platformid;
private String name;

@OneToMany(cascade = CascadeType.ALL, mappedBy = "platform")
@JsonIgnoreProperties("platform")
private List<Game> games;

public Platform() {
	
}

public Platform(String name) {
	super();
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
	return "Platform [platformid=" + platformid + ", name=" + name + "]";
}


}
