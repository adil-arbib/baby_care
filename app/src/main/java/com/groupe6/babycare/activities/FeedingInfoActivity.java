package com.groupe6.babycare.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.groupe6.babycare.R;
import com.groupe6.babycare.consts.GlobalKeys;
import com.groupe6.babycare.databinding.ActivityChildInfoBinding;
import com.groupe6.babycare.databinding.ActivityFeedingInfoBinding;
import com.groupe6.babycare.dtos.error.ErrorDTO;
import com.groupe6.babycare.dtos.feeding.FoodDTO;
import com.groupe6.babycare.listeners.ResponseListener;
import com.groupe6.babycare.repositories.implementations.FoodApiImpl;
import com.groupe6.babycare.utils.InputsUtils;

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


        binding.btnSave.setOnClickListener(v -> {
            saveChanges();
        });
    }

    private void displayData() {
        binding.inputLabel.setText(food.getLabel());
        binding.inputDate.setText(food.getReminderDate());
        binding.inputType.setText(food.getNutritionType());
        binding.inputQuantity.setText(food.getQuantity()+"");
        binding.toggleButton.setChecked(food.getReminderState().toLowerCase().equals("done"));
    }


    private void saveChanges() {
        if(!InputsUtils.validateInputs(binding.inputLabel, binding.inputDate, binding.inputQuantity))
            return;
        FoodDTO foodDTO = new FoodDTO();
        foodDTO.setLabel(binding.inputLabel.getText().toString());
        foodDTO.setNutritionType(binding.inputType.getText().toString());
        foodDTO.setQuantity(Double.parseDouble(binding.inputQuantity.getText().toString()));
        foodDTO.setReminderDate(food.getReminderDate());
        foodDTO.setReminderState(food.getReminderState());
        FoodApiImpl foodApi = FoodApiImpl.getInstance(getApplicationContext());
        foodApi.updateFood(foodDTO, food.getId(), new ResponseListener<FoodDTO>() {
            @Override
            public void onSuccess(FoodDTO response) {
                Toast.makeText(FeedingInfoActivity.this, "Updated successfully!!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(ErrorDTO error) {

            }
        });

    }

    public void cancelChanges() {
        displayData();
    }
}