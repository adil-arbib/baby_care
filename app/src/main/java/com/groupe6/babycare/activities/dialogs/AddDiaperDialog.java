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
import com.groupe6.babycare.dtos.diaper.DiaperCreateDTO;
import com.groupe6.babycare.dtos.error.ErrorDTO;
import com.groupe6.babycare.holders.GlobalObjectsHolder;
import com.groupe6.babycare.listeners.OnDatePickListener;
import com.groupe6.babycare.listeners.OnTimePickerListener;
import com.groupe6.babycare.listeners.ResponseListener;
import com.groupe6.babycare.repositories.implementations.DiaperApiImpl;
import com.groupe6.babycare.utils.InputsUtils;

public class AddDiaperDialog extends Dialog implements OnDatePickListener, OnTimePickerListener {

    private Button btnAdd, btnCancel;

    private TextInputEditText inputDate, inputTime;

    private EditText inputType;

    private Context context;

    private String selectedDate;

    private String selectedTime;

    public AddDiaperDialog(@NonNull Context context) {
        super(context);
        this.context = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_add_diaper);

        inputType = findViewById(R.id.input_type);
        inputDate = findViewById(R.id.input_date);
        inputTime = findViewById(R.id.input_time);

        btnAdd = findViewById(R.id.btn_add);
        btnCancel = findViewById(R.id.btn_cancel);

        inputType.setOnClickListener(v -> {
            PopupMenu popupMenu = new PopupMenu(context, inputType);
            popupMenu.getMenuInflater().inflate(R.menu.diaper_type_menu, popupMenu.getMenu());

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

        btnCancel.setOnClickListener(v -> {
            dismiss();
        });


        btnAdd.setOnClickListener(v -> {
            save();
        });


    }

    private void save() {

        if (!InputsUtils.validateInputs(inputDate, inputTime))
            return;

        if (!inputType.getText().toString().isEmpty()) {
            String diaperType = inputType.getText().toString();
            String date = selectedDate + "T" + selectedTime;

            DiaperCreateDTO diaper = new DiaperCreateDTO(date, GlobalObjectsHolder.getInstance().getCurrentChild().getId(),
                    diaperType);

            DiaperApiImpl diaperApi = DiaperApiImpl.getInstance(getContext());
            diaperApi.addDiaper(diaper, new ResponseListener<DiaperCreateDTO>() {
                @Override
                public void onSuccess(DiaperCreateDTO response) {
                    Toast.makeText(context, "Diaper added successfully !!", Toast.LENGTH_SHORT).show();
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
        inputDate.setText(year + "-" + month + "-" + day);
        selectedDate = year + "-" + month + "-" + day;
    }

    @Override
    public void onTimePick(int hour, int minute) {
        inputTime.setText(hour + ":" + minute);
        selectedTime = hour + ":" + minute + ":00";
    }
}
