package com.example.vehiclerepairtracker.UI;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vehiclerepairtracker.entities.Vehicle;

import java.util.List;

public class VehicleAdapter extends RecyclerView.Adapter<VehicleAdapter.VehicleViewHolder> {

    private List<Vehicle> vehicles;
    private final LayoutInflater inflater;

    public VehicleAdapter(Context context) {
        inflater = LayoutInflater.from(context);
    }

    public void setVehicles(List<Vehicle> vehicles) {
        this.vehicles = vehicles;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public VehicleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(android.R.layout.simple_list_item_2, parent, false);
        return new VehicleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VehicleViewHolder holder, int position) {
        Vehicle v = vehicles.get(position);
        holder.title.setText(v.getVehicleType() + ": " + v.getMake() + " " + v.getModel());
    }

    @Override
    public int getItemCount() {
        return vehicles == null ? 0 : vehicles.size();
    }

    static class VehicleViewHolder extends RecyclerView.ViewHolder {
        TextView title;

        public VehicleViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(android.R.id.text1);
        }
    }
}
