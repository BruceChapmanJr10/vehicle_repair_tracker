package com.example.vehiclerepairtracker.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Repairs")
public class Repair {

    @PrimaryKey
    private int repairId;
    private String repairFinished;
    private String dateFinished;

    public Repair(int repairId, String repairFinished, String dateFinished) {
        this.repairId = repairId;
        this.repairFinished = repairFinished;
        this.dateFinished = dateFinished;
    }

    public Repair() {
    }

    public int getRepairId() {
        return repairId;
    }

    public void setRepairId(int repairId) {
        this.repairId = repairId;
    }

    public String getRepairFinished() {
        return repairFinished;
    }

    public void setRepairFinished(String repairFinished) {
        this.repairFinished = repairFinished;
    }

    public String getDateFinished() {
        return dateFinished;
    }

    public void setDateFinished(String dateFinished) {
        this.dateFinished = dateFinished;
    }
}
