package com.franca.informatica.domain.user;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "app_user")
public class AppUser {
	@Id
	@GeneratedValue
	private Integer id;
	@NotEmpty(message =" O nome de usuário é obrigatório")
	private String username;
	@NotEmpty(message =" O senha é obrigatório")
	private String password;
	@NotEmpty(message =" O nome de exibição é obrigatório")
	private String displayName;
	
	

	public AppUser(String username, String password, String displayName) {
		super();
		this.username = username;
		this.password = password;
		this.displayName = displayName;
	}

	public AppUser() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public String getDisplayNam() {
		return displayName;
	}

	public void setDisplayNam(String displayNam) {
		this.displayName = displayNam;
	}	
	
	

}
