/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.classRosterSIWebApp.entity;

import java.util.List;

/**
 *
 * @author chaseowens
 */
public class Course {
    private int id;
    private String name;
    private String description;
    private Teacher teacher;
    private List<Student> students;
}
