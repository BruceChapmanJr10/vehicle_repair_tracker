package com.example.vehiclerepairtracker.database;

import android.app.Application;

import com.example.vehiclerepairtracker.dao.CarListDao;
import com.example.vehiclerepairtracker.dao.RepairListDao;
import com.example.vehiclerepairtracker.dao.UserDao;
import com.example.vehiclerepairtracker.entities.Car;
import com.example.vehiclerepairtracker.entities.Repair;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Repository {
    private CarListDao mCarDao;

    private RepairListDao mRepairDao;

    private UserDao mUserDao;

    private List<Car> mAllCars;

    private List<Repair> mAllRepairs;

    private FirebaseFirestore firestore;

    private FirebaseAuth auth;

    private static int NUMBER_OF_THREADS = 4;
    static final public ExecutorService databaseExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public Repository(Application application) {
        CarDatabaseBuilder db = CarDatabaseBuilder.getDataBase(application);
        mCarDao = db.carListDao();
        mRepairDao= db.repairListDao();
        mUserDao = db.userDao();

        //firebase
        firestore = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();
    }

    public List<Car> getmAllCars() {
        return mCarDao.getAllCars();
    }

    public void insert(Car car) {
        databaseExecutor.execute(() -> mCarDao.insert(car));


        //  Firestore
        if (FirebaseAuth.getInstance().getCurrentUser() == null) return;

        Map<String, Object> data = new HashMap<>();
        data.put("userId", FirebaseAuth.getInstance().getCurrentUser().getUid());
        data.put("year", car.getYear());
        data.put("make", car.getMake());
        data.put("model", car.getModel());
        data.put("doors", car.getDoors());

        FirebaseFirestore.getInstance()
                .collection("cars")
                .add(data);
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

    //Firestore
    public void insert(Repair repair) {
        databaseExecutor.execute(() -> mRepairDao.insert(repair));

        if (FirebaseAuth.getInstance().getCurrentUser() == null) return;

        Map<String, Object> data = new HashMap<>();
        data.put("userId", FirebaseAuth.getInstance().getCurrentUser().getUid());
        data.put("carId", repair.getCarId());
        data.put("repairFinished", repair.getRepairFinished());
        data.put("dateFinished", repair.getDateFinished());

        FirebaseFirestore.getInstance()
                .collection("repairs")
                .add(data);
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

    public List<Repair> getRepairsForCar(int carId) {
        return mRepairDao.getRepairsForCar(carId);
    }

    public Car getCarById(int id){
        return mCarDao.getCarById(id);}

    public List<Car> searchCars(String searchTerm) {
        return mCarDao.searchCars("%" + searchTerm + "%");
    }
    public UserDao getUserDao() {
        return mUserDao;
    }



}
