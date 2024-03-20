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


			Student p4 = new Student(null, "Yasmine Ali", new Date(), 21, false);
			Student p5 = new Student(null, "Omar Hadi", new Date(), 22, true);
			Student p7 = new Student(null, "Mohammed Sabir", new Date(), 19, true);
			Student p6 = new Student(null, "Fatima Khalid", new Date(), 20, false);


			studentRepository.save(p1);
			studentRepository.save(p2);
			studentRepository.save(p3);
			studentRepository.save(p4);
			studentRepository.save(p5);
			studentRepository.save(p6);
			studentRepository.save(p7);


			List<Student> students = studentRepository.findAll();
			students.forEach(p -> {
				System.out.println(p.toString());
			});
		};
	}
}
