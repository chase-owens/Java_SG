/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mycompany.classroster.service;

import java.util.ArrayList;
import java.util.List;
import mycompany.classroster.dao.DAO;
import mycompany.classroster.dto.Student;

/**
 *
 * @author chaseowens
 */
public class ServiceImpl implements Service {
    // Calls DAO 
    DAO dao;
    
    public ServiceImpl(DAO injectedDAO) {
        this.dao = injectedDAO;
    }

    @Override
    public Student getStudent(String studentId) {
        return dao.getStudent(studentId);
    }

    @Override
    public void removeStudent(String lastName) {
        dao.removeStudent(lastName);
    }

    @Override
    public ArrayList<Integer> createArrayList(int grade1, int grade2, int grade3) {
        return dao.createArrayList(grade1, grade2, grade3);
    }

    @Override
    public Student createStudent(String firstName, String lastName, ArrayList<Integer> grades) {
        return dao.createStudent(firstName, lastName, grades);
    }

    @Override
    public void addStudentToHashTable(Student newStudent) {
        dao.addStudentToHashTable(newStudent);
    }

    @Override
    public ArrayList<Integer> findStudentGrades(String lastName) {
        return dao.findStudentGrades(lastName); 
    }

    @Override
    public int calculateAverage(ArrayList<Integer> grades) {
        return dao.calculateAverage(grades);
    }

    @Override
    public String[] getStudents() {
        return dao.getStudents();
    }
}
