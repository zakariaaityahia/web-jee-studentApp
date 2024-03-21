package net.zakaria.EnglishExamapp.web;

import net.zakaria.EnglishExamapp.entities.Student;
import net.zakaria.EnglishExamapp.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.querydsl.QPageRequest;
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
    public String index(Model model,
                        @RequestParam(name = "page", defaultValue = "0") int p,
                        @RequestParam(name = "size", defaultValue = "4")  int s,
                        @RequestParam(name = "keyword", defaultValue = "")  String kw
    ){

        Page<Student> pageStudents = studentRepository.findByFullNameContains(kw, PageRequest.of(p,s));
        model.addAttribute("ListStudents", pageStudents.getContent());
        // return the total number of pages
        model.addAttribute("pages", new int[pageStudents.getTotalPages()]);
        // default page
        model.addAttribute("currentPage", p);
        model.addAttribute("keyword", kw);
        return "students";
    }
    @GetMapping("/deleteStudent")
    public String delete(@RequestParam(name= "id") Long idStudent, String keyword, int page) {
        studentRepository.deleteById(idStudent);
        return "redirect:/index?page="+page+"&keyword="+keyword;
    }
    @GetMapping("/formStudents")
    public String formStudents(Model model) {
        model.addAttribute("Students", new Student());
        return "formStudents";
    }
}
