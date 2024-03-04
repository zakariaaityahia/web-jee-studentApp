package net.zakaria.EnglishExamapp.web;

import net.zakaria.EnglishExamapp.entities.Student;
import net.zakaria.EnglishExamapp.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class StudentController {
    @Autowired
    private StudentRepository studentRepository;

    @GetMapping("/index")
    public String index(Model model) {
        List<Student> students = studentRepository.findAll();
        model.addAttribute("ListStudents", students);
        return "students";
    }
    @GetMapping("/deleteStudent")
    public String delete(@RequestParam(name= "id") Long idStudent) {
        studentRepository.deleteById(idStudent);
        return "redirect:/index";
    }
}
