package com.example.CarDealership;

import com.example.CarDealership.controller.ContactRESTController;
import com.example.CarDealership.controller.MakeRESTController;
import com.example.CarDealership.controller.ModelRESTController;
import com.example.CarDealership.controller.ProfileRESTController;
import com.example.CarDealership.controller.PurchaseRESTController;
import com.example.CarDealership.controller.SpecialRESTController;
import com.example.CarDealership.controller.UserRESTController;
import com.example.CarDealership.controller.VehicleRESTController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CarDealershipApplication {
    
    ProfileRESTController profileController;
    ContactRESTController contactController;
    UserRESTController userController;
    MakeRESTController makeController;
    ModelRESTController modelController;
    VehicleRESTController vehicleController;
    SpecialRESTController specialController;
    PurchaseRESTController purchaseController;

    @Autowired
    public CarDealershipApplication(ProfileRESTController profileController, ContactRESTController contactController, UserRESTController userController, MakeRESTController makeController, ModelRESTController modelController, VehicleRESTController vehicleController, SpecialRESTController specialController, PurchaseRESTController purchaseController) {
        this.profileController = profileController;
        this.contactController = contactController;
        this.userController = userController;
        this.makeController = makeController;
        this.modelController = modelController;
        this.vehicleController = vehicleController;
        this.specialController = specialController;
        this.purchaseController = purchaseController;
    }

    public static void main(String[] args) {
        SpringApplication.run(CarDealershipApplication.class, args);
    }

}
