package com.example.demo.feing;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.demo.dao.Person;

@FeignClient(name="micro-demo")
public interface PersonFeingClient {
	
	@GetMapping("/person/username/{username}")
	public Person findByUsername(@PathVariable String username);

}
