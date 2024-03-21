package net.zakaria.EnglishExamapp.web;

import jakarta.validation.Valid;
import net.zakaria.EnglishExamapp.entities.Student;
import net.zakaria.EnglishExamapp.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.querydsl.QPageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
        model.addAttribute("student", new Student());
        return "formStudents";
    }

    @PostMapping("/save")
    public String save(Model model, @Valid Student student, BindingResult bindingResult,
                                    @RequestParam(defaultValue = "0") int page,
                                    @RequestParam(defaultValue = "")String keyword) {
        if (bindingResult.hasErrors()) return "formStudents";
        studentRepository.save(student);
        return "redirect:/index?page"+page+"&keyword="+keyword;
    }

    @GetMapping("/editStudents")
    public String editStudents(Model model, Long id, String keyword, int page) {
        Student student = studentRepository.findById(id).orElse(null);
        if(student == null) throw new RuntimeException("Student introuvable");
        model.addAttribute("student", student);
        model.addAttribute("keyword", keyword);
        model.addAttribute("page", page);
        return "editStudents";
    }
}
