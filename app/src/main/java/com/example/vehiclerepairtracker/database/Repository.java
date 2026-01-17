package com.example.vehiclerepairtracker.database;

import android.app.Application;

import com.example.vehiclerepairtracker.dao.CarListDao;
import com.example.vehiclerepairtracker.dao.RepairListDao;
import com.example.vehiclerepairtracker.entities.Car;
import com.example.vehiclerepairtracker.entities.Repair;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Repository {
    private CarListDao mCarDao;

    private RepairListDao mRepairDao;

    private List<Car> mAllCars;

    private List<Repair> mAllRepairs;

    private static int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public Repository(Application application) {
        CarDatabaseBuilder db = CarDatabaseBuilder.getDataBase(application);
        mCarDao = db.carListDao();
        mRepairDao= db.repairListDao();
    }

    public List<Car> getmAllCars() {
        databaseExecutor.execute(() -> {
            mAllCars = mCarDao.getAllCars();
        });

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return mAllCars;
    }

    public void insert(Car car) {
        databaseExecutor.execute(() -> {
            mCarDao.insert(car);
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void update(Car car) {
        databaseExecutor.execute(() -> {
            mCarDao.update(car);
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void delete(Car car) {
        databaseExecutor.execute(() -> {
            mCarDao.delete(car);
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void insert(Repair repair) {
        databaseExecutor.execute(() -> {
            mRepairDao.insert(repair);
        });
        try {
            Thread.sleep(500); // optional, for simplicity
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void update(Repair repair) {
        databaseExecutor.execute(() -> {
            mRepairDao.update(repair);
        });
        try {
            Thread.sleep(500); // optional, for simplicity
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void delete(Repair repair) {
        databaseExecutor.execute(() -> mRepairDao.delete(repair));
    }

    public List<Repair>getAllRepairs() {
        databaseExecutor.execute(() -> {
            mAllRepairs = mRepairDao.getAllRepairs();
        });

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return mAllRepairs;

    }

    public Car getCarById(int id){
        return mCarDao.getCarById(id);}




}
