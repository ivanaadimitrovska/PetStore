package com.example.petstore;


import com.example.petstore.enumerations.TypeEnum;
import com.example.petstore.model.*;
import com.example.petstore.repository.BuyHistoryRepository;
import com.example.petstore.repository.MoneyRepository;
import com.example.petstore.repository.PetRepository;
import com.example.petstore.repository.UserRepository;
import com.example.petstore.response.BuyResponse;
import com.example.petstore.service.ServicePetStore;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class PetServiceTest {

    @Mock
    private PetRepository petRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private BuyHistoryRepository buyHistoryRepository;

    @Mock
    private MoneyRepository moneyRepository;

    @InjectMocks
    private ServicePetStore petService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void testBuy() {
        Customer user = new Customer("John", "Doe", "john@example.com", new Money(200.0));
        Pet pet = new Pet();
        pet.setPrice(new Money(300.0));
        pet.setType(TypeEnum.DOG);

        when(userRepository.findByEmail("john@example.com")).thenReturn(user);

        BuyResponse response = petService.buy("john@example.com", pet);

        assertEquals("JohnDoe", response.getCustomerName());
        assertEquals("You can not buy this pet", response.getMessage());
        assertEquals(null, response.getPetName());


        verify(userRepository).findByEmail("john@example.com");

    }

    @Test
    public void testBuyPets() {
        List<Pet> pets = new ArrayList<>();
        List<Customer> users = new ArrayList<>();
        Customer user = new Customer("John", "Doe", "john@example.com", new Money(500.0));
        users.add(user);
        Pet pet1 = new Pet(user, "Description 1", null, new Money(100.0), TypeEnum.CAT, "Pet 1");
        Pet pet2 = new Pet(user, "Description 2", null, new Money(200.0), TypeEnum.DOG, "Pet 2");
        pets.add(pet1);
        pets.add(pet2);

        when(petRepository.findAllByOwnerIsNull()).thenReturn(pets);
        when(userRepository.findAll()).thenReturn(users);

        petService.buyPets();

        assertEquals(2, pets.size());
        assertEquals(user, pet1.getOwner());
        assertEquals(user, pet2.getOwner());
    }


    @Test
    void testHistoryLog() {
        List<BuyHistory> expectedHistory = new ArrayList<>();
        when(buyHistoryRepository.findAll()).thenReturn(expectedHistory);

        List<BuyHistory> actualHistory = petService.historyLog();

        assertEquals(expectedHistory, actualHistory);
    }

    @Test
    void testCreateUsers() {
        List<Customer> expectedUsers = new ArrayList<>();
        when(userRepository.save(any())).thenReturn(new Customer());
        when(userRepository.findAll()).thenReturn(expectedUsers);

        List<Customer> actualUsers = petService.createUsers();

        assertEquals(expectedUsers, actualUsers);
    }

    @Test
    void testCreatePets() {
        List<Pet> expectedPets = new ArrayList<>();
        when(petRepository.save(any())).thenReturn(new Pet());
        when(petRepository.findAll()).thenReturn(expectedPets);

        List<Pet> actualPets = petService.createPets();

        assertEquals(expectedPets, actualPets);
    }

    @Test
    void testListUsers() {
        List<Customer> expectedUsers = new ArrayList<>();
        when(userRepository.findAll()).thenReturn(expectedUsers);

        List<Customer> actualUsers = petService.listUsers();

        assertEquals(expectedUsers, actualUsers);
    }

    @Test
    void testListPets() {
        List<Pet> expectedPets = new ArrayList<>();
        when(petRepository.findAll()).thenReturn(expectedPets);

        List<Pet> actualPets = petService.listPets();

        assertEquals(expectedPets, actualPets);
    }


}
