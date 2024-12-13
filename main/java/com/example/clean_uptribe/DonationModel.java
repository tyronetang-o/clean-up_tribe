package com.example.clean_uptribe;


public class DonationModel {
    private int id;
    private String name;
    private double amount;
    private String contactNumber;

    public DonationModel(int id, String name, double amount, String contactNumber) {
        this.id = id;
        this.name = name;
        this.amount = amount;
        this.contactNumber = contactNumber;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getAmount() {
        return amount;
    }

    public String getContactNumber() {
        return contactNumber;
    }
}
