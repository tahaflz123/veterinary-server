package com.veterinaryserver.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.veterinaryserver.entity.animal.Animal;

@Repository
public interface AnimalRepository extends JpaRepository<Animal, Long>{

	
	@Query(value = "SELECT a FROM Animal a WHERE lower(a.name) like lower(concat('%', :q, '%')) OR "
			+ "lower(a.user.username) like lower(concat('%', :q, '%')) OR "
			+ "lower(a.user.name) like lower(concat('%', :q, '%')) or lower(a.user.surname) like "
			+ "lower(concat('%', :q,'%'))")
	List<Animal> findAnimalsByNameOrUserNameOrUserSurname(String q);

	List<Animal> findAllByUserId(Long id);


}
