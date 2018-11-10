
import java.util.Collection;
import java.util.HashMap;
import java.util.Set;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author chaseowens
 */
public class App {
    public static void main(String[] args) {
        HashMap<String, Capital> capitalInfo = new HashMap<>();
        
        Capital montgomery = new Capital("Montgomery", 205000);
        Capital juneau = new Capital("Juneau", 31000);
        Capital phoenix = new Capital("Phoenix", 1445000);
        Capital littleRock = new Capital("Little Rock", 193000);
        
        capitalInfo.put("Alabama", montgomery);
        capitalInfo.put("Alaska", juneau);
        capitalInfo.put("Arizona", phoenix);
        capitalInfo.put("Arkansas", littleRock);
        
        Set<String> states = capitalInfo.keySet();
        Collection<Capital> capitals = capitalInfo.values();
        
        for (String state : states) {
            System.out.println(state);
        }
        
        for (Capital capital : capitals) {
            System.out.println(capital.getName() + " " + capital.getPopulation());
        }
    }
}
