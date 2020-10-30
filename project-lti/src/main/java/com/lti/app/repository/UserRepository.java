package com.lti.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lti.app.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
	
	User findByFirstNameAndLastName(String firstName,String lastName);
	
	User findByEmailId(String emailId);

	User findByEmailIdAndPassword(String emailId, String password);

}
