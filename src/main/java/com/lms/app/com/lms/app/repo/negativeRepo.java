package com.lms.app.com.lms.app.repo;

import com.lms.app.com.lms.app.model.Negative;
import com.lms.app.com.lms.app.model.Positive;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface negativeRepo extends JpaRepository<Negative,Integer>{

	 @Query(value = "SELECT * FROM NEGATIVE WHERE id=?1",nativeQuery = true)
	   List<Negative>  findallbyPositive(int id);

}
