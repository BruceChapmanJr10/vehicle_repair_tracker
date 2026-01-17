package com.example.vehiclerepairtracker.UI;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vehiclerepairtracker.R;
import com.example.vehiclerepairtracker.entities.Car;

import java.util.List;

public class CarAdapter extends RecyclerView.Adapter<CarAdapter.CarViewHolder> {

    private List<Car> mCar;
    private final Context context;
    private final LayoutInflater mInflater;

    public CarAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
        this.context = context;
    }


    @NonNull
    @Override
    public CarAdapter.CarViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.car_list_item, parent, false);
        return new CarViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CarAdapter.CarViewHolder holder, int position) {
        if (mCar != null) {
            Car current = mCar.get(position);
            String year = current.getYear();
            String make = current.getMake();
            String model = current.getModel();

            holder.carItemView.setText(year);
            holder.carItemView2.setText(make);
            holder.carItemView3.setText(model);
        } else {
            holder.carItemView.setText("No Car Name");
        }
    }

    @Override
    public int getItemCount() {
        if (mCar != null) {
            return mCar.size();

        } else {
            return 0;
        }
    }

    public void setCars(List<Car> cars) {
        mCar = cars;
        notifyDataSetChanged();

    }

    public class CarViewHolder extends RecyclerView.ViewHolder {
        private final TextView carItemView;
        private final TextView carItemView2;
        private final TextView carItemView3;

        public CarViewHolder(@NonNull View itemView) {
            super(itemView);
            this.carItemView = itemView.findViewById(R.id.yearRecycler);
            this.carItemView2 = itemView.findViewById(R.id.textView3);
            this.carItemView3 = itemView.findViewById(R.id.textView6);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getBindingAdapterPosition();
                    final Car current = mCar.get(position);
                    Intent intent = new Intent(context, CarDetailsActivity.class);
                    intent.putExtra("carId", current.getCarId());
                    intent.putExtra("year", current.getYear());
                    intent.putExtra("make", current.getMake());
                    intent.putExtra("model", current.getModel());
                    context.startActivity(intent);
                }
            });
        }

    }
}
