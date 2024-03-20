package net.zakaria.EnglishExamapp.repository;

import net.zakaria.EnglishExamapp.entities.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface StudentRepository extends JpaRepository<Student, Long> {
    Page<Student> findByFullNameContains(String keyword, Pageable pageable);
    @Query("select p from Student p where p.fullName like :x")
    Page<Student> charcher(@Param("x") String keyword, Pageable pageable);

}
