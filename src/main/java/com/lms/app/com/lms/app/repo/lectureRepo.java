package com.lms.app.com.lms.app.repo;

import com.lms.app.com.lms.app.model.Lecture;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface lectureRepo extends JpaRepository<Lecture,Integer> {

 @Query(value = "SELECT * FROM LECTURE WHERE id=?1",nativeQuery = true)	
 Lecture   findBycCusId(int id);
 

     @Query(value = "SELECT * FROM LECTURE WHERE Username=?1",nativeQuery = true)
     Lecture findbylog(String Username);
}
