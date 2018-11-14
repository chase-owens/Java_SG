/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mycompany.classroster.dao;

import java.util.ArrayList;
import mycompany.classroster.dto.ClassRosterPersistenceException;
import mycompany.classroster.dto.Student;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author chaseowens
 */
public class DAOTest {
    
    private DAO dao = new DAOImpl();
    ArrayList<Integer> grades = new ArrayList<>();
    Student newStudent;

    public DAOTest() {
        int grade1 = 100;
        int grade2 = 100;
        int grade3 = 100;
        grades.add(grade1);
        grades.add(grade2);
        grades.add(grade3);
        String firstName = "Chase";
        String lastName = "Owens";

        this.newStudent = dao.createStudent(firstName, lastName, grades);

        dao.addStudentToHashTable(newStudent);
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        dao.removeAllStudentsFromHashTable();
    }

    @After
    public void tearDown() throws ClassRosterPersistenceException {
        dao.readStudents();
    }
    
    /**
     * Test of addStudentToHashTable method, of class DAO.
     */
    @Test
    public void testAddGetStudent() {
        // Should start off with empty HashMap
        assertEquals(0, dao.getStudentKeys().size());
        assertEquals(0, dao.getStudents().length);
        assertEquals(0, dao.getStudentMap().size());
        
        // Check that student added and returned are equal
        dao.addStudentToHashTable(newStudent);
        Student thisStudent = new Student("Chase", "Owens", grades, 0);
        Student daoStudent = dao.getStudent("Owens");
        assertEquals(thisStudent, daoStudent);
        
        // Check that student was added to HashMap
        assertEquals(1, dao.getStudentKeys().size());
        assertEquals(1, dao.getStudents().length);
        assertEquals(1, dao.getStudentMap().size());
    }
    
    /**
     * Test of removeStudent method, of class DAO.
     */
    @Test
    public void testRemoveStudent() {
        // Add student to table and check that it has been added
        dao.addStudentToHashTable(newStudent);
        assertEquals(1, dao.getStudentKeys().size());
        assertEquals(1, dao.getStudents().length);
        assertEquals(1, dao.getStudentMap().size());
        
        dao.removeStudent(newStudent.getLastName());
        // map should now hav one less student ie. 0
        assertEquals(0, dao.getStudentKeys().size());
        assertEquals(0, dao.getStudents().length);
        assertEquals(0, dao.getStudentMap().size());
        
    }
    
}
