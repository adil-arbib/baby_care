package com.groupe6.babycare.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.groupe6.babycare.R;
import com.groupe6.babycare.databinding.ActivityInfoBinding;
import com.groupe6.babycare.dtos.activities.ActivityDTO;
import com.groupe6.babycare.dtos.diaper.DiaperDTO;
import com.groupe6.babycare.dtos.error.ErrorDTO;
import com.groupe6.babycare.listeners.ResponseListener;
import com.groupe6.babycare.repositories.implementations.ActivityApiImpl;
import com.groupe6.babycare.utils.InputsUtils;
import com.groupe6.babycare.utils.TimeUtils;

public class DiaperInfoActivity extends AppCompatActivity {

    private DiaperInfoActivity binding;

    private DiaperDTO diaper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diaper_info);
    }

    /*

    private void displayData() {
        binding..setText(diaper.getNotes());
        binding.diaperDTO.setText(diaper.getActivityType());
        binding.diaperDTO.setText(TimeUtils.formatFromSqlDateToRegular(activity.getReminderDate()));
        binding.diaperDTO.setChecked(diaper.getReminderState().toLowerCase().equals("COMPLETED"));

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
     */

}