package com.learnit.rest.webservices.restfulwebservices.user;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

//import ctrl shift O
@ApiModel(description="Kaikki käyttäjän tiedot")
@Entity 
public class User {
	
	@Id
	@GeneratedValue
	private Integer id; 
	
	//viesti jos ehto ei toteudu nimeä annettaessa
	@Size(min=2, message="name should have atleast 2 characters")
	private String name;

	// importti : ctrl shift O
	// Tässä tämmönen property määritys
	@Past
	@ApiModelProperty(notes="Syntymäaika oltava menneisyydessä")
	private Date birthDate;
	
	//TÄRKEÄ:  tämä mappedBy pitää olla se
	// mikä POST-luokassa on fieldin nimi
	// joka siis on 'kirjoittaja'
	//ensimmäinen sana One viittaa aina tähän luokkaan ja
	// toinen Many viittaa siihen mitä esitellään eli List
	@OneToMany(mappedBy="kirjoittaja")
	private List<Post> posts;
	
	protected User() {
		super();
		
	}

	
	public List<Post> getPosts() {
		return posts;
	}


	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}


	public User(Integer id, String name, Date date) {
		super();
		this.id = id;
		this.name = name;
		this.birthDate = date;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", birthDate=" + birthDate + "]";
	}
	
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}
	
}
