package com.groupe6.babycare.adapters;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.groupe6.babycare.R;
import com.groupe6.babycare.dtos.healthcare.HealthCareDTO;
import com.groupe6.babycare.listeners.OnItemClickListener;

import java.util.List;

public class HealthCareAdapter extends RecyclerView.Adapter<HealthCareAdapter.ViewHolder> {


    private List<HealthCareDTO> healthCareDTOS;

    private OnItemClickListener<HealthCareDTO> itemClickListener;

    public HealthCareAdapter(List<HealthCareDTO> list, OnItemClickListener<HealthCareDTO> itemClickListener) {
        this.healthCareDTOS = list;
        this.itemClickListener = itemClickListener;
    }

    @NonNull
    @Override
    public HealthCareAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_healthcare, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HealthCareAdapter.ViewHolder holder, int position) {
        HealthCareDTO health = healthCareDTOS.get(position);
        holder.date.setText(health.getReminderDate());
        holder.notes.setText(health.getNotes());
        holder.toggleButton.setChecked(health.getReminderState().equals("COMPLETED"));
        holder.container.setOnClickListener(v -> itemClickListener.onClick(health));
    }

    @Override
    public int getItemCount() {
        return healthCareDTOS.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        CardView container;
        TextView notes, type, date;
        CheckBox toggleButton;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            container = itemView.findViewById(R.id.healthcare_container);
            notes = itemView.findViewById(R.id.txt_note);
            type = itemView.findViewById(R.id.txt_type);
            date = itemView.findViewById(R.id.txt_date);
            toggleButton = itemView.findViewById(R.id.toggle_button);
        }
    }



    @SuppressLint("NotifyDataSetChanged")
    public void setHealthCare(List<HealthCareDTO> healthCareDTOS) {
        this.healthCareDTOS = healthCareDTOS;
        notifyDataSetChanged();
    }


    public List<HealthCareDTO> gethealthCareList() {
        return healthCareDTOS;
    }
}
