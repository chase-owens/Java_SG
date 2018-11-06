/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author chaseowens
 */
public class PlaneFlightSimulator {
    private String type; //lots of information about dash layout dependent on this
    private int numberOfSeatsInCockpit;
    private int cockpitSizeSquareFoot;
    private boolean hasAutoPilot;
    
    PlaneFlightSimulator() {
        
    }
    
    PlaneFlightSimulator(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getNumberOfSeatsInCockpit() {
        return numberOfSeatsInCockpit;
    }

    public void setNumberOfSeatsInCockpit(int numberOfSeatsInCockpit) {
        this.numberOfSeatsInCockpit = numberOfSeatsInCockpit;
    }

    public int getCockpitSizeSquareFoot() {
        return cockpitSizeSquareFoot;
    }

    public void setCockpitSizeSquareFoot(int cockpitSizeSquareFoot) {
        this.cockpitSizeSquareFoot = cockpitSizeSquareFoot;
    }

    public boolean isHasAutoPilot() {
        return hasAutoPilot;
    }

    public void setHasAutoPilot(boolean hasAutoPilot) {
        this.hasAutoPilot = hasAutoPilot;
    }
    
    
}
