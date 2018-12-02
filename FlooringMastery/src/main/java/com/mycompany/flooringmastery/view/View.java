/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.flooringmastery.view;

import com.mycompany.flooringmastery.dao.DataValidationException;
import com.mycompany.flooringmastery.model.PurchaseOrder;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Collection;
import java.util.HashMap;
import java.util.Set;

/**
 *
 * @author chaseowens
 */
public class View {

    UserIO io;

    public View(UserIO injectedIO) {
        this.io = injectedIO;
    }

    public String askIfUserWantsProduction() {
        String selection = io.readString("Training or Production mode? Enter T or P");

        return selection;

    }

    public void handleError(Exception e) {
        io.print(e.getMessage());
    }

    public void sayGoodbye() {
        io.print("Thank you for your business :)");
    }

    public void displayMenu() {

        io.print("                          __                    __\n"
                + "          __       __     \\_\\  __          __   \\_\\  __   __       __\n"
                + "          \\_\\     /_/        \\/_/         /_/      \\/_/   \\_\\     /_/\n"
                + "        .-.  \\.-./  .-.   .-./  .-.   .-./  .-.   .-\\   .-.  \\.-./  .-.\n"
                + "       //-\\\\_//-\\\\_//-\\\\_//-\\\\_//-\\\\_//-\\\\_// \\\\_//-\\\\_//-\\\\_//-\\\\_//-\\\\\n"
                + "     __(   '-'   '-'\\  '-'   '-'  /'-'   '-'\\__'-'   '-'__/'-'   '-'\\__\n"
                + "    /_/))            \\__       __/\\          \\_\\       /_/           \\_\\\n"
                + " ___\\_//              \\_\\     /_/  \\__\n"
                + "/_/  ((                             \\_\\\n"
                + "      )) __\n"
                + "__   // /_/\n"
                + "\\_\\_((_/___\n"
                + "     ))  \\_\\\n"
                + "     \\\\" + "\t\t1. Display Orders\n"
                + "      )) _\n"
                + "__   // /_/\n"
                + "\\_\\_((" + "\t\t2. Add an Order\n"
                + "     \\\\\n"
                + "      )) __\n"
                + "__   // /_/" + "\t3. Edit an Order\n"
                + "\\_\\_((_/___\n"
                + "     ))  \\_\\\n"
                + "     \\\\" + "\t\t4. Remove an Order\n"
                + "      )) _\n"
                + "__   // /_/\n"
                + "\\_\\_((_/" + "\t5. Save Current Work\n"
                + "     \\\\\n"
                + "      )) __\n"
                + "__   // /_/" + "\t6. Quit\n"
                + "\\_\\_((_/___\n"
                + "     ))  \\_\\\n"
                + "     \\\\\n"
                + "      )) _\n"
                + "__   // /_/\n"
                + "\\_\\_((_/___\n"
                + "     ))  \\_\\                __                    __\n"
                + "     \\\\     __       __     \\_\\  __          __   \\_\\  __   __       __\n"
                + "  __  ))    \\_\\     /_/        \\/_/         /_/      \\/_/   \\_\\     /_/\n"
                + "  \\_\\_((   .-.  \\.-./  .-.   .-./  .-.   .-./  .-.   .-\\   .-.  \\.-./  .-.\n"
                + "       \\\\_//-\\\\_//-\\\\_//-\\\\_//-\\\\_//-\\\\_//-\\\\_// \\\\_//-\\\\_//-\\\\_//-\\\\_//-\\\\\n"
                + "        'dc\\__'-'   '-'\\  '-'   '-'  /'-'   '-'\\__'-'   '-'__/'-'   '-'\\__\n"
                + "            \\_\\         \\__       __/\\          \\_\\       /_/           \\_\\\n"
                + "                         \\_\\     /_/  \\__\n"
                + "                                       \\_\\\n"
                + "\n"
                + "\t\t\t\t\tArt from https://asciiart.website\n");
    }

    public int getSelection() throws DataValidationException {
        int selection = 0;
        selection = io.readInt("Select an option by entering the number", 1, 6);
        return selection;
    }

    public String getDateOfOrder() {
        String date = io.readString("Enter date order was scheduled to be fulfilled in 'mm-dd-yyyy' format");
        return date;
    }

    public String[] getOrderInfo(Set<String> states, Set<String> products) throws DataValidationException {
        String[] info = new String[4];
        String[] userInfo = getUserInfo(states);
        info[0] = userInfo[0];
        info[1] = userInfo[1];
        displayProducts(products);
        info[2] = io.readString("Enter product").toLowerCase();
        BigDecimal area = io.readBigDecimal("Enter area product will cover");
        info[3] = area.toString();
        return info;
    }
    
    public String[] getUserInfo(Set<String> states) {
        String[] userInfo = new String[2];
        userInfo[0] = io.readString("Enter name");
        displayStates(states);
        userInfo[1] = io.readString("Enter state 2-letter abbreviation").toUpperCase();
        return userInfo;

    }
    
