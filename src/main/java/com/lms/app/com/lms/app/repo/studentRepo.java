package com.lms.app.com.lms.app.repo;

import com.lms.app.com.lms.app.model.Lecture;
import com.lms.app.com.lms.app.model.Students;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface studentRepo extends JpaRepository<Students,Integer> {

    @Query(value = "select * from Students where id=?1",nativeQuery = true)
    Students findByCustom(int id);
    
    
    @Query(value = "SELECT * FROM STUDENTS WHERE Username=?1",nativeQuery = true)
    Students findbylog(String Username);



}
