package com.webapp.site.entities;

import java.io.Serializable;

import javax.persistence.*;
import javax.transaction.Transactional;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.webapp.config.FigureSerializer;

import java.util.List;


/**
 * The persistent class for the figure database table.
 * 
 */
@Entity
@JsonSerialize(using = FigureSerializer.class)
@NamedQuery(name="Figure.findAll", query="SELECT f FROM Figure f")
public class Figure implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id @Column(name="idFigure")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idFigure;

	@Lob
	private String biography;

	private String lastName;
	
	private String firstName;

	private String url;

	//bi-directional many-to-one association to Art
	@OneToMany(mappedBy="figure")
	private List<Art> arts;

	//bi-directional many-to-one association to Date
	@ManyToOne
	@JoinColumn(name="idBirthDate")
	private Date birthDate;

	//bi-directional many-to-one association to Date
	@ManyToOne
	@JoinColumn(name="idDeathDate")
	private Date deathDate;

	//bi-directional many-to-one association to Country
	@ManyToOne
	@JoinColumn(name="idCountry")
	private Country country;

	//bi-directional many-to-one association to Painting
	@OneToMany(mappedBy="figure")
	private List<Painting> paintings;

	//bi-directional many-to-one association to Sculpture
	@OneToMany(mappedBy="figure")
	private List<Sculpture> sculptures;

	//bi-directional many-to-many association to Category
	@JoinTable(name = "figure_category", joinColumns = {
	        @JoinColumn(name = "idFigure", referencedColumnName = "idFigure")}, inverseJoinColumns = {
	        @JoinColumn(name = "idCategory", referencedColumnName = "idCategory")})
	@ManyToMany
	private List<Category> categories;

	//bi-directional many-to-many association to Event
	@ManyToMany(mappedBy="figures")
	private List<Event> events;

	//bi-directional many-to-many association to Role
	@JoinTable(name = "figure_role", joinColumns = {
	        @JoinColumn(name = "idFigure", referencedColumnName = "idFigure")}, inverseJoinColumns = {
	        @JoinColumn(name = "idRole", referencedColumnName = "idRole")})
	@ManyToMany
	private List<Role> roles;
	
	//bi-directional many-to-one association to User
	@ManyToOne
		@JoinColumn(name="idUser")
	private User user;

	public Figure() {
	}

	public Long getIdFigure() {
		return this.idFigure;
	}

	public void setIdFigure(Long idFigure) {
		this.idFigure = idFigure;
	}

	public String getBiography() {
		return this.biography;
	}

	public void setBiography(String biography) {
		this.biography = biography;
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@JsonIgnore
	public List<Art> getArts() {
		return this.arts;
	}

	public void setArts(List<Art> arts) {
		this.arts = arts;
	}

	public Art addArt(Art art) {
		getArts().add(art);
		art.setFigure(this);

		return art;
	}

	public Art removeArt(Art art) {
		getArts().remove(art);
		art.setFigure(null);

		return art;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public Date getDeathDate() {
		return deathDate;
	}

	public void setDeathDate(Date deathDate) {
		this.deathDate = deathDate;
	}

	public Country getCountry() {
		return this.country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	@JsonIgnore
	public List<Painting> getPaintings() {
		return this.paintings;
	}

	public void setPaintings(List<Painting> paintings) {
		this.paintings = paintings;
	}

	public Painting addPainting(Painting painting) {
		getPaintings().add(painting);
		painting.setFigure(this);

		return painting;
	}

	public Painting removePainting(Painting painting) {
		getPaintings().remove(painting);
		painting.setFigure(null);

		return painting;
	}

	@JsonIgnore
	public List<Sculpture> getSculptures() {
		return this.sculptures;
	}

	public void setSculptures(List<Sculpture> sculptures) {
		this.sculptures = sculptures;
	}

	public Sculpture addSculpture(Sculpture sculpture) {
		getSculptures().add(sculpture);
		sculpture.setFigure(this);

		return sculpture;
	}

	public Sculpture removeSculpture(Sculpture sculpture) {
		getSculptures().remove(sculpture);
		sculpture.setFigure(null);

		return sculpture;
	}

	@JsonIgnore
	public List<Category> getCategories() {
		return this.categories;
	}

	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}

	@JsonIgnore
	public List<Event> getEvents() {
		return this.events;
	}

	public void setEvents(List<Event> events) {
		this.events = events;
	}

	@JsonIgnore
	public List<Role> getRoles() {
		return this.roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
	
	@JsonIgnore
	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String toString(){
		String szBirthDate=" ";
		String szDeathDate=" ";
		if(birthDate!=null) szBirthDate=String.valueOf(birthDate.getYear());
		if(deathDate!=null) szDeathDate=String.valueOf(deathDate.getYear());
		return firstName+" "+lastName+" ("+szBirthDate+"-"+szDeathDate+")";
	}

}