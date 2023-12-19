package com.groupe6.babycare.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.groupe6.babycare.R;
import com.groupe6.babycare.consts.GlobalKeys;
import com.groupe6.babycare.databinding.ActivityNoteInfoBinding;
import com.groupe6.babycare.databinding.ActivitySleepInfoBinding;
import com.groupe6.babycare.dtos.activities.ActivityDTO;
import com.groupe6.babycare.dtos.error.ErrorDTO;
import com.groupe6.babycare.dtos.sleeping.SleepDTO;
import com.groupe6.babycare.listeners.ResponseListener;
import com.groupe6.babycare.repositories.implementations.ActivityApiImpl;
import com.groupe6.babycare.repositories.implementations.SleepApiImpl;
import com.groupe6.babycare.utils.InputsUtils;

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
        binding.toggleButton.setChecked(sleep.getReminderState().toLowerCase().equals("COMPLETED"));
    }

    private void saveChanges() {
        if(!InputsUtils.validateInputs(binding.inputStartDate, binding.inputEndDate))
            return;

        SleepDTO sleepDTO = new SleepDTO();
        sleepDTO.setId(sleep.getId());
        sleepDTO.setAwakenings(Integer.parseInt(binding.inputAwakenings.getText().toString()));
        sleepDTO.setSleepType(binding.inputType.getText().toString());
        sleepDTO.setStartDate(sleep.getStartDate());
        sleepDTO.setEndDate(sleep.getEndDate());
        sleepDTO.setReminderState(binding.toggleButton.isSelected() ? "COMPLETED" : "UPCOMING");

        Log.e("OUTPUT", sleepDTO.toString());

        SleepApiImpl sleepApi = SleepApiImpl.getInstance(getApplicationContext());
        sleepApi.updateSleep(sleepDTO, sleep.getId(), new ResponseListener<SleepDTO>() {

            @Override
            public void onSuccess(SleepDTO response) {
                    Toast.makeText(SleepInfoActivity.this, "Updated successfully!!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(ErrorDTO error) {
                Toast.makeText(SleepInfoActivity.this, "An error was occurred!!", Toast.LENGTH_SHORT).show();

            }
    });

    }

    public void cancelChanges() {
        displayData();
    }
}