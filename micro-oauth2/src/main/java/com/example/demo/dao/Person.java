package com.example.demo.dao;

import java.util.List;

import lombok.Data;

@Data
public class Person {

	private Long id;

	private String username;

	private String password;

	private Boolean enabled;
	
	private String name;
	
	private String lastname;

	private List<Role> roles;
}