    private String[] updateUserInfo(PurchaseOrder po, Set<String> states) {
        String[] userInfo = new String[2];
        userInfo[0] = io.readString("Enter name " + "("+po.getCustomerName()+")");
        displayStates(states);
        userInfo[1] = io.readString("Enter state 2-letter abbreviation " + "("+po.getState())+")";
        return userInfo;
    }
    
    public String[] getNewOrderInfo(PurchaseOrder po, Set<String> states, Set<String> products) throws DataValidationException {
        String[] info = new String[4];
        String[] userInfo = updateUserInfo(po, states);
        info[0] = userInfo[0];
        info[1] = userInfo[1];
        displayProducts(products);
        info[2] = io.readString("Enter product " + "("+po.getProductType()+")").toLowerCase();
        BigDecimal area = io.readBigDecimal("Enter area product will cover " + "("+po.getArea().toString()+")");
        info[3] = area.toString();
        return info;
    }

    public boolean placeOrder(PurchaseOrder po) {
        displayOrderInformation(po);
        boolean confirm = getDecision();
        return confirm;
    }

    public void displayOrderInformation(PurchaseOrder po) {
        io.print(po.getCustomerName());
        io.print(po.getState() + "Tax rate: " + po.getTaxRate());
        io.print("Material type: " + po.getProductType() + "\nMaterial cost per square foot: " + po.getMaterialCostPerSquareFoot().toString() + "\tLabor cost per square foot " + po.getLaborCostPerSquareFoot().toString());
        io.print("Area: " +po.getArea().toString());
        io.print("Material cost: " + po.getMaterialCost().toString() + "\tLabor cost: " + po.getLaborCost().toString());
        io.print("Subtotal: " + po.getSubTotal().setScale(2, RoundingMode.HALF_UP).toString());
        io.print("Tax: " + po.getTax().setScale(2, RoundingMode.HALF_UP).toString());
        io.print("Total: " + po.getTotal().setScale(2, RoundingMode.HALF_UP).toString());
        io.print("Date scheduled: " + po.getDate().toString() + "\n");
    }

    private boolean getDecision() {
        boolean validEntry = false, decision = false;
        String userEntry;

        while (!validEntry) {
            userEntry = io.readString("Confirm order Y/N").toUpperCase();
            if (userEntry.startsWith("Y")) {
                decision = true;
                validEntry = true;
            } else if (userEntry.startsWith("N")) {
                decision = false;
                validEntry = true;
            } else {
                io.print("Simply enter 'Y' for yes or 'N' for no");
            }
        }
        return decision;
    }

    public void printOrders(HashMap<String, PurchaseOrder> existingOrders) {
        Collection<PurchaseOrder> orders = existingOrders.values();
        orders.stream().forEach(po -> {
            io.print("\nOrder number: " + po.getOrderNumber());
            displayOrderInformation(po);
        });
    }

    private void displayStates(Set<String> states) {
        io.print("States we service:");
        states.stream().forEach(state -> {
            if (state != null) {
              io.print(state);  
            }
                    
        });
    }

    private void displayProducts(Set<String> products) {
        io.print("Materials we offer:");
        products.stream().forEach(product -> io.print(product));
    }

    public void displayConfirmation(PurchaseOrder po) {
        io.print("On " + po.getDate().toString() + " your order of " + po.getProductType() + " for " + po.getArea().toString() + " will be serviced.");
        io.print("Thank you for your business " + po.getCustomerName());
    }

    public String getOrderNumber() throws DataValidationException {
        int orderNumber = io.readInt("Enter order number of PurchaeOrder");
        String orderNumberString = Integer.toString(orderNumber);
        return orderNumberString;
    }

    public void confirmOrderRemoved(String orderNumber) {
        io.print(orderNumber + " was removed.");
    }

    public String getProductType(Set<String> products) {
        displayProducts(products);
        String product = io.readString("Enter product").toLowerCase();
        return product;
    }

    public String getUserName() {
        return io.readString("Enter name");
    }

    public String getUserState(Set<String> states) {
        displayStates(states);
        return io.readString("Enter state 2-letter abbreviation").toUpperCase();
    }

    public BigDecimal getUserArea() throws DataValidationException {
        return io.readBigDecimal("Enter area product will cover");
    }

    public String getUserNewName(PurchaseOrder po) {
        String name = io.readString("Enter name " + "("+po.getCustomerName()+")");
        return name;
    }

    public BigDecimal getNewUserArea(PurchaseOrder po) throws DataValidationException {
        BigDecimal area = io.readBigDecimalWithPossibilityOfNull("Enter area product will cover " + "("+po.getArea().toString()+")");
        return area;
    }

    public String getNewUserState(Set<String> states, PurchaseOrder po) {
        displayStates(states);
        String state = io.readString("Enter state 2-letter abbreviation " + "("+po.getState()+")").toUpperCase();
        return state;
    }

    public String getNewProductType(Set<String> products, PurchaseOrder po) {
        String product = io.readString("Enter product " + "("+po.getProductType()+")").toLowerCase();
        return product;
    }

    public void informUserOrderTooSmall() {
        io.print("Hmm... That's more of a do it yourself project!");
        io.print("We will have to charge you for an order of at least 10 square feet");
    }

    public void confirmOrderSaved() {
        io.print("Your current work has been saved");
    }
    
}
