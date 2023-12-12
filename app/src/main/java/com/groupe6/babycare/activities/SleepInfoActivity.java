package com.groupe6.babycare.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.groupe6.babycare.R;
import com.groupe6.babycare.consts.GlobalKeys;
import com.groupe6.babycare.databinding.ActivityNoteInfoBinding;
import com.groupe6.babycare.databinding.ActivitySleepInfoBinding;
import com.groupe6.babycare.dtos.sleeping.SleepDTO;

public class SleepInfoActivity extends AppCompatActivity {

    private ActivitySleepInfoBinding binding;

    private SleepDTO sleep;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySleepInfoBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            sleep = extras.getParcelable(GlobalKeys.SLEEP_KEY);
        }
        displayData();

        binding.icBack.setActivity(this);
    }

    private void displayData() {
        binding.inputType.setText(sleep.getSleepType());
        binding.inputStartDate.setText(sleep.getStartDate());
        binding.inputEndDate.setText(sleep.getEndDate());
        binding.inputAwakenings.setText(sleep.getAwakenings()+"");
        binding.toggleButton.setChecked(sleep.getReminderState().toLowerCase().equals("done"));
    }

    public void cancelChanges() {
        displayData();
    }
}