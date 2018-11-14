/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mycompany.classroster.dao;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Set;
import mycompany.classroster.dto.ClassRosterDataValidationException;
import mycompany.classroster.dto.ClassRosterDuplicateException;
import mycompany.classroster.dto.ClassRosterPersistenceException;
import mycompany.classroster.dto.Student;

/**
 *
 * @author chaseowens
 */
public class DAOImpl implements DAO {

    HashMap<String, Student> students = new HashMap<>();
    PrintWriter write;
    public static final String CLASS_ROSTER = "roster.txt";
    public static final String DELIMETER = "::";

    /**
     *
     * @param currentRoster
     * @throws ClassRosterPersistenceException
     */
    @Override
    public void writeStudents(HashMap<String, Student> currentRoster) throws ClassRosterPersistenceException {
        try {
            write = new PrintWriter(new FileWriter("roster.txt"));
        } catch (IOException e) {
            throw new ClassRosterPersistenceException("Hopefully the students have more presistence than this class roster app!!", e);
        }

        Collection<Student> currentStudents = currentRoster.values();
        for (Student student : currentStudents) {
            ArrayList<Integer> grades = student.getGrades();
            
            write.print(
                    student.getFirstName() + DELIMETER
                    + student.getLastName() + DELIMETER +
                            grades.get(0) + DELIMETER +
                            grades.get(1) + DELIMETER +
                            grades.get(2) + DELIMETER +
                            student.getId()
            );
            write.flush();
        }
        write.close();
    }
    
    @Override
    public void readStudents() throws ClassRosterPersistenceException {
        Scanner read;
        
        try {
            read = new Scanner(
                    new BufferedReader(
                            new FileReader("roster.txt")));
        } catch(FileNotFoundException e) {
            throw new ClassRosterPersistenceException("Hopefully the students have more presistence than this class roster app!!", e);
        }
        
        while(read.hasNextLine()) {
            String currentLine = read.nextLine();
            String currentTokens[];
            
            currentTokens = currentLine.split(DELIMETER);
            
            int grade1 = Integer.parseInt(currentTokens[2]);
            int grade2 = Integer.parseInt(currentTokens[3]);
            int grade3 = Integer.parseInt(currentTokens[4]);
            
            ArrayList<Integer> grades = createArrayList(grade1, grade2, grade3);
            
            Student student = createStudent(currentTokens[0], currentTokens[1], grades);
            
            addStudentToHashTable(student);
        }
        
        read.close();
    }

    @Override
    public ArrayList<Integer> createArrayList(int grade1, int grade2, int grade3) {
        ArrayList<Integer> grades = new ArrayList<>();
        grades.add(grade1);
        grades.add(grade2);
        grades.add(grade3);

        return grades;
    }

    @Override
    public Student createStudent(String firstName, String lastName, ArrayList<Integer> grades) {
        Student newStudent = new Student(firstName, lastName, grades, students.size());
        return newStudent;
    }

    @Override
    public void addStudentToHashTable(Student newStudent) {
        students.put(Integer.toString(students.size()), newStudent);
    }

    @Override
    public void removeStudent(String lastName) {
        Student studentOfInterest = null;
        String theKey = null;
        for (int i = 0; i < students.size(); i++) {
            String studentKey = Integer.toString(i);
            Student someStudent = students.get(studentKey);
            String someStudentLastName = someStudent.getLastName();
            if (lastName.equals(someStudentLastName)) {
                studentOfInterest = someStudent;
                theKey = studentKey;
                break;
            }
            
        }
        students.remove(theKey);
    }

    @Override
    public ArrayList<Integer> findStudentGrades(String lastName) {
        Student studentOfInterest = null;
        for (int i = 0; i < students.size(); i++) {
            String studentKey = Integer.toString(i);
            Student someStudent = students.get(studentKey);
            String someStudentLastName = someStudent.getLastName();
            if (lastName.equals(someStudentLastName)) {
                studentOfInterest = someStudent;
                break;
            }
        }
        return studentOfInterest.getGrades();
    }

    @Override
    public int calculateAverage(ArrayList<Integer> grades) {
        int sum = 0;
        for (int grade : grades) {
            sum += grade;
        }
        return (int) sum / grades.size();
    }

    @Override
    public String[] getStudents() {
        String[] studentsToDisplay = new String[students.size()];
        String studentKey = "";
        for (int i = 0; i < students.size(); i++) {
            studentKey = Integer.toString(i);
            Student learner = students.get(studentKey);
            studentsToDisplay[i] = learner.getFullName();
        }
        return studentsToDisplay;
    }

    @Override
    public Student getStudent(String lastName) {
        Student studentOfInterest = null;
        for (int i = 0; i < students.size(); i++) {
            String studentKey = Integer.toString(i);
            Student someStudent = students.get(studentKey);
            String someStudentLastName = someStudent.getLastName();
            if (lastName.equals(someStudentLastName)) {
                studentOfInterest = someStudent;
                break;
            }
        }
        return studentOfInterest;
    }

    @Override
    public void checkStudentProperties(Student newStudent) throws ClassRosterDuplicateException, ClassRosterDataValidationException {
        int newStudentId = newStudent.getId();
        for (int i = 0; i < students.size(); i++) {
            String studentKey = Integer.toString(i);
            Student someStudent = students.get(studentKey);
            
            // Check for duplicate ids
            int id = someStudent.getId();
            if (id == newStudentId) {
                 throw new ClassRosterDuplicateException("That created a student with an id that already exists, please try again.");
            }
            
            // Check for null values
            if (someStudent.getFirstName() == null || someStudent.getLastName() == null || someStudent.getGrades().size() != 3) {
                throw new ClassRosterDataValidationException("3 quiz grades and a first and last name are required to add a student.");
            }
        }
        
        // Audits entries
    }

    @Override
    public HashMap<String, Student> getStudentMap() {
        return students;
    }

    @Override
    public Set getStudentKeys() {
        return students.keySet();
    }

    @Override
    public void removeAllStudentsFromHashTable() {
        students.clear();
    }

}
