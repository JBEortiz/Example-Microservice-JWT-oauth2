package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
@EnableFeignClients
@EnableEurekaClient
@SpringBootApplication
public class MicroOauth2Application implements CommandLineRunner{

	@Autowired
	private BCryptPasswordEncoder b;
	public static void main(String[] args) {
		SpringApplication.run(MicroOauth2Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		String pass="1234";
		
		for (int i = 0; i < 6; i++) {
			String passEncoder=b.encode(pass);
			System.out.println(passEncoder);
		}
		
	}

}
