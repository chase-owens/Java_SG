/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.classRosterSIWebApp.dao;

import com.sg.classRosterSIWebApp.entity.Student;
import java.util.List;

/**
 *
 * @author chaseowens
 */
public interface StudentDao {
    Student getStudentById(int id);
    List<Student> getAllStudents();
    Student addStudent(Student student);
    void updateStudent(Student student);
    void deleteStudentById(int id);
}
