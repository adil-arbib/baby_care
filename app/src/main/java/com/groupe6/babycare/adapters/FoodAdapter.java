package com.groupe6.babycare.adapters;

import android.annotation.SuppressLint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.switchmaterial.SwitchMaterial;
import com.groupe6.babycare.R;
import com.groupe6.babycare.dtos.feeding.FoodDTO;
import com.groupe6.babycare.listeners.OnItemClickListener;

import java.util.List;

public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.ViewHolder> {


    private List<FoodDTO> foods;

    private OnItemClickListener<FoodDTO> itemClickListener;

    public FoodAdapter(List<FoodDTO> foods, OnItemClickListener<FoodDTO> itemClickListener) {
        this.foods = foods;
        this.itemClickListener = itemClickListener;
    }

    @NonNull
    @Override
    public FoodAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_feeding, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FoodAdapter.ViewHolder holder, int position) {
        FoodDTO food = foods.get(position);
        holder.label.setText(food.getLabel());
        holder.date.setText(food.getReminderDate());
        holder.quantity.setText(String.valueOf(food.getQuantity()));
        holder.imgFeedingType.setBackgroundResource(getImgResource(food.getNutritionType()));
        holder.toggleButton.setChecked(food.getReminderState().equals("COMPLETED"));
        holder.container.setOnClickListener(v -> itemClickListener.onClick(food));
    }


    @Override
    public int getItemCount() {
        return foods.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        CardView container;
        ImageView imgFeedingType;
        TextView label, quantity, date;
        CheckBox toggleButton;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            container = itemView.findViewById(R.id.feeding_item);
            imgFeedingType = itemView.findViewById(R.id.img_feeding_type);
            label = itemView.findViewById(R.id.txt_label);
            quantity = itemView.findViewById(R.id.txt_quantity);
            date = itemView.findViewById(R.id.txt_date);
            toggleButton = itemView.findViewById(R.id.toggle_button);

        }
    }

    private int getImgResource(String type) {
        switch (type.toLowerCase()) {
            case "breast" :
                return R.drawable.breast_feeding;
            case "liquid":
                return R.drawable.baby_bottle;
            case "solid":
                return R.drawable.baby_food;
        }
        return R.drawable.baby_bottle;
    }


    @SuppressLint("NotifyDataSetChanged")
    public void setFoods(List<FoodDTO> foods) {
        this.foods = foods;
        notifyDataSetChanged();
    }

}
