package com.lms.app.com.lms.app.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lms.app.com.lms.app.model.Marks;
import org.springframework.data.jpa.repository.Query;

public interface marksRepo extends JpaRepository<Marks, Integer>{

    @Query(value = "select * from Marks where id=?1",nativeQuery = true)
    Marks findBycustom(int id);

}
