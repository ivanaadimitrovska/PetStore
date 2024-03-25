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

    private final ServicePetStore servicePetStore;

    public RestControllerPetStore(ServicePetStore servicePetStore) {
        this.servicePetStore = servicePetStore;
    }

    @PostMapping("/buy/all")
    public void buyAll(){
        this.servicePetStore.buyPets();
    }

    @GetMapping("/historyLog")
    public List<BuyHistory> listHistoryLog(){
        return this.servicePetStore.historyLog();
    }

    @GetMapping("/list-users")
    public List<Customer> listUsers(){
        return this.servicePetStore.listUsers();
    }

    @GetMapping("/list-pets")
    public List<Pet> listPets(){
        return this.servicePetStore.listPets();
    }

    @PostMapping("/create-users")
    public void createUsers(){
        this.servicePetStore.createUsers();
    }

    @PostMapping("/create-pets")
    public void createPets(){
        this.servicePetStore.createPets();
    }
}
