/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.classRosterSIWebApp.dao;

import com.sg.classRosterSIWebApp.entity.Course;
import com.sg.classRosterSIWebApp.entity.Student;
import com.sg.classRosterSIWebApp.entity.Teacher;
import java.util.List;

/**
 *
 * @author chaseowens
 */
public interface CourseDao {
    Course getCourseById(int id);
    List<Course> getAllCourses();
    Course addCourse(Course course);
    void updtaeCourse(Course couse);
    void deleteCourseById(int id);
    
    List<Course> getCoursesForTeacher(Teacher teacher);
    List<Course> getCourseForStudent(Student student);
}
