package net.zakaria.EnglishExamapp.repository;

import net.zakaria.EnglishExamapp.entities.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface StudentRepository extends JpaRepository<Student, Long> {
    Page<Student> findByNomContains(String keyword, Pageable pageable);
    @Query("select p from student p where p.full_name like :x")
    Page<Student> Charcher(@Param("x") String keyword, Pageable pageable);

}
