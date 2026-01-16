package com.example.vehiclerepairtracker.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.vehiclerepairtracker.entities.Repair;

import java.util.List;

@Dao
public interface RepairListDao {

    @Insert
    void insert(Repair repair);

    @Update
    void update(Repair repair);

    @Delete
    void delete(Repair repair);

    @Query("SELECT * FROM Repairs")
    List<Repair> getAllRepairs();




}
