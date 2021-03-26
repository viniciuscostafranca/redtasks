package com.franca.informatica.domain.user;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.franca.informatica.domain.role.Role;

@Entity
@Table(name = "app_user")
public class AppUser {

	@Id
	@GeneratedValue
	private Integer id;

	@NotEmpty(message = "O nome de usuário é obrigatório")
	private String username;

	@NotEmpty(message = "A senha é obrigatória")
	private String password;

	private String displayName;

	@JsonProperty("firstName")
	@NotEmpty(message = "O Primeiro Nome  é obrigatório")
	private String firstName;
	
	@JsonProperty("lastName")
	@NotEmpty(message = "O Último Nome é obrigatório")
	private String lastName;
	
	@ManyToMany
	private Set<Role> roles;

	public AppUser() {
	}

	public AppUser(String username, String password, String displayName) {
		this.username = username;
		this.password = password;
		this.displayName = displayName;
	}

	public AppUser(String username, String displayName) {
		super();
		this.username = username;
		this.displayName = displayName;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public Integer getId() {
		return id;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

}