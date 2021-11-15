package com.example.demo.service;

import com.example.demo.entity.Person;

public interface PersonService extends AbstractService<Person, Long>{
	
	Person put(Person entity);

	Person findByUsername(String username);

}
