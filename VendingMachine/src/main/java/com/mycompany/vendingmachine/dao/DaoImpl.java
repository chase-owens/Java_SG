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
                    item.getName()+ DELIMETER
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
            throw new VendingMachinePersistenceError("Inventory Error", e);
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
    public BigDecimal processTransaction(BigDecimal money, String selection) throws InsufficientFundsError, OutOfStockException, VendingMachinePersistenceError, GetEntryError {

        Item item = null;
        int inventoryCount;
        try {
            item = items.get(selection);
            inventoryCount = item.getInventoryCount();
        } catch (NullPointerException e) {
            throw new GetEntryError("Please enter the item as it appears in the display.");
        }
        //Item item = items.get(selection);
        //inventoryCount = item.getInventoryCount();
        
        BigDecimal change;
        if (item.getInventoryCount() < 1) {
            throw new OutOfStockException("Out of stock");
        } else if (money.compareTo(item.getPrice()) < 0) {
            throw new InsufficientFundsError("Insufficient funds");
        } else {
            change = money.subtract(item.getPrice());
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

    @Override
    public Item getItem(String selection) {
        return items.get(selection);
    }

    @Override
    public BigDecimal checkMoney(String amountPaid) throws GettingMoneyError {
        BigDecimal amountPaidBD;

        try {
            amountPaidBD = new BigDecimal(amountPaid);
        } catch (IllegalArgumentException e) {
            throw new GettingMoneyError("Please enter a real number without any letters or commas", e);
        }

        if (amountPaidBD.compareTo(BigDecimal.ZERO) < 1) {
            throw new GettingMoneyError("Try that again and I will call the police and send them a photo of you...");
        } else if (amountPaidBD.compareTo(new BigDecimal("2125000000")) > 0) {
            throw new GettingMoneyError("I see you big baller... That's so such money we don't know what to do with it. Please enter less than 2,125,000,000");
        } else {
            return amountPaidBD;
        }
    }

}
