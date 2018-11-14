/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mycompany.classroster.dao;

import java.util.ArrayList;
import java.util.HashMap;
import mycompany.classroster.dto.ClassRosterDataValidationException;
import mycompany.classroster.dto.ClassRosterDuplicateException;
import mycompany.classroster.dto.Student;

/**
 *
 * @author chaseowens
 */
public abstract class DAOStubImpl implements DAO {
    
    private final Student onlyStudent;
    private final HashMap<String, Student> studentList = new HashMap<>();
    
    public DAOStubImpl() {
        ArrayList<Integer> grades = new ArrayList<>();
        grades.add(100);
        grades.add(100);
        grades.add(100);
        onlyStudent = new Student("Stan", "Lee", grades, 0);
        
        studentList.put(Integer.toString(onlyStudent.getId()), onlyStudent);
    }
    

    @Override
    public Student getStudent(String lastName) {
        if (lastName.equals(onlyStudent.getLastName())) {
            return onlyStudent;
        } else {
            return null;
        }
    }

    @Override
    public void removeStudent(String lastName) {
        if (lastName.equals(onlyStudent.getLastName())) {
            studentList.remove(Integer.toString(onlyStudent.getId()));
        }
    }

    @Override
    public String[] getStudents() {
        String[] studentNames = new String[studentList.size()];
        for (int count = 0; count < studentList.size(); count++) {
            Student a = studentList.get(Integer.toString(count));
            studentNames[count] = a.getFullName();
        }
        return studentNames;
    }

    @Override
    public void checkStudentProperties(Student newStudent) throws ClassRosterDuplicateException, ClassRosterDataValidationException {
        String newStudentId = Integer.toString(newStudent.getId());
        String onlyStudentId = Integer.toString(onlyStudent.getId());
        if (newStudentId.equals(onlyStudentId)) {
            throw new ClassRosterDuplicateException("Same Id");
        }
    }
}
