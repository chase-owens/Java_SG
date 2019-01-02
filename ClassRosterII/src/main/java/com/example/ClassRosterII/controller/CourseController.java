/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.ClassRosterII.controller;

import com.example.ClassRosterII.dao.CourseDao;
import com.example.ClassRosterII.dao.StudentDao;
import com.example.ClassRosterII.dao.TeacherDao;
import com.example.ClassRosterII.entity.Course;
import com.example.ClassRosterII.entity.Student;
import com.example.ClassRosterII.entity.Teacher;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
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
public class CourseController {
    
    @Autowired
    TeacherDao teacherDao;

    @Autowired
    StudentDao studentDao;

    @Autowired
    CourseDao courseDao;
    
    Set<ConstraintViolation<Teacher>> violations = new HashSet<>();
    
    @GetMapping("courses")
    public String displayCourses(Model model) {
        List<Course> courses = courseDao.getAllCourses();
        List<Teacher> teachers = teacherDao.getAllTeachers();
        List<Student> students = studentDao.getAllStudents();
        model.addAttribute("courses", courses);
        model.addAttribute("teachers", teachers);
        model.addAttribute("students", students);
        return "courses";
    }
    
    @PostMapping("addCourse")
    public String addCourse(Course course, HttpServletRequest request) {
        String teacherId = request.getParameter("teacherId");
        String[] studentIds = request.getParameterValues("studentId");
        
        course.setTeacher(teacherDao.getTeacherById(Integer.parseInt(teacherId)));
        
        List<Student> students = new ArrayList<>();
        for(String studentId : studentIds) {
            students.add(studentDao.getStudentById(Integer.parseInt(studentId)));
        }
        course.setStudents(students);
        courseDao.addCourse(course);
        
        return "redirect:/courses";
    }
    
    @GetMapping("courseDetail")
    public String courseDetail(Integer id, Model model) {
        Course course = courseDao.getCourseById(id);
        model.addAttribute("course", course);
        return "courseDetail";
    }
    
    @GetMapping("deleteCourse")
    public String deleteCourse(Integer id) {
        courseDao.deleteCourseById(id);
        return "redirect:/courses";
    }
    
    @GetMapping("editCourse")
    public String editCourse(Integer id, Model model) {
        Course course = courseDao.getCourseById(id);
        List<Student> students = studentDao.getAllStudents();
        List<Teacher> teachers = teacherDao.getAllTeachers();
        model.addAttribute("course", course);
        model.addAttribute("students", students);
        model.addAttribute("teachers", teachers);
        return "editCourse";
    }
    
    @PostMapping("editCourse")
    public String performEditCourse(Course course, HttpServletRequest request) {
        String teacherId = request.getParameter("teacherId");
        String[] studentIds = request.getParameterValues("studentId");
        
        course.setTeacher(teacherDao.getTeacherById(Integer.parseInt(teacherId)));
        
        List<Student> students = new ArrayList<>();
        for(String studentId : studentIds) {
            students.add(studentDao.getStudentById(Integer.parseInt(studentId)));
        }
        course.setStudents(students);
        courseDao.updateCourse(course);
        
        return "redirect:/courses";
    }
}
