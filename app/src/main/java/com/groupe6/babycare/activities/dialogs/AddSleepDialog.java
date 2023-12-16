package com.groupe6.babycare.activities.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.material.textfield.TextInputEditText;
import com.groupe6.babycare.R;
import com.groupe6.babycare.dtos.children.ChildDTO;
import com.groupe6.babycare.dtos.error.ErrorDTO;
import com.groupe6.babycare.dtos.sleeping.SleepDTO;
import com.groupe6.babycare.holders.GlobalObjectsHolder;
import com.groupe6.babycare.listeners.OnDatePickListener;
import com.groupe6.babycare.listeners.ResponseListener;
import com.groupe6.babycare.repositories.implementations.SleepApiImpl;
import com.groupe6.babycare.utils.InputsUtils;

public class AddSleepDialog  extends Dialog implements OnDatePickListener {

    private TextInputEditText startDateInput;
    private TextInputEditText endDateInput;
    private TextInputEditText awakenings;
    private String selectedStartDate;
    private String selectedEndDate;
    public AddSleepDialog(@NonNull Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_add_sleep);

        Button btnAdd = findViewById(R.id.btn_add);
        Button btnCancel = findViewById(R.id.btn_cancel);
        startDateInput=findViewById(R.id.input_start_date);
        endDateInput=findViewById(R.id.input_end_date);
        awakenings=findViewById(R.id.input_awakenings);

        startDateInput.setOnClickListener(view -> {
            DatePickerDialog datePickerDialog = new DatePickerDialog(this, getContext());
            datePickerDialog.init();
            datePickerDialog.open();
        });
        endDateInput.setOnClickListener(view -> {
            DatePickerDialog datePickerDialog = new DatePickerDialog(this, getContext());
            datePickerDialog.init();
            datePickerDialog.open();
        });

        btnCancel.setOnClickListener(view -> {
            dismiss();
        });

        btnAdd.setOnClickListener(view -> {
            SleepDTO sleep = new SleepDTO();
            if(!InputsUtils.validateInputs(startDateInput,endDateInput,awakenings))
                return;
            sleep.setAwakenings(Integer.parseInt(awakenings.getText().toString()));
            sleep.setStartDate(selectedStartDate);
            sleep.setEndDate(selectedEndDate);
            ChildDTO currentChild = GlobalObjectsHolder.getInstance().getCurrentChild();
            if (currentChild != null) {
            sleep.setChildId(currentChild.getId());
            SleepApiImpl sleepApi = SleepApiImpl.getInstance(getContext());
            sleepApi.createSleep(sleep, new ResponseListener<SleepDTO>() {
                @Override
                public void onSuccess(SleepDTO response) {
                    Toast.makeText(getContext(), "Sleep Added Successfully", Toast.LENGTH_SHORT).show();
                    cancel();
                }

                @Override
                public void onError(ErrorDTO error) {
                    Toast.makeText(getContext(), "Error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
            } else {
                Toast.makeText(getContext(), "Error: Child not found", Toast.LENGTH_SHORT).show();
            }
        });
    }
    @Override
    public void onDatePick(int year, int month, int day) {
        selectedStartDate = year + "-" +month +"-" + day;
        selectedEndDate = year + "-" +month +"-" + day;
        startDateInput.setText(day + "/"+month+"/"+year);
        endDateInput.setText(day + "/"+month+"/"+year);
    }
}
