/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.vendingmachine.dao;

import com.mycompany.vendingmachine.dto.ChangeMaker;
import com.mycompany.vendingmachine.dto.Item;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.HashMap;
import java.util.Scanner;

/**
 *
 * @author chaseowens
 */
public class DaoImpl implements Dao {

    HashMap<String, Item> items = new HashMap<>();
    PrintWriter write;
    public static final String ITEM_INVENTORY = "inventory.txt";
    public static final String DELIMETER = "::";

    @Override
    public void updateInventory(HashMap<String, Item> currentItems) throws VendingMachinePersistenceError {
        try {
            write = new PrintWriter(new FileWriter(ITEM_INVENTORY));
        } catch (IOException e) {
            throw new VendingMachinePersistenceError("The inventory could not be updated, please contact manager.", e);
        }


        Collection<Item> itemsOnList = items.values();
        itemsOnList.stream().forEach(item -> {
            write.println(
                    item.getName() + DELIMETER
                    + item.getPrice().toString() + DELIMETER
                    + item.getInventoryCount()
            );
            write.flush();
        });

        write.close();

        // First attempt using enhanced for loop
//        for (Item item : itemsOnList) {
//            write.print(
//                    item.getName() + DELIMETER
//                    + item.getPrice().toString() + DELIMETER
//                    + item.getInventoryCount()
//            );
//            write.flush();
//        }
//        write.close();
    }

    @Override
    public void getInventory() throws VendingMachinePersistenceError {
        Scanner read;

        try {
            read = new Scanner(new BufferedReader(new FileReader(ITEM_INVENTORY)));
        } catch (FileNotFoundException e) {
            throw new VendingMachinePersistenceError("Sorry could not find inventory", e);
        }

        while (read.hasNextLine()) {
            String currentLine = read.nextLine();
            String currentTokens[];

            currentTokens = currentLine.split(DELIMETER);

            String name = currentTokens[0];
            BigDecimal price = new BigDecimal(currentTokens[1]);
            int inventoryCount = Integer.parseInt(currentTokens[2]);

            Item item = new Item(name, price, inventoryCount);
            items.put(name, item);
        }
        read.close();
    }

    @Override
    public Collection<Item> getItems() throws VendingMachinePersistenceError {
        items.clear();
        getInventory();
        Collection<Item> currentInventory = items.values();
        return currentInventory;
    }

    @Override
    public BigDecimal processTransaction(BigDecimal $, String selection) throws InsufficientFundsError, OutOfStockException, VendingMachinePersistenceError {
        String entry = $.toString();
        
        // Bad case could break - how can we handle this..
        Item item = items.get(selection);
        int inventoryCount = item.getInventoryCount();
        BigDecimal change;
        if ($.compareTo(item.getPrice()) < 0) {
            throw new InsufficientFundsError("Insufficient Funds");
        } else if (item.getInventoryCount() < 1) {
            throw new OutOfStockException("Sorry none of those left");
        } else {
            change = $.subtract(item.getPrice());
            item.setInventoryCount(inventoryCount - 1);
            updateInventory(items);
        }
        return change;
    }

    @Override
    public ChangeMaker makeChange(BigDecimal change) {
        ChangeMaker changeOwed = new ChangeMaker(change);
        return changeOwed;
    }

}
