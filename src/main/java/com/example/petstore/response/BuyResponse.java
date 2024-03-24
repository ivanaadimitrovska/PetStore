package com.example.petstore.response;



public class BuyResponse {
    private String customerName;
    private String petName;
    private String message;

    public BuyResponse(String customerName, String petName) {
        this.customerName = customerName;
        this.petName = petName;
    }

    public BuyResponse() {

    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getPetName() {
        return petName;
    }

    public void setPetName(String petName) {
        this.petName = petName;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
