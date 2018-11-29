/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.flooringmastery.view;

import com.mycompany.flooringmastery.model.PurchaseOrder;
import java.math.RoundingMode;
import java.util.Collection;
import java.util.HashMap;

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

    public int getSelection() {
        int selection = io.readInt("Select an option by entering the number", 1, 6);
        return selection;
    }

    public String getDateOfOrder() {
        String date = io.readString("Enter date order was scheduled to be fulfilled in 'mm-dd-yyyy' format");
        return date;
    }

    public String[] getOrderInfo() {
        String[] info = new String[4];
        String[] userInfo = getUserInfo();
        info[0] = userInfo[0];
        info[1] = userInfo[1];
        info[2] = io.readString("Enter product");
        info[3] = io.readString("Enter area product will cover");
        return info;
    }

    public String[] getUserInfo() {
        String[] userInfo = new String[2];
        userInfo[0] = io.readString("Enter name");
        userInfo[1] = io.readString("Enter state 2-letter abbreviation").toUpperCase();
        return userInfo;

    }

    public boolean placeOrder(PurchaseOrder po) {
        displayOrderInformation(po);
        boolean confirm = getDecision();
        return confirm;
    }

    public void displayOrderInformation(PurchaseOrder po) {
        io.print(po.getCustomerName());
        io.print(po.getState() + "Tax rate: " + po.getTaxRate());
        io.print("Material type: " + po.getProductType() + "\nMaterial cost per square foot: " + po.getMaterialCostPerSquareFoot().toString() + "Labor cost per square foot" + po.getMaterialCostPerSquareFoot().toString());
        io.print(po.getArea().toString());
        io.print("Material cost: " + po.getMaterialCost().toString() + "\tLabor cost: " + po.getLaborCost().toString());
        io.print("Subtotal: " + po.getSubTotal().setScale(2, RoundingMode.HALF_UP).toString());
        io.print("Tax: " + po.getTax().setScale(2, RoundingMode.HALF_UP).toString());
        io.print("Total: " + po.getTotal().setScale(2, RoundingMode.HALF_UP).toString());
        io.print("Date scheduled: " + po.getDate().toString());
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
            io.print(po.getOrderNumber());
            displayOrderInformation(po);
        });
    }
}
