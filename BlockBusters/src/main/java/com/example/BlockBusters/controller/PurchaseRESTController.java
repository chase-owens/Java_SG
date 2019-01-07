/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.BlockBusters.controller;

import com.example.BlockBusters.service.PurchaseService;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author chaseowens
 */
@RestController
public class PurchaseRESTController {
    PurchaseService service;
    public PurchaseRESTController(PurchaseService service) {
        this.service = service;
    }
}
