
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
public class FactorizorObject {

    public static int determineFactorSum(int num) {
        int factorSum = 0;
        for (int i = 1; i < num; i++) {
            if (num % i == 0) {
                factorSum += i;

            }
        }
        return factorSum;
    }

    public static boolean isPrime(int num) {
        boolean prime;
        if (determineFactorSum(num) == 1) {
            prime = true;
        } else {
            prime = false;
        }
        return prime;
    }

    public static boolean isPerfect(int num) {
        boolean perfect;
        if (determineFactorSum(num) == num) {
            perfect = true;
        } else {
            perfect = false;
        }
        return perfect;
    }
    
     public static int determineFactorCount(int num) {
        int count = 0;
        for (int i = 1; i < num; i++) {
            if (num % i == 0) {
                count += 1;
            }
        }
        return count;
    }
     
     public static int[] getFactorList(int num) {
         int[] factors = new int[determineFactorCount(num)];
         int count = 0;
         for (int i = 1; i < num; i++) {
            if (num % i == 0) {
                factors[count] = i;
                count += 1;
            }
        }
         return factors;
     }
    
}
