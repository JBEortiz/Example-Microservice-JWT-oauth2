package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter{

	@Override
	public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
		resources.tokenStore(tokenStore());
	}

	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		.antMatchers(HttpMethod.POST,"/oauth/token")
		.permitAll()
		.antMatchers(HttpMethod.GET,"/person")
		.permitAll()
		.antMatchers(HttpMethod.GET,"/person/username/{username}")
		.permitAll()
		.antMatchers(HttpMethod.GET,"/person/{id}")
		.hasAnyRole("ADMIN","USER")
		.antMatchers(HttpMethod.POST,"/person/create")
		.hasRole("ADMIN")
		.antMatchers(HttpMethod.DELETE,"/person/delete/{id}")
		.hasRole("ADMIN")
		.anyRequest()
		.authenticated();
	}
	
	@Bean
	public JwtTokenStore tokenStore() {
		return new JwtTokenStore(accessTokenConverter());
	}

	@Bean
	public JwtAccessTokenConverter accessTokenConverter() {
		JwtAccessTokenConverter tokenConverter= new JwtAccessTokenConverter();
		tokenConverter.setSigningKey("hra");
		return tokenConverter;
	}
	 
	
}
