package com.example.CarDealership;

import com.example.CarDealership.controller.CarDealershipRESTController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CarDealershipApplication {
    
    CarDealershipRESTController controller;
    
    @Autowired
    public CarDealershipApplication(CarDealershipRESTController controller) {
        this.controller = controller;
    }

	public static void main(String[] args) {
		SpringApplication.run(CarDealershipApplication.class, args);
	}

}

