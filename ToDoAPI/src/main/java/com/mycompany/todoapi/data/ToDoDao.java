/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.todoapi.data;

import com.mycompany.todoapi.models.ToDo;
import java.util.List;

/**
 *
 * @author chaseowens
 */
public interface ToDoDao {
    ToDo add(ToDo todo);
    List<ToDo> getAll();
    ToDo findById(int id);
    boolean update(ToDo todo);
    boolean deleteById(int id);
}
