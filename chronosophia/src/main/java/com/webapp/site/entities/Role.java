package com.webapp.site.entities;

import java.io.Serializable;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

import java.util.List;


/**
 * The persistent class for the role database table.
 * 
 */
@Entity
@NamedQuery(name="Role.findAll", query="SELECT r FROM Role r")
public class Role implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@JsonSerialize(using = ToStringSerializer.class)
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idRole;
	
	private String name;

	//bi-directional many-to-many association to Figure
	@ManyToMany(mappedBy="roles")
	private List<Figure> figures;

	public Role() {
	}

	public int getIdRole() {
		return this.idRole;
	}

	public void setIdRole(int idRole) {
		this.idRole = idRole;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@JsonIgnore
	public List<Figure> getFigures() {
		return this.figures;
	}

	public void setFigures(List<Figure> figures) {
		this.figures = figures;
	}

}