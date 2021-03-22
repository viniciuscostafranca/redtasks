package com.franca.informatica.domain.task;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@Repository
@RepositoryRestResource(exported = true)
public interface TaskRepository extends PagingAndSortingRepository<Task, Integer> {

Task findByDescription(String description);
	
	@Override
	@Query("SELECT t FROM Task t WHERE t.appUser.username = ?#{principal}")
	Page<Task> findAll(Pageable pageable);
	
	@Override
	@Query("SELECT t FROM Task t WHERE t.id = ?1 AND t.appUser.username = ?#{principal}")
	Optional<Task> findById(Integer id);
}
