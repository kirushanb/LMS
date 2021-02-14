package com.lms.app.com.lms.app.repo;

        import com.lms.app.com.lms.app.model.subjects;
        import org.springframework.data.jpa.repository.JpaRepository;
        import org.springframework.data.jpa.repository.Query;

        import java.util.List;

public interface subjectRepo extends JpaRepository<subjects,Integer> {

     @Query(value = "SELECT * from SUBJECTS where id=?1",nativeQuery = true)
     List<subjects> findByLid(int id);
}
