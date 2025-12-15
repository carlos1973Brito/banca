package com.example.demo;

import com.example.demo.PetDTO;

public class PetResponse {

    private PetDTO pet;
    private String dateCreated;
    private String transactionId;

    public PetResponse(PetDTO pet, String dateCreated, String transactionId) {
        this.pet = pet;
        this.dateCreated = dateCreated;
        this.transactionId = transactionId;
    }

    public PetDTO getPet() {
        return pet;
    }

    public String getDateCreated() {
        return dateCreated;
    }
    public String getTransactionId() {
        return transactionId;
    }
}
