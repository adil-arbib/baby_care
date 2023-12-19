package com.groupe6.babycare.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.groupe6.babycare.R;
import com.groupe6.babycare.consts.GlobalKeys;
import com.groupe6.babycare.databinding.ActivityFeedingInfoBinding;
import com.groupe6.babycare.databinding.ActivityInfoBinding;
import com.groupe6.babycare.dtos.activities.ActivityDTO;
import com.groupe6.babycare.dtos.diaper.DiaperDTO;
import com.groupe6.babycare.dtos.error.ErrorDTO;
import com.groupe6.babycare.dtos.feeding.FoodDTO;
import com.groupe6.babycare.listeners.ResponseListener;
import com.groupe6.babycare.repositories.implementations.ActivityApiImpl;
import com.groupe6.babycare.repositories.implementations.FoodApiImpl;
import com.groupe6.babycare.utils.InputsUtils;

public class ActivityInfoActivity extends AppCompatActivity {

    private ActivityInfoBinding binding;

    private ActivityDTO activity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityInfoBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();

        setContentView(view);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            activity = extras.getParcelable(GlobalKeys.ACTIVITY_KEY);
        }
        displayData();

        binding.btnAdd.setOnClickListener(v -> saveChanges());

        binding.icBack.setActivity(this);
    }

    private void displayData() {
        binding.inputNote.setText(activity.getNotes());
        binding.inputType.setText(activity.getActivityType());
        binding.inputDate.setText(activity.getReminderDate());
        binding.toggleButton.setChecked(activity.getReminderState().toLowerCase().equals("completed"));

    }

    private void saveChanges() {
        if(!InputsUtils.validateInputs(binding.inputNote, binding.inputDate))
            return;

        ActivityDTO activityDTO = new ActivityDTO();
        activityDTO.setId(activity.getId());
        activityDTO.setNotes(binding.inputNote.getText().toString());
        activityDTO.setActivityType(binding.inputType.getText().toString());
        activityDTO.setReminderDate(activity.getReminderDate());
        activityDTO.setReminderState(binding.toggleButton.isSelected() ? "COMPLETED" : "UPCOMING");

        Log.e("OUTPUT", activityDTO.toString());

        ActivityApiImpl activityApi = ActivityApiImpl.getInstance(getApplicationContext());
        activityApi.updateActivity(activityDTO, activity.getId(), new ResponseListener<ActivityDTO>() {
            @Override
            public void onSuccess(ActivityDTO response) {
                Toast.makeText(ActivityInfoActivity.this, "Updated successfully!!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(ErrorDTO error) {
                Toast.makeText(ActivityInfoActivity.this, "An error was occurred!!", Toast.LENGTH_SHORT).show();

            }
        });

    }

    public void cancelChanges() {
        displayData();
    }

}