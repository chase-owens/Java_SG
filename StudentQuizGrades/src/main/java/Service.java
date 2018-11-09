
import java.util.ArrayList;
import java.util.HashMap;

 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author chaseowens
 */
public class Service {
    HashMap<String, Student> students = new HashMap<>();

    public ArrayList<Integer> createArrayList(int grade1, int grade2, int grade3) {
        ArrayList<Integer> grades = new ArrayList<>();
        grades.add(grade1);
        grades.add(grade2);
        grades.add(grade3);
        
        return grades;
    }

    public Student createStudent(String firstName, String lastName, ArrayList<Integer> grades) {
        Student newStudent = new Student(firstName, lastName, grades);
        return newStudent;
    }

    public void addStudentToHashTable(Student newStudent) {
        students.put(Integer.toString(students.size()), newStudent);
    }

    public void removeStudent(String lastName) {
        students.remove(lastName);
    }

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

    public int calculateAverage(ArrayList<Integer> grades) {
        int sum = 0;
        for (int grade : grades) {
            sum += grade;
        }
        return (int) sum/grades.size();
    }

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
    
}
