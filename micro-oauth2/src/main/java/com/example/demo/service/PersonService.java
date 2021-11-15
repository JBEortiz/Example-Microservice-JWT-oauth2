package com.example.demo.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.dao.Person;
import com.example.demo.feing.PersonFeingClient;

@Service
public class PersonService implements UserDetailsService{

	
	@Autowired
	private PersonFeingClient feingClient;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Person person= feingClient.findByUsername(username);
		
		if(person == null) {
			throw new UsernameNotFoundException("Error loging no existe el user");
		}
		
		List<GrantedAuthority> authorities = person.getRoles()
				.stream()
				.map(role->new SimpleGrantedAuthority(role.getName()))
				.peek(System.out::println)
				.collect(Collectors.toList());
		
		return new User(person.getUsername(), person.getPassword(), person.getEnabled(),true,true,true,authorities);
	}

}
