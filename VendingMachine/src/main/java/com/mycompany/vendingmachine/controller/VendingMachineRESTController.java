/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.vendingmachine.controller;

import com.mycompany.vendingmachine.dao.GetEntryError;
import com.mycompany.vendingmachine.dao.GettingMoneyError;
import com.mycompany.vendingmachine.dao.InsufficientFundsError;
import com.mycompany.vendingmachine.dao.OutOfStockException;
import com.mycompany.vendingmachine.dao.VendingMachinePersistenceError;
import com.mycompany.vendingmachine.dto.ChangeMaker;
import com.mycompany.vendingmachine.dto.Item;
import com.mycompany.vendingmachine.service.VMService;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author chaseowens
 */
@RestController
@RequestMapping("/api/")
public class VendingMachineRESTController {
    @Autowired
    VMService service;
    
    @GetMapping("/items")
    public List<Item> displayItems() throws VendingMachinePersistenceError, GetEntryError {
        return new ArrayList(service.getItems());
    }
    
    @GetMapping("/item")
    public Item getItem(String selection) throws GetEntryError {
        return service.getItem(selection);
    }
    
    @GetMapping("/money")
    public BigDecimal getMoney(String deposit) throws GettingMoneyError {
        return service.checkMoney(deposit);
    }
    
    @PostMapping("/purchase")
    public BigDecimal buyItem(String money, String selection) throws InsufficientFundsError, OutOfStockException, VendingMachinePersistenceError, GetEntryError {
        return service.processTransaction(money, selection);
    }
    
    @GetMapping("/change")
    public ChangeMaker makeChange(String money) throws InsufficientFundsError {
        return service.makeChange(new BigDecimal(money));
    }
}
