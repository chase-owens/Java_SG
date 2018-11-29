/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.flooringmastery.dao;

import com.mycompany.flooringmastery.model.Product;
import com.mycompany.flooringmastery.model.PurchaseOrder;
import com.mycompany.flooringmastery.model.StateTax;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Scanner;

/**
 *
 * @author chaseowens
 */
public class DaoImplTraining implements Dao {

    HashMap<String, BigDecimal[]> products = new HashMap<>();
    HashMap<String, BigDecimal> states = new HashMap<>();

    @Override
    public Product validateProduct(String productNameEntered) throws DataValidationException {
        BigDecimal[] productDetails;
        try {
            productDetails = products.get(productNameEntered);
        } catch (UnsupportedOperationException e) {
            throw new DataValidationException("We don't have that product", e);
        }
        
        Product product = new Product(productNameEntered, productDetails[0], productDetails[1]);
        return product;
        
    }

    @Override
    public StateTax validateState(String stateEntered) throws DataValidationException  {
        BigDecimal taxRate;
        try {
            taxRate = states.get(stateEntered);
        } catch (UnsupportedOperationException e) {
            throw new DataValidationException("We don't have that product", e);
        }
        
        StateTax stateTax = new StateTax(stateEntered, taxRate);
        return stateTax;
    }

    @Override
    public void loadFiles() throws FlooringMasteryPersistenceError {
        loadStates();
        loadProducts();
    }

    private void loadStates() throws FlooringMasteryPersistenceError {
        Scanner read;

        try {
            read = new Scanner(new BufferedReader(new FileReader("taxes.txt")));
        } catch (FileNotFoundException e) {
            throw new FlooringMasteryPersistenceError("File not found", e);
        }

        while (read.hasNextLine()) {
            String currentLine = read.nextLine();
            String currentTokens[];
            currentTokens = currentLine.split(",");
            String state = null;
            BigDecimal taxRate = null;

            if (currentTokens[0].length() == 2) {
                state = currentTokens[0];
            }

            if (currentTokens[1].contains(".")) {
                taxRate = new BigDecimal(currentTokens[1]);
            }

            states.put(state, taxRate);
        }
        read.close();
    }

    private void loadProducts() throws FlooringMasteryPersistenceError {
        Scanner read;
        String product;
        BigDecimal costPerSquareFoot, laborPerSquareFoot;
        BigDecimal[] productCosts = new BigDecimal[2];

        try {
            read = new Scanner(new BufferedReader(new FileReader("products.txt")));
        } catch (FileNotFoundException e) {
            throw new FlooringMasteryPersistenceError("File not found", e);
        }

        while (read.hasNextLine()) {
            String currentLine = read.nextLine();
            String currentTokens[];
            currentTokens = currentLine.split(",");

            if (currentLine.contains(".")) {
                product = currentTokens[0];
                costPerSquareFoot = new BigDecimal(currentTokens[1]);
                laborPerSquareFoot = new BigDecimal(currentTokens[2]);
                
                productCosts[0] = costPerSquareFoot;
                productCosts[1] = laborPerSquareFoot;
                products.put(product, productCosts);
            }
            
        }
        read.close();
    }

    @Override
    public PurchaseOrder createPurchaseOrder(String name, Product product, StateTax stateTaxRate, String area, LocalDate date) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void addOrderToOrderMap(PurchaseOrder po) throws FlooringMasteryPersistenceError, DateNotFoundException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public HashMap<String, PurchaseOrder> getOrders(String date) throws FlooringMasteryPersistenceError, DateNotFoundException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
