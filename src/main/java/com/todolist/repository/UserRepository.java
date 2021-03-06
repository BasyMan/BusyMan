package com.todolist.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.todolist.model.User;

public interface UserRepository extends PagingAndSortingRepository<User, Long> {
	
	@Query("FROM User WHERE userName=:username")
	User findByUsername(@Param("username") String username);
	
	@Query("FROM User")
	List<User> userList();

	User findByEmailIgnoreCase(String emailId);
}
