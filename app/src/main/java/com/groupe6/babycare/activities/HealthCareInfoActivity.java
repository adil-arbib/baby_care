package com.groupe6.babycare.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.groupe6.babycare.consts.GlobalKeys;
import com.groupe6.babycare.databinding.ActivityHealthcareInfoBinding;
import com.groupe6.babycare.dtos.error.ErrorDTO;
import com.groupe6.babycare.dtos.healthcare.HealthCareDTO;
import com.groupe6.babycare.listeners.ResponseListener;
import com.groupe6.babycare.repositories.implementations.HealthCareImpl;
import com.groupe6.babycare.utils.InputsUtils;

public class HealthCareInfoActivity  extends AppCompatActivity {


    private HealthCareDTO healthCareDTO;

    private ActivityHealthcareInfoBinding binding;

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

        binding.icBack.setActivity(this);


        binding.btnSave.setOnClickListener(v -> {
            saveChanges();
        });
    }

    private void displayData() {
        binding.inputNotes.setText(healthCareDTO.getNotes());
        binding.inputDate.setText(healthCareDTO.getReminderDate());
        binding.inputType.setText(healthCareDTO.getHealthCareType());
        binding.toggleButton.setChecked(healthCareDTO.getReminderState().toLowerCase().equals("done"));
    }


    private void saveChanges() {
        if(!InputsUtils.validateInputs(binding.inputNotes, binding.inputDate))
            return;
        HealthCareDTO healthDTO = new HealthCareDTO();
        healthDTO.setHealthCareType(binding.inputType.getText().toString());
        healthDTO.setNotes(binding.inputNotes.getText().toString());
        healthDTO.setReminderDate(healthDTO.getReminderDate());
        healthDTO.setReminderState(healthDTO.getReminderState());
        HealthCareImpl healthCareApi = HealthCareImpl.getInstance(getApplicationContext());
        healthCareApi.updateHealthCare(healthDTO, healthDTO.getId(), new ResponseListener<HealthCareDTO>() {
            @Override
            public void onSuccess(HealthCareDTO response) {
                Toast.makeText(HealthCareInfoActivity.this, "Updated successfully!!", Toast.LENGTH_SHORT).show();
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
