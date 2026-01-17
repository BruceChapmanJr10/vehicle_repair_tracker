package com.example.vehiclerepairtracker.database;


import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.vehiclerepairtracker.dao.CarListDao;
import com.example.vehiclerepairtracker.dao.RepairListDao;
import com.example.vehiclerepairtracker.entities.Car;
import com.example.vehiclerepairtracker.entities.Repair;

@Database(entities = {Car.class, Repair.class}, version = 3, exportSchema = false)

public abstract class CarDatabaseBuilder extends RoomDatabase {

    public abstract CarListDao carListDao();
    public abstract RepairListDao repairListDao();

    private static volatile CarDatabaseBuilder INSTANCE;

    static CarDatabaseBuilder getDataBase(final Context context) {
        if (INSTANCE == null) {
            synchronized (CarDatabaseBuilder.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), CarDatabaseBuilder.class, "CarRepairDatabase.db")
                            .allowMainThreadQueries()
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;
    }





}
