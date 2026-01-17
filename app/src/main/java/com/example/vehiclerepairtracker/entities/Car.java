package com.example.vehiclerepairtracker.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Cars")
public class Car extends Vehicle {
    @PrimaryKey(autoGenerate = true)
    private int carId;
    private String year;
    private String make;
    private String model;
    private int doors;

    public Car(int carId, String year, String make, String model, int doors) {
        super(make, model);
        this.carId = carId;
        this.year = year;
        this.make = make;
        this.model = model;
        this.doors = doors;

    }

    public int getCarId() {
        return carId;
    }

    public void setCarId(int carId) {
        this.carId = carId;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getDoors() { return doors; }



    @Override
    public String getVehicleType() {
        return "Car";
    }
}
