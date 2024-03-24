package com.example.petstore.model;

import jakarta.persistence.*;
import lombok.Data;


@Data
@Entity
@Table(name = "customer")
public class Customer  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;
    private String email;

    @ManyToOne
    private Money budget;

    public Customer (String firstName, String lastName, String email, Money budget) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.budget = budget;
    }

    public Customer () {

    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public Money getBudget() {
        return budget;
    }

    public void setBudget(Money budget) {
        this.budget = budget;
    }

    public void setEmail(String email){
        this.email=email;
    }

    @Override
    public String toString() {
        return firstName + " " + lastName;
    }

}
