package com.example.vehiclerepairtracker.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.vehiclerepairtracker.entities.Car;

import java.util.List;

@Dao
public interface CarListDao {

    @Insert
    void insert(Car car);

    @Update
    void update(Car car);

    @Delete
    void delete(Car car);

    @Query("SELECT * FROM Cars" )
    List<Car> getAllCars();




}
