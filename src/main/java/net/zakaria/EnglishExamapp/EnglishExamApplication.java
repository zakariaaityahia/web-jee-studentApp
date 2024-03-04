package net.zakaria.EnglishExamapp;

import net.zakaria.EnglishExamapp.entities.Student;
import net.zakaria.EnglishExamapp.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;
import java.util.List;

@SpringBootApplication
public class EnglishExamApplication {
//	@Autowired
//	private StudentRepository studentRepository;

	public static void main(String[] args) {
		SpringApplication.run(EnglishExamApplication.class, args);
	}
	@Bean
	public CommandLineRunner start(StudentRepository studentRepository) {
		return args -> {
			// NoArgsConstructor
			Student p1 = new Student();
			p1.setFullName("Zakaria ait yahia");
			p1.setBirth(new Date());
			p1.setMark(19);
			p1.setStatus(true);

			// AllArgsConstructor
			Student p2 = new Student(null, "Ahmed amely", new Date(), 18, true);

			//Builder
			Student p3 = Student.builder()
							.fullName("amal amely")
							.birth(new Date())
							.mark(17)
							.status(true).build();

			studentRepository.save(p1);
			studentRepository.save(p2);
			studentRepository.save(p3);


			List<Student> students = studentRepository.findAll();
			students.forEach(p -> {
				System.out.println(p.toString());
			});
		};
	}
}
