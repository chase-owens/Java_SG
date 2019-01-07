package com.example.BlockBusters;

import com.example.BlockBusters.controller.BrandRESTController;
import com.example.BlockBusters.controller.ContactRESTController;
import com.example.BlockBusters.controller.CustomRESTController;
import com.example.BlockBusters.controller.ProfileRESTController;
import com.example.BlockBusters.controller.PurchaseRESTController;
import com.example.BlockBusters.controller.ShoeRESTController;
import com.example.BlockBusters.controller.SpecialRESTController;
import com.example.BlockBusters.controller.UserRESTController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BlockBustersApplication {
    
    ProfileRESTController profileController;
    ContactRESTController contactController;
    UserRESTController userController;
    BrandRESTController brandController;
    ShoeRESTController shoeController;
    CustomRESTController customController;
    SpecialRESTController specialController;
    PurchaseRESTController purchaseController;

    @Autowired
    public BlockBustersApplication(ProfileRESTController profileController, ContactRESTController contactController, UserRESTController userController, BrandRESTController brandController, ShoeRESTController shoeController, CustomRESTController customController, SpecialRESTController specialController, PurchaseRESTController purchaseController) {
        this.profileController = profileController;
        this.contactController = contactController;
        this.userController = userController;
        this.brandController = brandController;
        this.shoeController = shoeController;
        this.customController = customController;
        this.specialController = specialController;
        this.purchaseController = purchaseController;
    }

	public static void main(String[] args) {
		SpringApplication.run(BlockBustersApplication.class, args);
	}

}

