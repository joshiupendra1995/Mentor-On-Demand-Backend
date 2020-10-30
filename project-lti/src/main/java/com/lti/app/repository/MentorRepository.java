package com.lti.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.lti.app.model.Mentor;

@Repository
public interface MentorRepository extends JpaRepository<Mentor,Integer>{

	List<Mentor> findByCourseExpertiseContainingIgnoreCase(String courseName);

	Mentor findByEmailId(String emailId);

	Mentor findByEmailIdAndPassword(String emailId, String password);
}
