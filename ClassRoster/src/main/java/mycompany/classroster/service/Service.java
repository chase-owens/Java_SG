/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mycompany.classroster.service;

import java.util.ArrayList;
import java.util.HashMap;
import mycompany.classroster.dto.ClassRosterDataValidationException;
import mycompany.classroster.dto.ClassRosterDuplicateException;
import mycompany.classroster.dto.ClassRosterPersistenceException;
import mycompany.classroster.dto.Student;

/**
 *
 * @author chaseowens
 */
public interface Service {
    
	    
	    /**
	     * Returns the student object associated with the given student id.
	     * Returns null if no such student exists
	     * 
	     * @param studentId ID of the student to retrieve
	     * @return the Student object associated with the given student id,  
	     * null if no such student exists
	     */
	    public Student getStudent(String studentId);
            
	    public void removeStudent(String lastName);

            public String[] getStudents();

            public ArrayList<Integer> createArrayList(int grade1, int grade2, int grade3);

            public Student createStudent(String firstName, String lastName, ArrayList<Integer> grades);

            public void addStudentToHashTable(Student newStudent);

            public ArrayList<Integer> findStudentGrades(String lastName);

            public int calculateAverage(ArrayList<Integer> grades);

            public void checkStudentProperties(Student newStudent)throws ClassRosterDuplicateException, ClassRosterDataValidationException;

            public void readStudents() throws ClassRosterPersistenceException;

            public HashMap<String, Student> getStudentMap();

            public void writeStudents(HashMap<String, Student> students) throws ClassRosterPersistenceException;

            public void writeAuditEntry(String string) throws ClassRosterPersistenceException;


}
