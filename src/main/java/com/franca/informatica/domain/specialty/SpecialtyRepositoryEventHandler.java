package com.franca.informatica.domain.specialty;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.annotation.HandleBeforeCreate;
import org.springframework.data.rest.core.annotation.HandleBeforeSave;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.stereotype.Component;


@Component
@RepositoryEventHandler(Specialty.class)
public class SpecialtyRepositoryEventHandler {

	private SpecialtyRepository specialtyRepository;

	@Autowired
	public SpecialtyRepositoryEventHandler(SpecialtyRepository specialtyRepository) {
		this.specialtyRepository = specialtyRepository;
	}
	
	@HandleBeforeSave
	@HandleBeforeCreate
	public void handle(Specialty specialty) throws DuplicateSpecialtyException {
		
		Specialty specialtyDB = specialtyRepository.findByName(specialty.getName());
		boolean duplicate = false;
		
		if (specialtyDB != null) {
			if ((specialty.getId() == null || specialty.getId() == 0) && specialty.getName().equals(specialtyDB.getName())) {
				duplicate = true;
			}
			
			if (specialty.getId() != null && specialty.getId() > 0 && !specialty.getId().equals(specialtyDB.getId())) {
				duplicate = true;
			}
		}
		
		if (duplicate) {
			throw new DuplicateSpecialtyException("Já existe uma especilidade com este nome");
		}
	}
}