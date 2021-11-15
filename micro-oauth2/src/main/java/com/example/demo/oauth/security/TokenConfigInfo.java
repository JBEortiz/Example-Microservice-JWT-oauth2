package com.example.demo.oauth.security;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Component;

import com.example.demo.dao.Person;
import com.example.demo.feing.PersonFeingClient;

@Component
public class TokenConfigInfo implements TokenEnhancer{
	
	@Autowired
	private PersonFeingClient feingClient;
	
	@Override
	public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
		Map<String, Object> info= new HashMap<String,Object>();
		Person person= feingClient.findByUsername(authentication.getName());
		info.put("name", person.getName());
		info.put("lastname", person.getLastname());
		((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(info);
		return accessToken;
	}

}
