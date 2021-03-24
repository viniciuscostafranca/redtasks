package com.franca.informatica.domain.user;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AppUserServiceImpl implements AppUserService {

	@Autowired
	private AppUserRepository repository;

	@Override
	public AppUser getClientById(Integer id) throws NotFoundAppUserException {
		if (id != null) {
			AppUser cliente = repository.findById(id)
					.orElseThrow(() -> new NotFoundAppUserException("Usuário não encontrado"));
			return (cliente);
		}
		return null;
	}

	@Override
	public void save(AppUser cliente) throws Exception {
		if (cliente.getUsername() == null) {
			throw new NotFoundAppUserException("Usuário não encontrado");
		}

		if (repository.findByUsername(cliente.getUsername()) == null) {
			PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
			cliente.setPassword(encoder.encode(cliente.getPassword()));
			repository.save(cliente);
		} else {
			throw new DuplicateAppUserException(" Nome do usuário já usado, favor tentar outro!");
		}
	}

	@Override
	public void delete(Integer id) throws NotFoundAppUserException {
		if (id != null) {
			repository.findById(id).map(clienteExistente -> {
				repository.delete(clienteExistente);
				return clienteExistente;
			}).orElseThrow(() -> new NotFoundAppUserException("Usuário não encontrado"));
		}
	}

	@Override
	public void update(Integer id, AppUser cliente) throws NotFoundAppUserException {
		if (id != null) {
			repository.findById(id).map(clienteExistente -> {
				// cliente.setId(clienteExistente.getId());
				repository.save(cliente);
				return clienteExistente;
			}).orElseThrow(() -> new NotFoundAppUserException("Usuário não encontrado"));
		}

	}

	@Override
	public List<AppUser> find(Integer pageNo, Integer pageSize, AppUser filtro) {
		ExampleMatcher matcher = ExampleMatcher.matching().withIgnoreCase()
				.withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
		Example example = Example.of(filtro, matcher);

		Page<AppUser> pagedResult = repository.findAll(example, PageRequest.of(pageNo, pageSize));
		if (pagedResult.hasContent()) {

			return (pagedResult.getContent());
		} else {
			return Collections.emptyList();
		}

	}
}
