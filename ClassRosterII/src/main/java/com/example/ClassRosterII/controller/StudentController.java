/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.ClassRosterII.controller;

import com.example.ClassRosterII.dao.CourseDao;
import com.example.ClassRosterII.dao.StudentDao;
import com.example.ClassRosterII.dao.TeacherDao;
import com.example.ClassRosterII.entity.Student;
import com.example.ClassRosterII.entity.Teacher;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.validation.ConstraintViolation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author chaseowens
 */
@Controller
public class StudentController {
    
    @Autowired
    TeacherDao teacherDao;

    @Autowired
    StudentDao studentDao;

    @Autowired
    CourseDao courseDao;
    
    Set<ConstraintViolation<Teacher>> violations = new HashSet<>();
    
    @GetMapping("students")
    public String displayStudents(Model model) {
        List<Student> students = studentDao.getAllStudents();
        model.addAttribute("students", students);
        return "students";
    }
    
    @PostMapping("addStudent")
    public String addStudent(String firstName, String lastName) {
        Student student = new Student();
        student.setFirstName(firstName);
        student.setLastName(lastName);
        studentDao.addStudent(student);
        
        return "redirect:/students";
    }
    
    @GetMapping("deleteStudent")
    public String deleteStudent(Integer id) {
        studentDao.deleteStudentById(id);
        return "redirect:/students";
    }
    
    @GetMapping("editStudent")
    public String editStudent(Integer id, Model model) {
        Student student = studentDao.getStudentById(id);
        model.addAttribute("student", student);
        return "editStudent";
    }
    
    @PostMapping("editStudent")
    public String performEditStudent(Student student) {
        studentDao.updateStudent(student);
        return "redirect:/students";
    }
}
