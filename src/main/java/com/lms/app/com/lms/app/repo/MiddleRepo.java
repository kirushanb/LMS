package com.lms.app.com.lms.app.repo;

import com.lms.app.com.lms.app.model.MiddleComments;
import com.lms.app.com.lms.app.model.Positive;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MiddleRepo extends JpaRepository<MiddleComments,Integer> {
	
	
	@Query(value = "SELECT * FROM MIDDLE_COMMENTS WHERE id=?1",nativeQuery = true)
	   List<MiddleComments>  findallbyMiddle(int id);
}
