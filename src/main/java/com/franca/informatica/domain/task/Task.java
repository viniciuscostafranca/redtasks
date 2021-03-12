package com.franca.informatica.domain.task;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.franca.informatica.domain.user.AppUser;

@Entity
public class Task {
	
	@Id
	@GeneratedValue
	private Integer id;
	
	@NotEmpty(message = "A descrição da tarefa é obrigatória")
	@Length(max = 40 , min = 3, message = " O tamanho da  tarefa é inválido")
	private String description;
	
	@NotNull(message = "A data da tarefa é obrigatório")
	@FutureOrPresent(message = " A data da tarefa não pode estar no passado")
	private LocalDate whentoDo;
	
	private Boolean done =false;
	
	@ManyToOne
	@JoinColumn(name="app_user_id")
	//@NotNull(message = "O usuário da tarefa é obrigatorio")
	private AppUser appUser;
	
	public Task( String description, LocalDate whentoDo, Boolean done) {
		super();
	
		this.description = description;
		this.whentoDo = whentoDo;
		this.done = done;
	}
	public Task() {
		super();
	}
	public Integer getId() {
		return id;
	}
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public LocalDate getWhentoDo() {
		return whentoDo;
	}
	public void setWhentoDo(LocalDate whentoDo) {
		this.whentoDo = whentoDo;
	}
	public Boolean getDone() {
		return done;
	}
	public void setDone(Boolean done) {
		this.done = done;
	}
	public AppUser getAppUser() {
		return appUser;
	}
	public void setAppUser(AppUser appUser) {
		this.appUser = appUser;
	}
	
	

}
