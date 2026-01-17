package com.example.vehiclerepairtracker.UI;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

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
import com.example.vehiclerepairtracker.entities.Repair;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class CarDetailsActivity extends AppCompatActivity {
    Repository repository;
    int carId;
    String year;
    String make;
    String model;
    int numRepairs;
    int repairId;

    TextView editYear;
    TextView editMake;
    TextView editModel;

    Car currentCar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_car_details);

        FloatingActionButton btn = findViewById(R.id.floatingActionButton);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CarDetailsActivity.this, RepairDetailsActivity.class);
                intent.putExtra("carId", carId);
                startActivity(intent);
            }
        });


        repository = new Repository(getApplication());

        editYear = findViewById(R.id.vehicleYear);
        editMake = findViewById(R.id.vehicleMake);
        editModel = findViewById(R.id.vehicleModel);


        carId = getIntent().getIntExtra("carId", -1);
        year = getIntent().getStringExtra("year");
        make = getIntent().getStringExtra("make");
        model = getIntent().getStringExtra("model");

        editYear.setText(year);
        editMake.setText(make);
        editModel.setText(model);

        currentCar = null;
        if (carId != -1) {
            currentCar = repository.getCarById(carId);
        }

        if (currentCar != null) {
            editYear.setText(currentCar.getYear());
            editMake.setText(currentCar.getMake());
            editModel.setText(currentCar.getModel());
        }

        RecyclerView recyclerView = findViewById(R.id.repairRecyclerView);
        final RepairAdapter repairAdapter = new RepairAdapter(this);
        recyclerView.setAdapter(repairAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        List<Repair> filteredRepairs = new ArrayList<>();
        for (Repair e : repository.getRepairsForCar(carId)) {
            if (e.getCarId() == carId) filteredRepairs.add(e);

        }
        repairAdapter.setRepairList(filteredRepairs);


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.car_details_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == android.R.id.home) {
            Intent intent = new Intent(CarDetailsActivity.this, CarActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            finish();
            return true;
        }
        if (item.getItemId() == R.id.saveCar) {
            Car car;

            if (currentCar == null && carId != -1) {
                currentCar = repository.getCarById(carId);
            }

            if (currentCar == null) {
                // INSERT
                Car newCar = new Car(
                        0,
                        editYear.getText().toString(),
                        editMake.getText().toString(),
                        editModel.getText().toString()

                );
                repository.insert(newCar);
                currentCar = newCar;
                Toast.makeText(this, "Car added", Toast.LENGTH_SHORT).show();
            } else {
                // UPDATE
                currentCar.setYear(editYear.getText().toString());
                currentCar.setMake(editMake.getText().toString());
                currentCar.setModel(editModel.getText().toString());

                repository.update(currentCar);
                Toast.makeText(this, "Car updated", Toast.LENGTH_SHORT).show();
            }

            return true;
        }

        if (item.getItemId() == R.id.deleteCar) {
            if (currentCar == null) {
                currentCar = repository.getCarById(carId);
            }

            if (currentCar == null) {
                Toast.makeText(this, "Error: Car not found", Toast.LENGTH_SHORT).show();
                return true;
            }

            numRepairs = 0;
            for (Repair repair : repository.getRepairsForCar(carId)) {
                if (repair.getCarId() == currentCar.getCarId()) numRepairs++;
            }

            if (numRepairs > 0) {
                Toast.makeText(this, "Can't delete a car with repairs", Toast.LENGTH_LONG).show();
                return true;
            }

            repository.delete(currentCar);
            Toast.makeText(this, currentCar.getMake() + " " + currentCar.getModel() + " was deleted", Toast.LENGTH_LONG).show();

            Intent intent = new Intent(CarDetailsActivity.this, CarActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


}