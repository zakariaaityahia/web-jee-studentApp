package net.zakaria.EnglishExamapp.repository;

import net.zakaria.EnglishExamapp.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {

}
