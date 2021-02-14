package com.lms.app.com.lms.app.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lms.app.com.lms.app.model.User_message;

public interface InqRepo extends JpaRepository<User_message, Integer>{

}
