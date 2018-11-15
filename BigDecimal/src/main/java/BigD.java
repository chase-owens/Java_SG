
import java.math.BigDecimal;
import java.math.RoundingMode;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author chaseowens
 */
public class BigD {
    public static void main(String[] args) {
        BigDecimal bd = new BigDecimal("100.01");
        BigDecimal nd = new BigDecimal(100.02);
        
        BigDecimal sum = bd.add(nd);
        System.out.println("BigD sum: " + sum);
        
        BigDecimal formattedSum = sum.setScale(0, RoundingMode.HALF_UP);
        System.out.println("Expect to have no scale: " + formattedSum);
        
        // Why is this converting it to a long?
        //BigDecimal sumFormatted = bd.add(nd, 2, RoundingMode.HALF_UP); ERROR
        
        BigDecimal one = BigDecimal.ONE;
        BigDecimal two = one.add(one);
        
        System.out.println(two);
        
        
    }
}
