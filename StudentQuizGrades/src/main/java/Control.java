
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author chaseowens
 */
public class Control {

    View view = new View();
    Service service = new Service();

    public void run() {

        boolean keepGoing = true;

        while (keepGoing) {
            int menuSelection = view.getMenuSelection();

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
                    view.exitProgram();
                    keepGoing = false;
                    break;
                default:
                    break;
            }
        }

    }

    private void addStudent() {
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

        // Add student to students
        service.addStudentToHashTable(newStudent);
    }

    private void removeStudent() {
        // Get student last name
        String lastName = view.getLastName();
        
        // Delete student form students
        service.removeStudent(lastName);
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

}
