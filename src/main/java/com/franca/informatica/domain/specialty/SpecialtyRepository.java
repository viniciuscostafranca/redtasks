package com.franca.informatica.domain.specialty;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@Repository
@RepositoryRestResource( path = "specialty",collectionResourceRel = "specialtys" )
public interface SpecialtyRepository extends PagingAndSortingRepository<Specialty, Integer> {

	Specialty findByName(String name);
	/*
	@Override
	@Query("SELECT t FROM Task t WHERE t.appUser.username = ?#{principal}")
	Page<Specialty> findAll(Pageable pageable);
	
	@Override
	@Query("SELECT t FROM Task t WHERE t.id = ?1 AND t.appUser.username = ?#{principal}")
	Optional<Specialty> findById(Integer id);
	*/
}
