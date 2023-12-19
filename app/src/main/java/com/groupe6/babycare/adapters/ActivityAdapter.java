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
import com.groupe6.babycare.dtos.sleeping.SleepDTO;
import com.groupe6.babycare.listeners.OnItemClickListener;
import com.groupe6.babycare.utils.TimeUtils;

import java.util.List;

public class ActivityAdapter extends RecyclerView.Adapter<ActivityAdapter.ViewHolder> {

    private List<ActivityDTO> activities;

    private OnItemClickListener<ActivityDTO> itemClickListener;

    public ActivityAdapter(List<ActivityDTO> activities, OnItemClickListener<ActivityDTO> itemClickListener) {
        this.activities = activities;
        this.itemClickListener = itemClickListener;
    }

    @NonNull
    @Override
    public ActivityAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_activity2, parent, false);
        return new ActivityAdapter.ViewHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onBindViewHolder(@NonNull ActivityAdapter.ViewHolder holder, int position) {
        ActivityDTO activity = activities.get(position);
        holder.date.setText(TimeUtils.formatSqlDatetime(activity.getReminderDate()));
        holder.note.setText(String.valueOf(activity.getNotes()));
        holder.type.setText(activity.getActivityType());
        holder.checkBox.setChecked(activity.getReminderState().equals("COMPLETED"));
        holder.container.setOnClickListener(v -> itemClickListener.onClick(activity));
    }

    @Override
    public int getItemCount() {
        return activities.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        CardView container;
        CheckBox checkBox;
        TextView note, type, date;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            container = itemView.findViewById(R.id.activity_container);
            checkBox = itemView.findViewById(R.id.checkBox);
            note = itemView.findViewById(R.id.txt_note);
            date = itemView.findViewById(R.id.txt_date);
            type = itemView.findViewById(R.id.txt_type);

        }
    }
}
