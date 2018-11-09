/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mycompany.classroster.dao;

import java.util.ArrayList;
import java.util.List;
import mycompany.classroster.dto.Student;

/**
 *
 * @author chaseowens
 */
public interface DAO {
	    
	    /**
	     * Returns the student object associated with the given student id.
	     * Returns null if no such student exists
	     * 
	     * @param lastName of the student to retrieve
	     * @return the Student object associated with the given student id,  
	     * null if no such student exists
	     */
	    Student getStudent(String lastName);
	    
	    void removeStudent(String lastName);

    ArrayList<Integer> createArrayList(int grade1, int grade2, int grade3);

    Student createStudent(String firstName, String lastName, ArrayList<Integer> grades);

    void addStudentToHashTable(Student newStudent);

    ArrayList<Integer> findStudentGrades(String lastName);

    int calculateAverage(ArrayList<Integer> grades);

    String[] getStudents();
}
