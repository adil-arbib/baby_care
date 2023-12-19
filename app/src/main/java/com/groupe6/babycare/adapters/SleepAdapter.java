package com.groupe6.babycare.adapters;

import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.switchmaterial.SwitchMaterial;
import com.groupe6.babycare.R;
import com.groupe6.babycare.dtos.activities.ActivityDTO;
import com.groupe6.babycare.dtos.feeding.FoodDTO;
import com.groupe6.babycare.dtos.sleeping.SleepDTO;
import com.groupe6.babycare.listeners.OnItemClickListener;
import com.groupe6.babycare.utils.TimeUtils;

import java.util.List;

public class SleepAdapter extends RecyclerView.Adapter<SleepAdapter.ViewHolder> {


    private List<SleepDTO> sleepList;

    private OnItemClickListener<SleepDTO> itemClickListener;

    public SleepAdapter(List<SleepDTO> sleepList, OnItemClickListener<SleepDTO> itemClickListener) {
        this.sleepList = sleepList;
        this.itemClickListener = itemClickListener;
    }

    @NonNull
    @Override
    public SleepAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_sleeping, parent, false);
        return new SleepAdapter.ViewHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onBindViewHolder(@NonNull SleepAdapter.ViewHolder holder, int position) {
        SleepDTO sleep = sleepList.get(position);
        System.out.println(sleep);
        holder.startDate.setText(TimeUtils.formatSqlDatetime(sleep.getStartDate()));
        holder.endDate.setText(TimeUtils.formatSqlDatetime(sleep.getEndDate()));
        holder.imgSleepingType.setBackgroundResource(
                sleep.getSleepType().toLowerCase().equals("NAP") ?
                        R.drawable.nap : R.drawable.deep_sleep
        );
        holder.toggleButton.setChecked(sleep.getReminderState().equals("COMPLETED"));
        holder.container.setOnClickListener(v -> itemClickListener.onClick(sleep));
    }

    @Override
    public int getItemCount() {
        return sleepList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        CardView container;
        ImageView imgSleepingType;
        TextView startDate, endDate;
        CheckBox toggleButton;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            container = itemView.findViewById(R.id.sleeping_item);
            imgSleepingType = itemView.findViewById(R.id.img_sleeping_type);
            startDate = itemView.findViewById(R.id.txt_from_value);
            endDate = itemView.findViewById(R.id.txt_to_value);
            toggleButton = itemView.findViewById(R.id.toggle_button);

        }
    }
    public List<SleepDTO> getSleepList() {
        return sleepList;
    }


    public void setSleepList(List<SleepDTO> sleepList) {
        this.sleepList = sleepList;
        notifyDataSetChanged();
    }
}
