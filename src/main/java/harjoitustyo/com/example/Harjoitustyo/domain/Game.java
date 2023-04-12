package harjoitustyo.com.example.Harjoitustyo.domain;


import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Game {
@Id
@GeneratedValue(strategy=GenerationType.AUTO)
private long id;
private String title;
private String description;
@Basic
private java.sql.Date released;
private double price;

@JsonIgnore
@ManyToOne
@JoinColumn(name = "categoryid")
private Category category;
@JsonIgnore
@ManyToOne
@JoinColumn(name ="platformid")
private Platform platform;
@JsonIgnore
@ManyToOne
@JoinColumn(name = "publisherid")
private Publisher publisher;

public Game() {
	
}

public Game(String title, String description, java.sql.Date released, double price, Category category,
		Platform platform, Publisher publisher) {
	super();
	this.title = title;
	this.description = description;
	this.released = released;
	this.price = price;
	this.category = category;
	this.platform = platform;
	this.publisher = publisher;
}

public long getId() {
	return id;
}

public void setId(long id) {
	this.id = id;
}

public String getTitle() {
	return title;
}

public void setTitle(String title) {
	this.title = title;
}

public String getDescription() {
	return description;
}

public void setDescription(String description) {
	this.description = description;
}

public java.sql.Date getReleased() {
	return released;
}

public void setReleased(java.sql.Date released) {
	this.released = released;
}


public double getPrice() {
	return price;
}

public void setPrice(double price) {
	this.price = price;
}

public Category getCategory() {
	return category;
}

public void setCategory(Category category) {
	this.category = category;
}

public Platform getPlatform() {
	return platform;
}

public void setPlatform(Platform platform) {
	this.platform = platform;
}

public Publisher getPublisher() {
	return publisher;
}

public void setPublisher(Publisher publisher) {
	this.publisher = publisher;
}

@Override
public String toString() {
	if (this.category != null ||  this.platform != null ||  this.publisher != null)
		return "Game [id=" + id + ", title=" + title + ", description=" + description + ", released=" + released
			+ ", price=" + price + ", category=" + this.getCategory() + ", platform=" + this.getPlatform()
			+ ", publisher=" + this.getPublisher() + "]";
	else 
		return "Game [id=" + id + ", title=" + title + ", description=" + description + ", released=" + released
				+ ", price=" + price +  "]";
			
}


}
