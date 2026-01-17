package com.example.vehiclerepairtracker.entities;

public abstract class Vehicle {
    private String make;
    private String model;

    public Vehicle(String make, String model) {
        this.make = make;
        this.model = model;
    }

    public String getMake() { return make; }
    public String getModel() { return model; }

    // Polymorphic method: each subclass will implement this
    public abstract String getVehicleType();
}