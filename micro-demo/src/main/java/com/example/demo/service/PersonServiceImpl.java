package com.example.demo.service;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Person;
import com.example.demo.repository.PersonRepository;
@Service
public class PersonServiceImpl extends AbstractServiceImpl<Person, Long> implements PersonService {
	
	@Autowired
	private PersonRepository repository;
	
	@Override
	public Person put(Person entity) {
		
		Person entityUpdate = repository
						.findById(entity.getId())
						.orElseThrow(EntityNotFoundException::new);
		
		entityUpdate.setLastname(entity.getLastname());
		entityUpdate.setName(entity.getName());
		
		repository.save(entityUpdate);
		
		return entityUpdate;
	}

	@Override
	public Person findByUsername(String username) {
		return repository
				.findByUsername(username)
				.orElseThrow(EntityNotFoundException::new);
	}

}
