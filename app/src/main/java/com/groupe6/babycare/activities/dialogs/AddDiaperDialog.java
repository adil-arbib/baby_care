package com.groupe6.babycare.activities.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupMenu;

import androidx.annotation.NonNull;

import com.google.android.material.textfield.TextInputEditText;
import com.groupe6.babycare.R;
import com.groupe6.babycare.listeners.OnDatePickListener;
import com.groupe6.babycare.listeners.OnTimePickerListener;

public class AddDiaperDialog extends Dialog implements OnDatePickListener, OnTimePickerListener {

    private Button btnAdd, btnCancel;

    private TextInputEditText inputDate, inputTime;

    private EditText inputType;

    private Context context;

    private String fullDate;

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

    }

    @Override
    public void onDatePick(int year, int month, int day) {

    }

    @Override
    public void onTimePick(int hour, int minute) {

    }
}
