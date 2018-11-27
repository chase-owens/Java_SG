/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.flooringmastery.mediator;

import com.mycompany.flooringmastery.control.Controller;
import com.mycompany.flooringmastery.dao.DataValidationException;
import com.mycompany.flooringmastery.dao.DateNotFoundException;
import com.mycompany.flooringmastery.dao.FlooringMasteryPersistenceError;
import com.mycompany.flooringmastery.view.View;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author chaseowens
 */
public class Mediator {

    View view;

    public Mediator(View injectedView) {
        this.view = injectedView;
    }

    public void selectMode()  throws FlooringMasteryPersistenceError, DateNotFoundException, DataValidationException  {

        boolean productionMode = false;
        boolean validEntry = false;
        String selection;

        while (!validEntry) {
            try {

                selection = view.askIfUserWantsProduction();

                if (selection.toLowerCase().startsWith("t")) {
                    productionMode = false;
                    validEntry = true;
                } else if (selection.toLowerCase().startsWith("p")) {
                    productionMode = true;
                    validEntry = true;
                } else {
                    throw new DataValidationException("Please enter 'T' for Training or 'P' for Production");
                }

            } catch (DataValidationException e) {
                view.handleError(e);
            }
        }

        runBuild(productionMode);
    }

    private void runBuild(boolean productionMode) throws FlooringMasteryPersistenceError, DateNotFoundException, DataValidationException  {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        if (productionMode == true) {
            Controller controller = ctx.getBean("controllerProduction", Controller.class);
            controller.run();
        } else {
            Controller controller = ctx.getBean("controllerTraining", Controller.class);
            controller.run();
        }
    }
}
