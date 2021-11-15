package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Person;
import com.example.demo.service.PersonService;

@RestController
public class PersonController {
	
	@Autowired
	private PersonService service;
	
	@GetMapping("/person")
	public ResponseEntity<List<Person>> listar() {
		List<Person> person = service.getAll();
		if (person.isEmpty()) {
			throw new EntityNotFoundException("la lista de usuarios esta vacia");
		}
		return ResponseEntity.ok(person);
	}

	@GetMapping("/person/{id}")
	public ResponseEntity<Person> findById(@PathVariable Long id) {
		Person person = service.getById(id);
		return ResponseEntity.ok(person);

	}

	@GetMapping("/person/username/{username}")
	public ResponseEntity<Person> findByUsername(@PathVariable String username) {
		Person person = service.findByUsername(username);
		return ResponseEntity.ok(person);

	}

	@PostMapping("/person/create")
	public ResponseEntity<Boolean> create(@RequestBody Person user) {
		return ResponseEntity.ok(service.save(user));
	}
	
	@DeleteMapping("/person/delete/{id}")
	public ResponseEntity<Boolean> delete(@PathVariable Long id) {
		return ResponseEntity.ok(service.delete(id));
	}



}
