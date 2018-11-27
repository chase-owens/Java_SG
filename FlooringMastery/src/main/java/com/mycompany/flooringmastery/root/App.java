/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.flooringmastery.root;

import com.mycompany.flooringmastery.dao.DataValidationException;
import com.mycompany.flooringmastery.dao.DateNotFoundException;
import com.mycompany.flooringmastery.dao.FlooringMasteryPersistenceError;
import com.mycompany.flooringmastery.mediator.Mediator;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author chaseowens
 */
public class App {

    public static void main(String[] args)  throws FlooringMasteryPersistenceError, DateNotFoundException, DataValidationException  {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        Mediator mediator = ctx.getBean("mediator", Mediator.class);
        mediator.selectMode();

    }
}
