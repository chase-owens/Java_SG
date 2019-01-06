package com.example.BlockBusters;

import com.example.BlockBusters.controller.BlockBustersRESTController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BlockBustersApplication {
    
    BlockBustersRESTController controller;
    
    @Autowired
    public BlockBustersApplication(BlockBustersRESTController controller) {
        this.controller = controller;
    }

	public static void main(String[] args) {
		SpringApplication.run(BlockBustersApplication.class, args);
	}

}

