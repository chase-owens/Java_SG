
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
public class SimpleSort {

    public static void main(String[] args) {
        int[] firstHalf = {3, 7, 9, 10, 16, 19, 20, 34, 55, 67, 88, 99};
        int[] secondHalf = {1, 4, 8, 11, 15, 18, 21, 44, 54, 79, 89, 100};
        
        System.out.println(Arrays.toString(sortArrays(firstHalf, secondHalf)));
    }

    public static int[] sortArrays(int[] array1, int[] array2) {
        int[] wholeNumbers = new int[array1.length + array2.length];
        int count = 0;
        for (int i = 0; i < array1.length; i++) {
            if (array1[i] < array2[i]) {
                wholeNumbers[count] = array1[i];
                count += 1;
                wholeNumbers[count] = array2[i];
            } else {
                wholeNumbers[count] = array2[i];
                count += 1;
                wholeNumbers[count] = array1[i];
            }
            count += 1;
        }
        return wholeNumbers;
    }
}
