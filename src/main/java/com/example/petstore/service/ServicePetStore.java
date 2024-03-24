package com.example.petstore.service;


import com.example.petstore.response.BuyResponse;
import com.example.petstore.enumerations.TypeEnum;
import com.example.petstore.model.BuyHistory;
import com.example.petstore.model.Customer;
import com.example.petstore.model.Money;
import com.example.petstore.model.Pet;

import com.example.petstore.repository.BuyHistoryRepository;
import com.example.petstore.repository.MoneyRepository;
import com.example.petstore.repository.PetRepository;
import com.example.petstore.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.*;

@Service
public class ServicePetStore {

    private final PetRepository petRepository;
    private final UserRepository userRepository;
    private final BuyHistoryRepository buyHistoryRepository;
    private int totalUsersSuccessfullyBought = 0;
    private int totalUsersNotAllowedToBuy = 0;
    private final MoneyRepository moneyRepository;

    public ServicePetStore(PetRepository petRepository, UserRepository userRepository, BuyHistoryRepository buyHistoryRepository, MoneyRepository moneyRepository) {
        this.petRepository = petRepository;
        this.userRepository = userRepository;
        this.buyHistoryRepository = buyHistoryRepository;
        this.moneyRepository = moneyRepository;
    }

    public BuyResponse buy(String email, Pet pet) {
        BuyResponse response = new BuyResponse();
        Customer user = this.userRepository.findByEmail(email);
        int usersSuccessfullyBought = 0;
        int usersNotAllowedToBuy = 0;
        Money money;
        if(user.getBudget().getValue() >=  pet.getPrice().getValue()) {
            pet.setOwner(user);
            money=new Money(user.getBudget().getValue() - pet.getPrice().getValue());
            this.moneyRepository.save(money);
            user.setBudget(money);
            this.userRepository.save(user);
            this.petRepository.save(pet);
            usersSuccessfullyBought++;
            if(pet.getType() == TypeEnum.CAT) {
                System.out.println("Meow, cat " + pet.getName() + " has owner " + user.getFirstName() + " " + user.getLastName());
                response.setMessage("Meow, cat " + pet.getName() + " has owner " + user.getFirstName() + " " + user.getLastName());
            }
            else{
                System.out.println("Woof, dog " + pet.getName() + " has owner " + user.getFirstName() + " " + user.getLastName());
                response.setMessage("Woof, dog " + pet.getName() + " has owner " + user.getFirstName() + " " + user.getLastName());
            }
        } else {
            usersNotAllowedToBuy++;
            System.out.println("You can not buy this pet");
            response.setMessage("You can not buy this pet");
        }
        response.setPetName(pet.getName());
        response.setCustomerName(user.getFirstName() + user.getLastName());
        this.totalUsersNotAllowedToBuy+=usersNotAllowedToBuy;
        this.totalUsersSuccessfullyBought+=usersSuccessfullyBought;
        return response;
    }

    public List<BuyHistory> historyLog(){
        return this.buyHistoryRepository.findAll();
    }

    public List<Customer> createUsers(){
          String[] FIRST_NAMES = {"Alice", "Bob", "Charlie", "David", "Eve", "Frank", "Grace", "Henry", "Ivy", "Jack"};
          String[] LAST_NAMES = {"Smith", "Johnson", "Williams", "Brown", "Jones", "Garcia", "Miller", "Davis", "Rodriguez", "Martinez"};
          String[] EMAIL_DOMAINS = {"gmail.com", "yahoo.com", "hotmail.com", "outlook.com"};
          double MIN_BUDGET = 100.0;
          double MAX_BUDGET = 1000.0;

        Random random = new Random();

        Set<String> usedEmails = new HashSet<>();

        for (int i = 0; i < 10; i++) {
            String firstName = FIRST_NAMES[random.nextInt(FIRST_NAMES.length)];
            String lastName = LAST_NAMES[random.nextInt(LAST_NAMES.length)];
            String email;
            do {
                email = firstName.toLowerCase() + "." + lastName.toLowerCase() + "@" + EMAIL_DOMAINS[random.nextInt(EMAIL_DOMAINS.length)];
            } while (usedEmails.contains(email));
            usedEmails.add(email);

            Money budget = new Money(MIN_BUDGET + (MAX_BUDGET - MIN_BUDGET) * random.nextDouble());
            this.moneyRepository.save(budget);

            Customer newCustomer = new Customer(firstName, lastName, email, budget);
            this.userRepository.save(newCustomer);
        }
        return this.userRepository.findAll();
    }

    public List<Pet> createPets() {
        Random random = new Random();
        int years;
        int rating;
        Money price;
        for (int i = 0; i < 20; i++) {
            Pet pet = new Pet();
            pet.setName("Pet" + i);
            pet.setType(TypeEnum.valueOf(random.nextBoolean() ? "CAT" : "DOG"));
            pet.setDescription("Description for Pet" + i);
            pet.setDateOfBirth(LocalDate.now().minusYears(random.nextInt(5)));
            if(pet.getType().equals(TypeEnum.CAT)) {
                years = Period.between(pet.getDateOfBirth(), LocalDate.now()).getYears();
                price = new Money((double) years);
                this.moneyRepository.save(price);
                pet.setPrice(price);
            }
            else if (pet.getType().equals(TypeEnum.DOG)) {
                 years = Period.between(pet.getDateOfBirth(), LocalDate.now()).getYears();
                 rating = random.nextInt(11);
                 pet.setRating(rating);
                 price = new Money((double) (years+rating));
                 this.moneyRepository.save(price);
                 pet.setPrice(price);
            }
            this.petRepository.save(pet);
        }
        return petRepository.findAll();
    }


    public List<Customer> listUsers() {
        return this.userRepository.findAll();
    }


    public List<Pet> listPets() {
        return this.petRepository.findAll();
    }


    public void buyPets(){
        List<Pet> pets = this.petRepository.findAllByOwnerIsNull();
        Pet pet;
        List<Customer> users = this.userRepository.findAll();

        while (petRepository.findFirstByOwnerIsNull() != null) {
            for (Customer user : users) {
                pet = petRepository.findFirstByOwnerIsNull();
                this.buy(user.getEmail(), pet);
            }
        }

        BuyHistory buyHistory = new BuyHistory();
        buyHistory.setDateOfExecution(LocalDateTime.now());
        buyHistory.setUsersSuccessfullyBought(totalUsersSuccessfullyBought);
        buyHistory.setUsersNotAllowedToBuy(totalUsersNotAllowedToBuy);
        this.buyHistoryRepository.save(buyHistory);

    }
}
