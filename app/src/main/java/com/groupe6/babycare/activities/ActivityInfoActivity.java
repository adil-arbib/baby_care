package com.groupe6.babycare.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.groupe6.babycare.R;
import com.groupe6.babycare.consts.GlobalKeys;
import com.groupe6.babycare.databinding.ActivityFeedingInfoBinding;
import com.groupe6.babycare.databinding.ActivityInfoBinding;
import com.groupe6.babycare.dtos.activities.ActivityDTO;

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

        binding.icBack.setActivity(this);
    }

    private void displayData() {
        binding.inputNote.setText(activity.getNote());
        binding.inputType.setText(activity.getType());
        binding.inputDate.setText(activity.getDate());
        binding.toggleButton.setChecked(activity.getStatus().toLowerCase().equals("done"));

    }

    public void cancelChanges() {
        displayData();
    }

}