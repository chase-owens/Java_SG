/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.ClassRosterII.dao;

import com.example.ClassRosterII.entity.Course;
import com.example.ClassRosterII.entity.Student;
import com.example.ClassRosterII.entity.Teacher;
import java.util.List;

/**
 *
 * @author chaseowens
 */
public interface CourseDao {

    Course getCourseById(int id);

    List<Course> getAllCourses();

    Course addCourse(Course course);

    void updateCourse(Course course);

    void deleteCourseById(int id);

    List<Course> getCoursesForTeacher(Teacher teacher);

    List<Course> getCourseForStudent(Student student);
}
