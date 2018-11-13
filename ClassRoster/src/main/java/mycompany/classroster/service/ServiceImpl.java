/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mycompany.classroster.service;

import java.util.ArrayList;
import java.util.HashMap;
import mycompany.classroster.dao.ClassRosterAuditDAO;
import mycompany.classroster.dao.DAO;
import mycompany.classroster.dto.ClassRosterDataValidationException;
import mycompany.classroster.dto.ClassRosterDuplicateException;
import mycompany.classroster.dto.ClassRosterPersistenceException;
import mycompany.classroster.dto.Student;

/**
 *
 * @author chaseowens
 */
public class ServiceImpl implements Service {

    // Calls DAO 
    DAO dao;
    ClassRosterAuditDAO auditDao;

    public ServiceImpl(DAO injectedDAO, ClassRosterAuditDAO injectedAuditDao) {
        this.dao = injectedDAO;
        this.auditDao = injectedAuditDao;
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

    @Override
    public void checkStudentProperties(Student newStudent) throws ClassRosterDuplicateException, ClassRosterDataValidationException {
        dao.checkStudentProperties(newStudent);
    }

    @Override
    public void readStudents() throws ClassRosterPersistenceException {
        dao.readStudents();
    }

    @Override
    public HashMap<String, Student> getStudentMap() {
        return dao.getStudentMap();
    }

    @Override
    public void writeStudents(HashMap<String, Student> students) throws ClassRosterPersistenceException {
        dao.writeStudents(students);
    }

    @Override
    public void writeAuditEntry(String string) throws ClassRosterPersistenceException {
        auditDao.writeAuditEntry(string);
    }

}
