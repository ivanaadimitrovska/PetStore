package com.example.petstore.controller;


import com.example.petstore.model.BuyHistory;
import com.example.petstore.model.Customer;
import com.example.petstore.model.Pet;
import com.example.petstore.service.ServicePetStore;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pet")
public class RestControllerPetStore {

    private final ServicePetStore petService;

    public RestControllerPetStore(ServicePetStore petService) {
        this.petService = petService;
    }

    @PostMapping("/buy/all")
    public void buyAll(){
        this.petService.buyPets();
    }

    @GetMapping("/historyLog")
    public List<BuyHistory> listHistoryLog(){
        return this.petService.historyLog();
    }

    @GetMapping("/list-users")
    public List<Customer> listUsers(){
        return this.petService.listUsers();
    }

    @GetMapping("/list-pets")
    public List<Pet> listPets(){
        return this.petService.listPets();
    }

    @PostMapping("/create-users")
    public void createUsers(){
        this.petService.createUsers();
    }

    @PostMapping("/create-pets")
    public void createPets(){
        this.petService.createPets();
    }
}
