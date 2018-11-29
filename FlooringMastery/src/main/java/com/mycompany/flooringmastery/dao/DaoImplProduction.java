/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.flooringmastery.dao;

import com.google.gson.Gson;
import com.mycompany.flooringmastery.model.Product;
import com.mycompany.flooringmastery.model.PurchaseOrder;
import com.mycompany.flooringmastery.model.StateTax;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

/**
 *
 * @author chaseowens
 */
public class DaoImplProduction implements Dao {

    HashMap<String, BigDecimal[]> products = new HashMap<>();
    HashMap<String, BigDecimal> states = new HashMap<>();
    HashMap<LocalDate, HashMap<String, PurchaseOrder>> ordersMap = new HashMap<>();

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
    public StateTax validateState(String stateEntered) throws DataValidationException {
        BigDecimal taxRate;
        try {
            taxRate = states.get(stateEntered);
        } catch (UnsupportedOperationException e) {
            throw new DataValidationException("We don't serve that state", e);
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
        PurchaseOrder po = new PurchaseOrder(name, product, stateTaxRate, area, date);
        return po;
    }

    @Override
    public void addOrderToOrderMap(PurchaseOrder po) throws FlooringMasteryPersistenceError, DateNotFoundException {
        HashMap<String, PurchaseOrder> existingOrders = new HashMap<>();
        boolean hasExistingOrders = checkIfFileExists(po.getDate().toString());
        Set<Integer> orderNumbers;
        int maxOrderNumber;

        if (hasExistingOrders) {
            existingOrders = getOrders(po.getDate().toString());
            Set<String> orderNumStrings = existingOrders.keySet();
            try {
                orderNumbers = orderNumStrings.stream().map(s -> Integer.parseInt(s)).collect(Collectors.toSet());
                maxOrderNumber = orderNumbers.stream().collect(Collectors.reducing(Integer::max)).get();
                String newMax = Integer.toString(maxOrderNumber + 1);
                existingOrders.put(newMax, po);
                ordersMap.put(po.getDate(), existingOrders);
            } catch (NumberFormatException e) {
                throw new DateNotFoundException("The order numbers are out of whack!!");
            }
        } else {
            existingOrders.put("1", po);
            ordersMap.put(po.getDate(), existingOrders);
        }

    }

    public boolean checkIfFileExists(String date) throws FlooringMasteryPersistenceError {
        String[] dateWithoutDashes = date.split("-");
        File file = new File("Orders_" + dateWithoutDashes[0] + dateWithoutDashes[1] + dateWithoutDashes[2] + ".txt");
        boolean fileExists;

        try {
            fileExists = file.exists();
        } catch (SecurityException e) {
            throw new FlooringMasteryPersistenceError("Security flagged this", e);
        }
        return fileExists;
    }

    @Override
    public HashMap<String, PurchaseOrder> getOrders(String date) throws FlooringMasteryPersistenceError, DateNotFoundException {
        String[] dateWithoutDashes = date.split("-");
        HashMap<String, PurchaseOrder> orders = new HashMap<>();
        LocalDate ld = LocalDate.parse(date);

        // Name file
        File file = new File("Orders_" + dateWithoutDashes[0] + dateWithoutDashes[1] + dateWithoutDashes[2] + ".txt");

        // Check if file exists
        boolean fileExists = checkIfFileExists(date);

        if (fileExists) {
            Scanner read;
            Gson gson = new Gson();

            try {
                read = new Scanner(new BufferedReader(new FileReader(file)));
            } catch (FileNotFoundException e) {
                throw new FlooringMasteryPersistenceError("File not found", e);
            }

            while (read.hasNextLine()) {
                PurchaseOrder order = gson.fromJson(read.nextLine(), PurchaseOrder.class);
                orders.put(order.getOrderNumber(), order);
            }
            return orders;
        } else if (ordersMap.containsKey(ld)) {
            orders = ordersMap.get(ld);
            return orders;
        } else {
            throw new DateNotFoundException("Sorry we don't have any orders for that date.");
        }

    }
}
