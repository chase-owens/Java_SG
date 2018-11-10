
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
public class StateCapital {
    public static void main(String[] args) {
        HashMap<String, String> stateCapitalPair = new HashMap<>();
        
        stateCapitalPair.put("Alabama", "Montgomery");
        stateCapitalPair.put("Alaska", "Juneau");
        stateCapitalPair.put("Arizona", "Montgomery");
        stateCapitalPair.put("Arkansas", "Little Rock");
        
        Set<String> states = stateCapitalPair.keySet();
        
        Collection<String> capitals = stateCapitalPair.values();
        
        for (String state : states) {
            System.out.println(state);
        }
        
        for (String capital : capitals) {
            System.out.println(capital);
        }
        
    }
}
