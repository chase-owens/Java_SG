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
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
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

        if (products.get(productNameEntered) == null) {
            throw new DataValidationException("We don't have that product");
        }

        productDetails = products.get(productNameEntered);

        Product product = new Product(productNameEntered, productDetails[0], productDetails[1]);
        return product;

    }

    @Override
    public StateTax validateState(String stateEntered) throws DataValidationException {
        BigDecimal taxRate;
        if (states.get(stateEntered) == null) {
            throw new DataValidationException("We don't serve that state");
        }
        taxRate = states.get(stateEntered);

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

            if (currentLine.contains(".")) {
                state = currentTokens[0];
                taxRate = new BigDecimal(currentTokens[1]);
            }
            if (states != null) {
                states.put(state, taxRate);
            }

        }
        read.close();
    }

    private void loadProducts() throws FlooringMasteryPersistenceError {
        Scanner read;
        String product;
        BigDecimal costPerSquareFoot, laborPerSquareFoot;

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
                BigDecimal[] productCosts = new BigDecimal[2];
                product = currentTokens[0].toLowerCase();
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
    public PurchaseOrder addOrderToOrderMap(PurchaseOrder po) throws FlooringMasteryPersistenceError, DateNotFoundException {
        HashMap<String, PurchaseOrder> existingOrders;
        boolean hasExistingOrders = checkIfFileExists(po.getDate().toString());
        boolean ordersInMap = checkIfOrderExistsInMap(po.getDate());
        Set<Integer> orderNumbers;
        int maxOrderNumber;

//        if (hasExistingOrders && !ordersInMap) {
//            loadOrders(po.getDate().toString());
//            existingOrders = ordersMap.get(po.getDate());
//        }
        if (ordersInMap) {
            existingOrders = ordersMap.get(po.getDate());
        } else {
            existingOrders = new HashMap<>();
        }

        if (existingOrders.size() > 0) {
            Set<String> orderNumStrings = existingOrders.keySet();
            try {
                orderNumbers = orderNumStrings.stream().map(s -> Integer.parseInt(s)).collect(Collectors.toSet());
                maxOrderNumber = orderNumbers.stream().collect(Collectors.reducing(Integer::max)).get();
                String newMax = Integer.toString(maxOrderNumber + 1);
                po.setOrderNumber(newMax);
                existingOrders.put(newMax, po);
            } catch (NumberFormatException e) {
                throw new DateNotFoundException("The order numbers are out of whack!!");
            }
        } else {
            po.setOrderNumber("1");
            existingOrders.put(po.getOrderNumber(), po);
            ordersMap.put(po.getDate(), existingOrders);
        }
        return po;

    }

    public boolean checkIfFileExists(String date) throws FlooringMasteryPersistenceError {
        String[] dateWithoutDashes = date.split("-");

        String fileName = "Orders_" + dateWithoutDashes[0] + dateWithoutDashes[1] + dateWithoutDashes[2];
        String folderPath = "/Users/chaseowens/Desktop/bitbucket/chase-owens-individual-work/FlooringMastery/src/main/java/com/mycompany/flooringmastery/orders/" + fileName + "/";
        File file = new File(folderPath + fileName + ".txt");

//        String order = "Orders_" + dateWithoutDashes[0] + dateWithoutDashes[1] + dateWithoutDashes[2];
//        File file = new File(order + ".txt");
        boolean fileExists;

        try {
            fileExists = file.exists();
        } catch (SecurityException e) {
            throw new FlooringMasteryPersistenceError("Security flagged this", e);
        }
        return fileExists;
    }

    public boolean checkIfOrderExistsInMap(LocalDate date) throws FlooringMasteryPersistenceError {
        boolean orderExists = ordersMap.containsKey(date);
        return orderExists;
    }

    @Override
    public void loadOrders(String date) throws FlooringMasteryPersistenceError {
        boolean exists = checkIfFileExists(date);
        LocalDate ld = LocalDate.parse(date, DateTimeFormatter.ofPattern("MM-dd-yyyy"));
        boolean inMap = checkIfOrderExistsInMap(ld);

        if (exists && !inMap) {
            String[] dateWithoutDashes = date.split("-");

            String fileName = "Orders_" + dateWithoutDashes[0] + dateWithoutDashes[1] + dateWithoutDashes[2];
            String folderPath = "/Users/chaseowens/Desktop/bitbucket/chase-owens-individual-work/FlooringMastery/src/main/java/com/mycompany/flooringmastery/orders/" + fileName + "/";
            File file = new File(folderPath + fileName + ".txt");

            //File file = new File("Orders_" + dateWithoutDashes[0] + dateWithoutDashes[1] + dateWithoutDashes[2] + ".txt");
            HashMap<String, PurchaseOrder> currentOrders = new HashMap<>();

            Scanner read;

            try {
                read = new Scanner(new BufferedReader(new FileReader(file)));
            } catch (FileNotFoundException e) {
                throw new FlooringMasteryPersistenceError("File not found", e);
            }

            while (read.hasNextLine()) {
                Gson gson = new Gson();
                String json = read.nextLine();
                PurchaseOrder order = gson.fromJson(json, PurchaseOrder.class);
                currentOrders.put(order.getOrderNumber(), order);
            }
            ordersMap.put(ld, currentOrders);
        }

    }

    @Override
    public HashMap<String, PurchaseOrder> getOrders(String date) throws FlooringMasteryPersistenceError, DateNotFoundException {
        String[] dateWithoutDashes = date.split("-");
        HashMap<String, PurchaseOrder> orders;
        LocalDate ld = LocalDate.parse(date, DateTimeFormatter.ofPattern("MM-dd-yyyy"));

        // Name file
        String fileName = "Orders_" + dateWithoutDashes[0] + dateWithoutDashes[1] + dateWithoutDashes[2];
        String folderPath = "/Users/chaseowens/Desktop/bitbucket/chase-owens-individual-work/FlooringMastery/src/main/java/com/mycompany/flooringmastery/orders/";
        File folder = new File(folderPath + fileName);
        File file = new File(folder + "/" + fileName + ".txt");

        // Check if file exists
        boolean fileExists = checkIfFileExists(date);
        boolean ordersInMap = checkIfOrderExistsInMap(ld);

        if (fileExists && !ordersInMap) {
            loadOrders(date);
            orders = ordersMap.get(ld);
        } else if (ordersInMap) {
            orders = ordersMap.get(ld);
        } else {
            throw new DateNotFoundException("Could not find that date");
        }

        if (ordersMap.get(ld).isEmpty()) {
            throw new DateNotFoundException("Could not find that date");
        }
        return orders;

    }

    @Override
    public Set<String> getStatesServiced() {
        return states.keySet();
    }

    @Override
    public Set<String> getProductsOffered() {
        return products.keySet();
    }

    @Override
    public void removeOrder(String date, String orderNumber) throws FlooringMasteryPersistenceError, DateNotFoundException {
        HashMap<String, PurchaseOrder> currentOrders = getOrders(date);
        currentOrders.remove(orderNumber);
    }

    @Override
    public PurchaseOrder getOrder(String date, String orderNumber) throws FlooringMasteryPersistenceError, DateNotFoundException, DataValidationException {
        HashMap<String, PurchaseOrder> currentOrders = getOrders(date);
        PurchaseOrder po;
        try {
            po = currentOrders.get(orderNumber);
        } catch (NullPointerException e) {
            throw new DataValidationException("That order number does not exist");
        }

        return po;
    }

    @Override
    public PurchaseOrder updatePO(String name, Product product, StateTax stateTaxRate, String area, PurchaseOrder po) {
        if (!po.getCustomerName().equals(name)) {
            po.setCustomerName(name);
        }
        if (!po.getProductType().equals(product.getProduct())) {
            po.setProductType(product.getProduct());
            po.setMaterialCostPerSquareFoot(product.getCostPerSquareFoot());
            po.setLaborCostPerSquareFoot(product.getLaborPerSquareFoot());
        }
        if (!po.getState().equals(stateTaxRate.getState())) {
            po.setState(stateTaxRate.getState());
            po.setTaxRate(stateTaxRate.getTaxRate());
        }
        String areaString = po.getArea().toString();
        if (!areaString.equals(area)) {
            po.setArea(new BigDecimal(area));
        }
        // update laborCost, materialCost, tax, total
        po.updatePODetails();
        return po;
    }

    @Override
    public void saveOrders() throws FlooringMasteryPersistenceError {
        Gson gson = new Gson();

        Set<LocalDate> keys = ordersMap.keySet();

        for (LocalDate key : keys) {
            PrintWriter write;
            DateTimeFormatter df = DateTimeFormatter.ofPattern("MM-dd-yyyy");
            String stringDate = key.format(df);
            boolean fileExists = checkIfFileExists(stringDate);
            String[] stringDateWithoutDashes = stringDate.split("-");
            String orders = "Orders_" + stringDateWithoutDashes[0] + stringDateWithoutDashes[1] + stringDateWithoutDashes[2];
            String folderPath = "/Users/chaseowens/Desktop/bitbucket/chase-owens-individual-work/FlooringMastery/src/main/java/com/mycompany/flooringmastery/orders/";
            File folder = new File(folderPath + orders);
            File file = new File(folder + "/" + orders + ".txt");
            HashMap<String, PurchaseOrder> ordersToWrite = ordersMap.get(key);

            if (fileExists) {
                if (ordersToWrite.isEmpty()) {
                    file.delete();
                    folder.delete();
                } else {
                    try {
                        write = new PrintWriter(new FileWriter(file, true));
                    } catch (IOException e) {
                        throw new FlooringMasteryPersistenceError("The inventory could not be updated. Please contact your manager", e);
                    }

                    Collection<PurchaseOrder> actualOrders = ordersToWrite.values();
                    actualOrders.stream().forEach(order -> {
                        write.println(gson.toJson(order));
                        write.flush();

                    });
                    write.close();
                }
            } else {

                if (ordersToWrite.isEmpty()) {
                    file.delete();
                    folder.delete();
                } else {
                    try {
                        folder.mkdir();
                        file.createNewFile();
                        write = new PrintWriter(new FileWriter(file));
                    } catch (IOException e) {
                        throw new FlooringMasteryPersistenceError("The inventory could not be updated. Please contact your manager", e);
                    }
                    Collection<PurchaseOrder> actualOrders = ordersToWrite.values();
                    actualOrders.stream().forEach(order -> {
                        write.println(gson.toJson(order));
                        write.flush();
                    });
                    write.close();
                }

            }

        }
    }
}
