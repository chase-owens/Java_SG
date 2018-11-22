/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mycompany.classroster.controller;

import java.util.ArrayList;
import java.util.HashMap;
import mycompany.classroster.dto.ClassRosterDataValidationException;
import mycompany.classroster.dto.ClassRosterDuplicateException;
import mycompany.classroster.dto.ClassRosterPersistenceException;
import mycompany.classroster.dto.Student;
import mycompany.classroster.service.Service;
import mycompany.classroster.ui.View;

/**
 *
 * @author chaseowens
 */
public class Controller {

    View view;
    Service service;

    public Controller(View injectedView, Service injectedService) {
        this.view = injectedView;
        this.service = injectedService;
    }

    public void run() throws ClassRosterPersistenceException, ClassRosterDuplicateException,
            ClassRosterDataValidationException {
        boolean keepGoing = true;
        readStudents();

        while (keepGoing) {
            int menuSelection = displayMenuSelection();

            switch (menuSelection) {
                case 1:
                    addStudent();
                    break;
                case 2:
                    removeStudent();
                    break;
                case 3:
                    getStudentScores();
                    break;
                case 4:
                    averageStudentScores();
                    break;
                case 5:
                    displayStudents();
                    break;
                case 6:
                    exitProgram();
                    keepGoing = false;
                    break;
                default:
                    break;
            }
        }

    }

    private int displayMenuSelection() {
        return view.getMenuSelection();
    }

    private void addStudent() throws
            ClassRosterDuplicateException,
            ClassRosterDataValidationException,
            ClassRosterPersistenceException {
        // Get student first and last name
        String firstName = view.getFirstName();
        String lastName = view.getLastName();
        int[] studentGrades = view.setGrades();
        int grade1 = studentGrades[0];
        int grade2 = studentGrades[1];
        int grade3 = studentGrades[2];

        // Create arrayList
        ArrayList<Integer> grades = service.createArrayList(grade1, grade2, grade3);

        // Instantiate student with names
        Student newStudent = service.createStudent(firstName, lastName, grades);

        // Check to make sure not duplicate id, has names, and has grades
        service.checkStudentProperties(newStudent);

        // Add student to students
        service.addStudentToHashTable(newStudent);
        
        //service.writeAuditEntry("Student " + newStudent.getId() + " was created.");
    }

    private void removeStudent() throws ClassRosterPersistenceException{
        // Get student last name
        String lastName = view.getLastName();

        // Delete student form students
        service.removeStudent(lastName);
        
        //service.writeAuditEntry(lastName + " was deleted rom the roster");
    }

    private void getStudentScores() {
        // Get student lastName
        String lastName = view.getLastName();

        // Find student grades in students
        ArrayList<Integer> grades = service.findStudentGrades(lastName);

        // Print scores
        view.printScores(grades);

    }

    private void averageStudentScores() {
        // get last name
        String lastName = view.getLastName();

        // get grades
        ArrayList<Integer> grades = service.findStudentGrades(lastName);

        // calculate average
        int average = service.calculateAverage(grades);

        // print average
        view.printAverage(average);
    }

    private void displayStudents() {
        // print all studnets
        String[] students = service.getStudents();

        //print students
        view.printStudents(students);
    }

    private void readStudents() throws ClassRosterPersistenceException {
        service.readStudents();
    }

    private void exitProgram() throws ClassRosterPersistenceException{
        HashMap<String, Student> students = service.getStudentMap();
        service.writeStudents(students);
        view.exitProgram();
    }

}
