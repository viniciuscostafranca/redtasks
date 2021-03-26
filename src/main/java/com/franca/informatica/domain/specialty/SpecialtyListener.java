package com.franca.informatica.domain.specialty;

import javax.persistence.EntityNotFoundException;
import javax.persistence.PrePersist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.franca.informatica.domain.user.AppUser;
import com.franca.informatica.domain.user.AppUserRepository;
/*
@Component
public class SpecialtyListener {

	private static AppUserRepository appUserRepository;
	
	@PrePersist
	public void onPrePersistHandler(Specialty task) {
		if (task. == null) {
			String username = SecurityContextHolder.getContext().getAuthentication().getName();
			AppUser appUser = appUserRepository.findByUsername(username);
			
			if (appUser == null) {
				throw new EntityNotFoundException("O usuário " + username + " não foi encontrado");
			}
			
			task.setAppUser(appUser);
		}
	}
	
	@Autowired
	public void init(AppUserRepository appUserRepository) {
		SpecialtyListener.appUserRepository = appUserRepository;
	}
}
*/