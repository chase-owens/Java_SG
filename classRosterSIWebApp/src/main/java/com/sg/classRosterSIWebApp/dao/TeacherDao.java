/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.classRosterSIWebApp.dao;

import com.sg.classRosterSIWebApp.entity.Teacher;
import java.util.List;

/**
 *
 * @author chaseowens
 */
public interface TeacherDao {
    Teacher getTeacherById(int id);
    List<Teacher> getAllTeachers();
    Teacher addTeacher(Teacher teacher);
    void updateTeacher(Teacher teacher);
    void deleteTeacherById(int id);
}
