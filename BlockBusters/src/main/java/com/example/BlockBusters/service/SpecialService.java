/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.BlockBusters.service;

import com.example.BlockBusters.entity.Special;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author chaseowens
 */
public interface SpecialService {
    public Special createSpecial(String title, String description, int customId, LocalDate dateBegin, LocalDate dateEnd, int userId);
    public List<Special> getAllSpecials();
    public Special getSpecialById(int id);
    public void deleteSpecial(int id);
}
