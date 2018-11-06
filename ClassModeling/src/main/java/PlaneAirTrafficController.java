/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author chaseowens
 */
public class PlaneAirTrafficController {
    private String destination;
    private String departureTime;
    private String gateNumber;
    private String runwayToTakeoffFrom;
    private String planeType;
    private boolean isBoarded;
    private boolean hasPushedOffFromGate;
    
    PlaneAirTrafficController() {
        
    }
    // So is the public/private distinction necessary when creating a constructor?
    public PlaneAirTrafficController(String destination) {
        this.destination = destination;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }

    public String getGateNumber() {
        return gateNumber;
    }

    public void setGateNumber(String gateNumber) {
        this.gateNumber = gateNumber;
    }

    public String getRunwayToTakeoffFrom() {
        return runwayToTakeoffFrom;
    }

    public void setRunwayToTakeoffFrom(String runwayToTakeoffFrom) {
        this.runwayToTakeoffFrom = runwayToTakeoffFrom;
    }

    public String getPlaneType() {
        return planeType;
    }

    public void setPlaneType(String planeType) {
        this.planeType = planeType;
    }

    public boolean isIsBoarded() {
        return isBoarded;
    }

    public void setIsBoarded(boolean isBoarded) {
        this.isBoarded = isBoarded;
    }

    public boolean isHasPushedOffFromGate() {
        return hasPushedOffFromGate;
    }

    public void setHasPushedOffFromGate(boolean hasPushedOffFromGate) {
        this.hasPushedOffFromGate = hasPushedOffFromGate;
    }
    
    
}
