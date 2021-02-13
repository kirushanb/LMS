package com.lms.app.com.lms.app.repo;

import com.lms.app.com.lms.app.model.Lecture;
import com.lms.app.com.lms.app.model.Positive;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public interface PositiveRepo extends JpaRepository<Positive,Integer> {


      @Query(value = "Select id,  count(message) from positive group by positive.id order by 2 desc ",nativeQuery = true)
        Positive findallbyCus();
      
      @Query(value = "Select positive.id, count(message) as count from positive group by positive.id order by count desc",nativeQuery = true)
      List<MessaheInterface> findallBycusAr();

      
   @Query(value = "Select positive.id,positive.fid,positive.message,positive.Name, count(message) as count from positive group by positive.id order by count desc",nativeQuery = true)   
      List<Positive>  findmypos();
   
   @Query(value = "SELECT * FROM POSITIVE WHERE id=?1",nativeQuery = true)
   List<Positive>  findallbyPositive(int id);
   
   
}
