/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.BlockBusters.dao;

import com.example.BlockBusters.entity.Custom;
import java.util.List;

/**
 *
 * @author chaseowens
 */
public interface CustomDao {
    //CRUD methods
    public Custom createCustom();
    public List<Custom> readAllCustoms();
    public Custom readCustomById(int id);
    public void updateCustom(int id);
    public void deleteCustom(int id);
}
