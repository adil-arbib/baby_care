package com.groupe6.babycare.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.groupe6.babycare.R;
import com.groupe6.babycare.consts.GlobalKeys;
import com.groupe6.babycare.databinding.ActivityChildInfoBinding;
import com.groupe6.babycare.databinding.ActivityFeedingInfoBinding;
import com.groupe6.babycare.dtos.feeding.FoodDTO;

public class FeedingInfoActivity extends AppCompatActivity {


    private FoodDTO food;

    private ActivityFeedingInfoBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityFeedingInfoBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            food = extras.getParcelable(GlobalKeys.FEEDING_KEY);
        }
        displayData();

        binding.icBack.setActivity(this);
    }

    private void displayData() {
        binding.inputLabel.setText(food.getLabel());
        binding.inputDate.setText(food.getDate());
        binding.inputType.setText(food.getType());
        binding.inputQuantity.setText(food.getQuantity()+"");
        binding.toggleButton.setChecked(food.getStatus().toLowerCase().equals("done"));
    }

    public void cancelChanges() {
        displayData();
    }
}