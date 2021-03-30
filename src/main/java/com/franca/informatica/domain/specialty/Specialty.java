package com.franca.informatica.domain.specialty;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

@Entity
public class Specialty {
	
	@Id
	@GeneratedValue
	private Integer id;
	
	@NotEmpty(message = "A nome da especilidade é obrigatário")
	@Length(max = 120 , min = 3, message = " O tamanho da especilidade inválido")
	private String name;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
	
	

}
