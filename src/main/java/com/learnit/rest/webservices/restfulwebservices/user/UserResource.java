package com.learnit.rest.webservices.restfulwebservices.user;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;
import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;



@RestController
public class UserResource {

	@Autowired
	private UserDaoService service;
	
	//GET /users
	// retreiveAllUsers
	@GetMapping("/users")
	public List<User> retrieveAllUsers(){
		return service.findAll();
	}
	
	//Get users/{id}
	//ret user (int id),
	@GetMapping("users/{id}")
	public Resource<User> retrieveUser(@PathVariable int id) {
		
		User u =service.findOne(id);
		if(u ==null) {
			throw new UserNotFoundException("id-" + id);
		}
		
		Resource<User> resource = new Resource<User>(u);
		
		//using STATIC 
		// linkataan noita polkuja
		ControllerLinkBuilder link = linkTo(methodOn(this.getClass()).retrieveAllUsers());
		
		resource.add(link.withRel("all-users"));
		
		return resource;
		//return service.findOne(id);
	}
	
	//CREATED
	//käyttäjän detalji
	//ouput - CREATED ja palauttaa luodun URI:n
	
	/**
	 * @param user
	 * @returns
	 */
	@PostMapping("/users")
	public ResponseEntity<Object> createUser(@Valid @RequestBody User user) {
		User savedUser = service.save(user);
		//CREATED
		//user/{idß}
		//otetaan ID savedUserista ja lisätään pathiin ja toURI()
		
		
		URI location =ServletUriComponentsBuilder
		.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId()).toUri();
		return ResponseEntity.created(location).build();
	}
	
	@DeleteMapping("users/{id}")
	public void deleteUser(@PathVariable int id) {
		
		User u =service.deleteById(id);
		
		if(u ==null) {
			throw new UserNotFoundException("id-" + id);
		}
		
	}
	
	
}
