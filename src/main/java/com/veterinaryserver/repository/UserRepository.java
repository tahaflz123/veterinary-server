package com.veterinaryserver.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.veterinaryserver.entity.user.User;

public interface UserRepository extends JpaRepository<User, Long>{

	Optional<User> findByUsername(String username);

	@Query("SELECT u FROM User u WHERE lower(u.name) like lower(concat('%', :q, '%')) OR "
			+ "lower(u.surname) like lower(concat('%', :q, '%')) OR "
			+ "lower(u.username) like lower(concat('%', :q, ',%'))")
	List<User> searchAll(String q);

	Boolean existsByUsername(String username);


}
