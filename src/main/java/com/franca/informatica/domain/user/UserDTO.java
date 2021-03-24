package com.franca.informatica.domain.user;

import java.util.Set;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.franca.informatica.domain.role.Role;

public class UserDTO {
	
	private String username;
	private String displayName;
	private Set<Role> roles;
	private String token;
	
	

	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getDisplayName() {
		return displayName;
	}
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
	public Set<Role> getRoles() {
		return roles;
	}
	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public static String buildToJSon(String username, String displayName, String token) throws JsonProcessingException {
		
		UserDTO userDto =new UserDTO();
		userDto.setDisplayName(displayName);
		userDto.setUsername(username);
		userDto.setToken(token);
		
		return new ObjectMapper().writeValueAsString(userDto);
	}
	
	
	
}
