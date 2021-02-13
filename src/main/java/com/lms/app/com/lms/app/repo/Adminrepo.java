package com.lms.app.com.lms.app.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.lms.app.com.lms.app.model.Admin_account;

public interface Adminrepo extends JpaRepository<Admin_account, Integer> {

	@Query(value = "SELECT * FROM ADMIN_ACCOUNT WHERE Username =?1",nativeQuery = true)
	boolean findByUserName(String Username);
	
	@Query(value = "SELECT * FROM ADMIN_ACCOUNT WHERE Username =?1",nativeQuery = true)
	Admin_account findUser(String Username);
}
