package com.example.vehiclerepairtracker.UI;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.vehiclerepairtracker.R;
import com.example.vehiclerepairtracker.database.Repository;
import com.example.vehiclerepairtracker.entities.Car;
import com.example.vehiclerepairtracker.entities.Repair;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class RepairDetailsActivity extends AppCompatActivity {
    EditText repairService;
    EditText repairDate;
    EditText editService;
    EditText editDate;

    String service;
    String date;
    int repairId;
    int carId;

    Repository repository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_repair_details);

        repository = new Repository(getApplication());

        service = getIntent().getStringExtra("repair");
        editService = findViewById(R.id.vehicleRepair);
        editService.setText(service);


        date = getIntent().getStringExtra("date");
        editDate = findViewById(R.id.repairDate);
        editDate.setText(date);

        repairId = getIntent().getIntExtra("repairId", -1);
        carId = getIntent().getIntExtra("carId", -1);
        repairService = findViewById(R.id.vehicleRepair);
        repairDate = findViewById(R.id.repairDate);

        ArrayList<Car> carArrayList = new ArrayList<>();
        carArrayList.addAll(repository.getmAllCars());
        ArrayList<Integer> carIdList = new ArrayList<>();
        for (Car car : carArrayList) {
            carIdList.add(car.getCarId());


            ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
                Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
                v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
                return insets;
            });
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.repair_details_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.saveRepair) {

            String service = repairService.getText().toString();
            String date = repairDate.getText().toString();

            if (!isValidDate(date)) {
                Toast.makeText(this,
                        "Dates must be in MM/dd/yyyy format",
                        Toast.LENGTH_LONG).show();
                return true;
            }

            //validate repair date
            Car parentCar = repository.getCarById(carId);
            if (parentCar == null) {
                Toast.makeText(this, "Associated car not found", Toast.LENGTH_SHORT).show();
                return true;
            }

            SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy", Locale.US);


            if (repairId == -1) {
                // ADD
                Repair repair = new Repair(
                        0,
                        carId,
                        service,
                        date
                );
                repository.insert(repair);
            } else {

                // UPDATE
               Repair repair = new Repair(
                        repairId,
                        carId,
                        service,
                        date
                );
                repository.update(repair);
            }

            finish();
            return true;
        }

        // DELETE
        if (item.getItemId() == R.id.deleteRepair) {

            Repair repair = new Repair(
                    repairId,
                    carId,
                    repairService.getText().toString(),
                    repairDate.getText().toString()
            );

            repository.delete(repair);
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    //validate mm/dd/yyyy
    private boolean isValidDate(String date) {
        return date.matches(
                "^(0[1-9]|1[0-2])/(0[1-9]|[12][0-9]|3[01])/\\d{4}$"
        );
    }
}