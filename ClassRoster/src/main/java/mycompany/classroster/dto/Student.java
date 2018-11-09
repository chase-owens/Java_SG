/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mycompany.classroster.dto;
import java.util.ArrayList;

/**
 *
 * @author chaseowens
 */
public class Student {
    private String firstName;
    private String lastName;
    private ArrayList<Integer> grades = new ArrayList<>();
    
    public Student(String first, String last, ArrayList<Integer> grades) {
        this.firstName = first;
        this.lastName = last;
        this.grades = grades;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    
    public String getFullName() {
        return (this.lastName + ", " + this.firstName);
    }

    public ArrayList<Integer> getGrades() {
        return grades;
    }

    public void setGrades(ArrayList<Integer> grades) {
        this.grades = grades;
    }
}
