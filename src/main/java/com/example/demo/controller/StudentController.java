package com.example.demo.controller;

import com.example.demo.model.Student;
import com.example.demo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("api/v1/student")
public class StudentController {
    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("list")
    public List<Student> list() {
        return studentService.list();
    }

    @GetMapping
    public String getStudentList(Model model){

        List <Student> listStudent = studentService.list();

        model.addAttribute("list", listStudent);

        return "student";
    }

    @PostMapping
    @ResponseBody
    public void newStudent(@RequestBody Student student){
        studentService.addStudent(student);
    }

    @PutMapping(path = "{studentId}")
    public void updateStudent(
            @PathVariable("studentId") Long id,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String email){
        studentService.updateStudent(id, name, email);
    }

    @DeleteMapping(path = "{studentId}")
    public void delStudent(@PathVariable("studentId") Long id) {
        studentService.remStudent(id);
    }
}
