package harjoitustyo.com.example.Harjoitustyo.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Basic;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.PositiveOrZero;


@Entity
public class Game {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	@NotEmpty (message = "Games title cannot be empty.")
	private String title;
	@NotEmpty(message = "Please add something to describe the game.")
	private String description;

	@Basic
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date released;
	@PositiveOrZero(message = "No negatives please.")
	private double price;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "categoryid")
	private Category category;
	@JsonIgnore
	@ManyToMany
	@JoinTable(name = "game_platforms", joinColumns = @JoinColumn(name = "game_id"), inverseJoinColumns = @JoinColumn(name = "platform_id"))
	private List<Platform> platforms = new ArrayList<>();
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "publisherid")
	private Publisher publisher;

	public Game() {

	}

	public Game(String title, String description, Date released, double price, Category category,
			List<Platform> platforms, Publisher publisher) {
		super();
		this.title = title;
		this.description = description;
		this.released = released;
		this.price = price;
		this.category = category;
		this.platforms = platforms;
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

	public Date getReleased() {
		return released;
	}

	public void setReleased(Date released) {
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

	public List<Platform> getPlatforms() {
		return platforms;
	}

	public void setPlatforms(List<Platform> platforms) {
		this.platforms = platforms;
	}

	public Publisher getPublisher() {
		return publisher;
	}

	public void setPublisher(Publisher publisher) {
		this.publisher = publisher;
	}

	@Override
	public String toString() {
		if (this.category != null || this.platforms != null || this.publisher != null)
			return "Game [id=" + id + ", title=" + title + ", description=" + description + ", released=" + released
					+ ", price=" + price + ", category=" + this.getCategory() + ", publisher=" + this.getPublisher()
					+ "]";
		else
			return "Game [id=" + id + ", title=" + title + ", description=" + description + ", released=" + released
					+ ", price=" + price + "]";

	}

}
