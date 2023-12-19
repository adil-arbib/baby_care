package com.groupe6.babycare.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.groupe6.babycare.R;
import com.groupe6.babycare.dtos.activities.SimpleActivityDTO;
import com.groupe6.babycare.dtos.reminder.ReminderDTO;
import com.groupe6.babycare.listeners.OnItemClickListener;
import com.groupe6.babycare.utils.TimeUtils;

import java.util.List;

public class ActivityLogAdapter extends RecyclerView.Adapter<ActivityLogAdapter.ViewHolder> {

    private List<ReminderDTO> reminderList;
    private OnItemClickListener<ReminderDTO> itemClickListener;

    public ActivityLogAdapter(List<ReminderDTO> reminderList,
                              OnItemClickListener<ReminderDTO> itemClickListener) {
        this.reminderList = reminderList;
        this.itemClickListener = itemClickListener;

    }

    @NonNull
    @Override
    public ActivityLogAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_activity, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ActivityLogAdapter.ViewHolder holder, int position) {
        ReminderDTO reminderDTO = reminderList.get(position);
        holder.container.setOnClickListener(v -> itemClickListener.onClick(reminderDTO));
        holder.imgActivity.setBackgroundResource(getAssociatedActivityIcon(reminderDTO.getType()));
        holder.txtTime.setText(reminderDTO.getDate());
        holder.txtLabel.setText(reminderDTO.getType());
//        holder.txtRemainingTime.setText(TimeUtils.getRemainingTime(reminderDTO.getDate()));
    }

    @Override
    public int getItemCount() {
        return reminderList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ConstraintLayout container;
        TextView txtLabel, txtTime, txtRemainingTime;
        ImageView imgActivity;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            container = itemView.findViewById(R.id.activity_container);
            txtLabel = itemView.findViewById(R.id.txt_activity_label);
            txtTime = itemView.findViewById(R.id.txt_time);
//            txtRemainingTime = itemView.findViewById(R.id.txt_remaining_time);
            imgActivity = itemView.findViewById(R.id.img_activity_icon);
        }
    }

    private int getAssociatedActivityIcon(String label) {
        switch (label.toLowerCase()) {
            case "sleeping":
                return R.drawable.sleeping_circle;
            case "diaper":
                return R.drawable.diaper_circle;
            case "feeding":
                return R.drawable.feeding_circle;
            default:
                return R.drawable.feeding_circle;
        }
    }
}
