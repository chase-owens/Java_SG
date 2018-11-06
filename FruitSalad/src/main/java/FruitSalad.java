
import java.util.Arrays;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author chaseowens
 */
public class FruitSalad {

    public static void main(String[] args) {
        String[] fruits = {"Kiwi Fruit", "Gala Apple", "Granny Smith Apple", "Cherry Tomato", "Gooseberry", "Beefsteak Tomato", "Braeburn Apple", "Blueberry", "Strawberry", "Navel Orange", "Pink Pearl Apple", "Raspberry", "Blood Orange", "Sungold Tomato", "Fuji Apple", "Blackberry", "Banana", "Pineapple", "Florida Orange", "Kiku Apple", "Mango", "Satsuma Orange", "Watermelon", "Snozzberry"};
        String[] fruitSalad;
        int berryCount = 0, appleCount = 0, orangeCount = 0, sizeOfSalad;
        boolean enoughApples = false, enoughOranges = false;

        for (String fruit : fruits) {
            if (fruit.contains("berry")) {
                berryCount += 1;
            }
            if (fruit.contains("Apple")) {
                appleCount += 1;
            }
            if (fruit.contains("Orange")) {
                orangeCount += 1;
            }
        }

        if (appleCount >= 3 && orangeCount >= 3) {
            fruitSalad = new String[berryCount + 6];
            sizeOfSalad = 12;
        } else if (appleCount < 3 && orangeCount >= 3) {
            fruitSalad = new String[berryCount + appleCount + 3];
            sizeOfSalad = berryCount + appleCount + 3;
        } else if (appleCount >= 3 && orangeCount < 3) {
            fruitSalad = new String[berryCount + orangeCount + 3];
            sizeOfSalad = berryCount + orangeCount + 3;
        } else {
            fruitSalad = new String[berryCount + appleCount + orangeCount];
            sizeOfSalad = berryCount + appleCount + orangeCount;
        }

        int fruitCount = 0, count = 0;
        appleCount = 0;
        orangeCount = 0;

        for (String fruit : fruits) {

            if (!fruit.contains("Tomato") && fruitCount < 12) {

                if (fruit.contains("Apple") && appleCount < 3) {
                    appleCount += 1;
                    fruitSalad[fruitCount] = fruit;
                    fruitCount += 1;
                } else if (fruit.contains("Orange") && orangeCount < 3) {
                    orangeCount += 1;
                    fruitSalad[fruitCount] = fruit;
                    fruitCount += 1;
                } else if ((fruit.contains("Orange") && orangeCount > 2) || (fruit.contains("Apple") && appleCount > 2)) {
                    continue;
                } else {
                    fruitSalad[fruitCount] = fruit;
                    fruitCount += 1;
                }
            }
        }

        System.out.println(Arrays.toString(fruitSalad));
    }
}
