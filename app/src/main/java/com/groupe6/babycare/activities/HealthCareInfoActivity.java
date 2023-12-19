package com.groupe6.babycare.activities;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.groupe6.babycare.consts.GlobalKeys;
import com.groupe6.babycare.databinding.ActivityHealthcareInfoBinding;
import com.groupe6.babycare.dtos.error.ErrorDTO;
import com.groupe6.babycare.dtos.healthcare.HealthCareDTO;
import com.groupe6.babycare.listeners.ResponseListener;
import com.groupe6.babycare.repositories.implementations.HealthCareImpl;
import com.groupe6.babycare.utils.InputsUtils;
import com.groupe6.babycare.utils.TimeUtils;

public class HealthCareInfoActivity  extends AppCompatActivity {


    private HealthCareDTO healthCareDTO;

    private ActivityHealthcareInfoBinding binding;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHealthcareInfoBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            healthCareDTO = extras.getParcelable(GlobalKeys.HEALTHCARE_KEY);
        }
        displayData();




        binding.btnSave.setOnClickListener(v -> {
            saveChanges();
        });
        binding.btnCancel.setOnClickListener(v -> {
            cancelChanges();
        });
        binding.icBack.setActivity(this);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void displayData() {
        binding.inputNotes.setText(healthCareDTO.getNotes());
        binding.inputDate.setText(TimeUtils.formatSqlDatetime(healthCareDTO.getReminderDate()));
        binding.inputType.setText(healthCareDTO.getHealthCareType());
        binding.toggleButton.setChecked(healthCareDTO.getReminderState().toLowerCase().equals("completed"));
    }


    private void saveChanges() {
        if(!InputsUtils.validateInputs(binding.inputNotes, binding.inputDate))
            return;
        HealthCareDTO healthDTO = new HealthCareDTO();
        healthDTO.setId(healthCareDTO.getId());
        healthDTO.setHealthCareType(binding.inputType.getText().toString());
        healthDTO.setNotes(binding.inputNotes.getText().toString());
        healthDTO.setReminderDate(healthCareDTO.getReminderDate());
        String state = binding.toggleButton.isSelected() ?
                "COMPLETED" : "UPCOMING";
        healthDTO.setReminderState(state);
        HealthCareImpl healthCareApi = HealthCareImpl.getInstance(getApplicationContext());
        healthCareApi.updateHealthCare(healthDTO, healthDTO.getId(), new ResponseListener<HealthCareDTO>() {
            @Override
            public void onSuccess(HealthCareDTO response) {
                Toast.makeText(HealthCareInfoActivity.this, "Updated successfully!!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(ErrorDTO error) {
                Toast.makeText(HealthCareInfoActivity.this, "An error was occurred!!", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void cancelChanges() {
        displayData();
    }
}
