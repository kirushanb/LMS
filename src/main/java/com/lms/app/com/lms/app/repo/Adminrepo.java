package com.lms.app.com.lms.app.repo;

import com.lms.app.com.lms.app.model.Students;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.lms.app.com.lms.app.model.Admin_account;

public interface Adminrepo extends JpaRepository<Admin_account, Integer> {

//	@Query(value = "SELECT * FROM admin_account WHERE Username =?1",nativeQuery = true)
//	boolean findUser(String Username);

//	@Query(value = "select * from admin_account where id=?1",nativeQuery = true)
//	Admin_account findUser(String Username);



	@Query(value = "SELECT * FROM admin_account WHERE Username =?1",nativeQuery = true)
	Admin_account findUser(String Username);
}
