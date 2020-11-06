package com.lti.app.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.lti.app.model.Notification;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, String>{

	List<Notification> findByEmailId(String emailId);

}