package com.example.petstore.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class BuyHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime dateOfExecution;
    private int usersSuccessfullyBought;
    private int usersNotAllowedToBuy;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDateOfExecution() {
        return dateOfExecution;
    }

    public void setDateOfExecution(LocalDateTime dateOfExecution) {
        this.dateOfExecution = dateOfExecution;
    }

    public int getUsersSuccessfullyBought() {
        return usersSuccessfullyBought;
    }

    public void setUsersSuccessfullyBought(int usersSuccessfullyBought) {
        this.usersSuccessfullyBought = usersSuccessfullyBought;
    }

    public int getUsersNotAllowedToBuy() {
        return usersNotAllowedToBuy;
    }

    public void setUsersNotAllowedToBuy(int usersNotAllowedToBuy) {
        this.usersNotAllowedToBuy = usersNotAllowedToBuy;
    }
}
