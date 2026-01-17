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
import com.example.vehiclerepairtracker.entities.Repair;

import java.util.List;

public class RepairAdapter extends RecyclerView.Adapter<RepairAdapter.RepairViewHolder> {


    class RepairViewHolder extends RecyclerView.ViewHolder {
        private final TextView repairItemView;
        private final TextView repairItemView2;

        public RepairViewHolder(@NonNull View itemView) {
            super(itemView);
            repairItemView = itemView.findViewById(R.id.textView);
            repairItemView2 = itemView.findViewById(R.id.textView2);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    final Repair current = repairList.get(position);
                    Intent intent = new Intent(context, RepairDetailsActivity.class);
                    intent.putExtra("excursionId", current.getRepairId());
                    intent.putExtra("repair", current.getRepairFinished());
                    intent.putExtra("date", current.getDateFinished());
                    intent.putExtra("carId", current.getCarId());

                    context.startActivity(intent);
                }
            });
        }
    }

    private List<Repair> repairList;
    private final Context context;
    private final LayoutInflater mInflater;

    public RepairAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
        this.context = context;
    }

    @NonNull
    @Override
    public RepairViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = mInflater.inflate(R.layout.repair_list_item, parent,false);

        return new RepairViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RepairViewHolder holder, int position) {

        if(repairList != null){
            Repair current = repairList.get(position);
            String title = current.getRepairFinished();
            String date = current.getDateFinished();
            holder.repairItemView.setText(title);
            holder.repairItemView2.setText(date);
        }
        else{
            holder.repairItemView.setText("No repair info");
            holder.repairItemView2.setText("No repair date");
        }

    }

    @Override
    public int getItemCount() {
        return repairList == null ? 0 : repairList.size();
    }

    public void setRepairList(List<Repair> repairs){
        repairList = repairs;
        notifyDataSetChanged();
    }

}
