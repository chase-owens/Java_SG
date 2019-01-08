/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.BlockBusters.dao;

import com.example.BlockBusters.entity.Special;
import java.util.List;

/**
 *
 * @author chaseowens
 */
public interface SpecialDao {
    //CRUD methods
    public Special createSpecial(String title, String description, int customId, int userId);
    public List<Special> readAllSpecials();
    public Special readSpecialById(int id);
    public void updateSpecial(Special special);
    public void deleteSpecial(int id);
}
