/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.ClassRosterII.dao;

import com.example.ClassRosterII.entity.Student;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author chaseowens
 */
@Repository
public class StudentDaoDB implements StudentDao {

    @Autowired
    JdbcTemplate jdbc;

    @Override
    public Student getStudentById(int id) {
        try {
            final String GET_STUDENT_BY_ID = "SELECT * FROM student WHERE id = ?";
            return jdbc.queryForObject(GET_STUDENT_BY_ID, new StudentMapper(), id);
        } catch (DataAccessException e) {
            return null;
        }
    }

    @Override
    public List<Student> getAllStudents() {
        final String GET_STUDENTS = "SELECT * FROM student";
        return jdbc.query(GET_STUDENTS, new StudentMapper());
    }

    @Override
    public Student addStudent(Student student) {
        final String ADD_STUDENT = "INSERT INTO student(firstName, lastName) VALUES(?,?)";
        jdbc.update(ADD_STUDENT, student.getFirstName(), student.getLastName());
        int newId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        student.setId(newId);
        return student;
    }

    @Override
    public void updateStudent(Student student) {
        final String UPDATE_STUDENT = "UPDATE student SET firstName = ?, lastName = ?";
        jdbc.update(UPDATE_STUDENT, student.getFirstName(), student.getLastName());
    }

    @Override
    @Transactional
    public void deleteStudentById(int id) {
        final String DELETE_COURSE_STUDENT = "DELETE FROM course_student WHERE studentId = ?";
        jdbc.update(DELETE_COURSE_STUDENT, id);

        final String DELETE_STUDENT = "DELETE FROM student WHERE id = ?";
        jdbc.update(DELETE_STUDENT, id);
    }

    public static final class StudentMapper implements RowMapper<Student> {

        @Override
        public Student mapRow(ResultSet rs, int index) throws SQLException {
            Student student = new Student();
            student.setId(rs.getInt("id"));
            student.setFirstName(rs.getString("firstName"));
            student.setLastName(rs.getString("lastName"));

            return student;
        }

    }
}
