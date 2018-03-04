package com.webapp.site.entities;

import java.io.Serializable;

import javax.persistence.*;

import java.util.List;


/**
 * The persistent class for the user database table.
 * 
 */
@Entity
@NamedQuery(name="User.findAll", query="SELECT u FROM User u")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idUser;

	private String login;

	private String password;
	
	//bi-directional many-to-one association to Event
		@OneToMany(mappedBy="user")
	private List<Event> events;
		
	//bi-directional many-to-one association to Figure
		@OneToMany(mappedBy="user")
	private List<Figure> figures;
		
	//bi-directional many-to-one association to Art
		@OneToMany(mappedBy="user")
	private List<Art> arts;

	//bi-directional many-to-one association to Almanac
	@OneToMany(mappedBy="user")
	private List<Almanac> almanacs;

	//bi-directional many-to-one association to Chronology
	@OneToMany(mappedBy="user")
	private List<Chronology> chronologies;

	//bi-directional many-to-one association to Profile
	@ManyToOne
	@JoinColumn(name="idProfile")
	private Profile profile;

	public User() {
	}

	public Long getIdUser() {
		return this.idUser;
	}

	public void setIdUser(Long idUser) {
		this.idUser = idUser;
	}

	public String getLogin() {
		return this.login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Almanac> getAlmanacs() {
		return this.almanacs;
	}

	public void setAlmanacs(List<Almanac> almanacs) {
		this.almanacs = almanacs;
	}

	public Almanac addAlmanac(Almanac almanac) {
		getAlmanacs().add(almanac);
		almanac.setUser(this);

		return almanac;
	}

	public Almanac removeAlmanac(Almanac almanac) {
		getAlmanacs().remove(almanac);
		almanac.setUser(null);

		return almanac;
	}

	public List<Chronology> getChronologies() {
		return this.chronologies;
	}

	public void setChronologies(List<Chronology> chronologies) {
		this.chronologies = chronologies;
	}

	public Chronology addChronology(Chronology chronology) {
		getChronologies().add(chronology);
		chronology.setUser(this);

		return chronology;
	}

	public Chronology removeChronology(Chronology chronology) {
		getChronologies().remove(chronology);
		chronology.setUser(null);

		return chronology;
	}

	public Profile getProfile() {
		return this.profile;
	}

	public void setProfile(Profile profile) {
		this.profile = profile;
	}

	public List<Event> getEvents() {
		return events;
	}

	public void setEvents(List<Event> events) {
		this.events = events;
	}

	public List<Figure> getFigures() {
		return figures;
	}

	public void setFigures(List<Figure> figures) {
		this.figures = figures;
	}

	public List<Art> getArts() {
		return arts;
	}

	public void setArts(List<Art> arts) {
		this.arts = arts;
	}
	
	public Art addArt(Art art) {
		getArts().add(art);
		art.setUser(this);

		return art;
	}

	public Art removeArt(Art art) {
		getArts().remove(art);
		art.setUser(null);

		return art;
	}
	
	public Event addEvent(Event event) {
		getEvents().add(event);
		event.setUser(this);

		return event;
	}

	public Event removeEvent(Event event) {
		getEvents().remove(event);
		event.setUser(null);

		return event;
	}
	
	public Figure addFigure(Figure figure) {
		getFigures().add(figure);
		figure.setUser(this);

		return figure;
	}

	public Figure removeFigure(Figure figure) {
		getFigures().remove(figure);
		figure.setUser(null);

		return figure;
	}
	
}