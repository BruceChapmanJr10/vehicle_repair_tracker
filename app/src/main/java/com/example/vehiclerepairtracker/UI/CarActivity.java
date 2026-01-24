package com.example.vehiclerepairtracker.UI;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vehiclerepairtracker.R;
import com.example.vehiclerepairtracker.database.Repository;
import com.example.vehiclerepairtracker.entities.Car;
import com.example.vehiclerepairtracker.entities.Vehicle;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class CarActivity extends AppCompatActivity {
    public Repository repository;
    EditText searchText;
    Button searchButton;
    CarAdapter carAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_car);


        searchText = findViewById(R.id.searchText);
        searchButton = findViewById(R.id.searchButton);


        FloatingActionButton btn = findViewById(R.id.addCarDetails);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int vehicleDoors = 4;
                Vehicle newCar = new Car(1,"1992","Honda", "Civic", vehicleDoors);
                System.out.println("Added vehicle: " + newCar.getVehicleType() + " " + newCar.getMake() + " " + newCar.getModel());
                Intent intent = new Intent(CarActivity.this, CarDetailsActivity.class);
                startActivity(intent);
            }
        });

        repository = new Repository(getApplication());

        //Examples

//        repository.insert(new Car(0, "2010", "Ford", "Focus", 4));
//        repository.insert(new Car(0, "2012", "Ford", "Fusion", 4));
//        repository.insert(new Car(0, "2018", "Toyota", "Camry", 4));
//        repository.insert(new Car(0, "2020", "Toyota", "Corolla", 4));
//        repository.insert(new Car(0, "2021", "Honda", "Civic", 4));
//        repository.insert(new Car(0, "2019", "Honda", "Accord", 4));
//        repository.insert(new Car(0, "2022", "Tesla", "Model 3", 4));
//        repository.insert(new Car(0, "2023", "Tesla", "Model X", 4));
//        repository.insert(new Car(0, "2015", "Ford", "Mustang", 2));

        // Views
        searchText = findViewById(R.id.searchText);
        searchButton = findViewById(R.id.searchButton);

        Button reportButton = findViewById(R.id.reportButton);

        reportButton.setOnClickListener(v -> {
            List<Car> allCars = repository.getmAllCars();
            StringBuilder report = new StringBuilder();

            // Title
            report.append("Vehicle Report\n");
            report.append("Generated on: ").append(java.time.LocalDateTime.now()).append("\n\n");

            // Column headers
            report.append(String.format("%-6s %-6s %-10s %-10s %-5s\n",
                    "ID", "Year", "Make", "Model", "Doors"));

            // Data rows
            for (Car car : allCars) {
                report.append(String.format("%-6s %-6s %-10s %-10s %-5s\n",
                        String.valueOf(car.getCarId()),
                        car.getYear(),
                        car.getMake(),
                        car.getModel(),
                        String.valueOf(car.getDoors())
                ));
            }

            // Show in a dialog
            new androidx.appcompat.app.AlertDialog.Builder(this)
                    .setTitle("Vehicle Report")
                    .setMessage(report.toString())
                    .setPositiveButton("OK", null)
                    .show();
        });

        // RecyclerView + Adapter
        RecyclerView recyclerView = findViewById(R.id.recycler);
        carAdapter = new CarAdapter(this);
        recyclerView.setAdapter(carAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Load all cars initially
        carAdapter.setCars(repository.getmAllCars());

        // FAB adds a new car
        FloatingActionButton fab = findViewById(R.id.addCarDetails);

// Set a single click listener
        fab.setOnClickListener(v -> {
            carAdapter.setCars(repository.getmAllCars());
            Intent intent = new Intent(CarActivity.this, CarDetailsActivity.class);
            startActivity(intent);
        });

        // Search functionality
        searchButton.setOnClickListener(v -> {
            String query = searchText.getText().toString().trim();
            if (query.isEmpty()) {
                carAdapter.setCars(repository.getmAllCars());
            } else {
                carAdapter.setCars(repository.searchCars(query));
            }
        });
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}