/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author chaseowens
 */
public class MenuOfChampions {
    public static void main(String[] args) {
        String restaurantName, item1, item2, item3;
        float item1Price, item2Price, item3Price;
        
        restaurantName = "The Double Up";
        item1 = "Double Cheesburger";
        item2 = "Double Chicken Pot Pie";
        item3 = "Ribeye with Double Potatos";
        
        item1Price = 12.99f;
        item2Price = 14.99f;
        item3Price = 24.99f;
        
        System.out.println(".oOo.oOo.oOo.oOo.oOo.oOo.oOo.oOo.oOo.oOo.oOo.oOo.oOo.oOo.oOo.");
        System.out.println("WELCOME TO RESTAURANT " + restaurantName.toUpperCase());
        System.out.println("Today's menu is...");
        System.out.println(".oOo.oOo.oOo.oOo.oOo.oOo.oOo.oOo.oOo.oOo.oOo.oOo.oOo.oOo.oOo.");
        
        System.out.print(String.format("%-20s", item1));
        System.out.println(String.format("%-20s", item1Price));
        
        System.out.print(String.format("%-20s", item2));
        System.out.println(String.format("%-20s", item2Price));
        
        System.out.print(String.format("%-20s", item3));
        System.out.println(String.format("%-20s", item3Price));
        
    }
}
