/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.inventory.repositories;

import com.sg.inventory.entities.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author chaseowens
 */
@Repository
public interface StoreRepository extends JpaRepository<Store, Integer> {
    
}
