package com.groupe6.babycare.activities.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupMenu;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.material.textfield.TextInputEditText;
import com.groupe6.babycare.R;
import com.groupe6.babycare.dtos.error.ErrorDTO;
import com.groupe6.babycare.dtos.healthcare.HealthCareCreateDTO;
import com.groupe6.babycare.holders.GlobalObjectsHolder;
import com.groupe6.babycare.listeners.OnDatePickListener;
import com.groupe6.babycare.listeners.OnTimePickerListener;
import com.groupe6.babycare.listeners.ResponseListener;
import com.groupe6.babycare.repositories.implementations.HealthCareImpl;
import com.groupe6.babycare.utils.InputsUtils;

public class AddHealthCare  extends Dialog implements OnDatePickListener, OnTimePickerListener {
    private Button btnAdd, btnCancel;

    private TextInputEditText inputNotes, inputDate, inputTime;

    private EditText inputType;

    private Context context;

    private String selectedDate;

    private String selectedTime;

    public AddHealthCare(@NonNull Context context) {
        super(context);
        this.context = context;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_add_healthcare);

        inputType = findViewById(R.id.input_type);
        inputDate = findViewById(R.id.input_date);
        inputTime = findViewById(R.id.input_time);
        inputNotes = findViewById(R.id.input_notes);
        btnAdd = findViewById(R.id.btn_add);
        btnCancel = findViewById(R.id.btn_cancel);

        inputType.setOnClickListener(v -> {
            PopupMenu popupMenu = new PopupMenu(context, inputType);
            popupMenu.getMenuInflater().inflate(R.menu.healthcare_type_menu, popupMenu.getMenu());

            popupMenu.setOnMenuItemClickListener(item -> {
                inputType.setText(item.getTitle());
                return true;
            });

            popupMenu.show();
        });

        inputDate.setOnClickListener(v -> {
            DatePickerDialog datePickerDialog = new DatePickerDialog(this, getContext());
            datePickerDialog.init();
            datePickerDialog.open();
        });

        inputTime.setOnClickListener(v -> {
            TimePickerDialog timePickerDialog = new TimePickerDialog(this, getContext());
            timePickerDialog.init();
            timePickerDialog.open();
        });


        btnCancel.setOnClickListener(v -> dismiss());


        btnAdd.setOnClickListener(v -> save());


    }

    private void save() {

        if (!InputsUtils.validateInputs( inputDate, inputTime, inputNotes))
            return;
        if (!inputType.getText().toString().isEmpty()) {
            String healthCareType = inputType.getText().toString();
            String healthCareNotes = inputNotes.getText().toString();
            String date = selectedDate + "T" + selectedTime;

            HealthCareCreateDTO healthCareCreateDTO = new HealthCareCreateDTO(date, date,healthCareType, healthCareNotes, GlobalObjectsHolder.getInstance().getCurrentChild().getId());

           HealthCareImpl healthCareApi = HealthCareImpl.getInstance(getContext());
            healthCareApi.createHealthCare(healthCareCreateDTO, new ResponseListener<HealthCareCreateDTO>() {
                @Override
                public void onSuccess(HealthCareCreateDTO response) {
                    Toast.makeText(context, "HealthCare added successfully !!", Toast.LENGTH_SHORT).show();
                    dismiss();
                }

                @Override
                public void onError(ErrorDTO error) {
                    Toast.makeText(context, "An error was occured !!", Toast.LENGTH_SHORT).show();
                    Log.e("Error", error.getMessage());
                }
            });
        }

    }

    @Override
    public void onDatePick(int year, int month, int day) {
        inputDate.setText(year+"-"+month+"-"+day);
        selectedDate = year+"-"+month+"-"+day;
    }

    @Override
    public void onTimePick(int hour, int minute) {
        inputTime.setText(hour+":"+minute);
        selectedTime = hour+":"+minute+":00";
    }

}
