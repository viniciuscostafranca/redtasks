package com.franca.informatica.domain.user;

import java.util.List;

public interface AppUserService {
	
	void save(AppUser cliente) throws Exception;

	void delete(Integer id) throws Exception;

	void update(Integer id, AppUser cliente) throws Exception;
	
	List<AppUser> find(Integer pageNo, Integer pageSize, AppUser filtro) throws Exception;

	AppUser getClientById(Integer id) throws Exception;
}
