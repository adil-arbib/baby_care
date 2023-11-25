package com.groupe6.babycare.activities.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Window;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.annotation.NonNull;

import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.groupe6.babycare.R;
import com.groupe6.babycare.listeners.OnDatePickListener;

import java.util.Calendar;


public class AddChildDialog extends Dialog implements OnDatePickListener {

    private TextInputEditText birthdateInput;


    public AddChildDialog(@NonNull Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_add_child);

        Button btnAdd = findViewById(R.id.btn_add);
        Button btnCancel = findViewById(R.id.btn_cancel);
        TextInputEditText nameInput = findViewById(R.id.input_name);
        RadioGroup genderGrp = findViewById(R.id.radio_gender);
        genderGrp.check(R.id.radioBoy);
        birthdateInput = findViewById(R.id.input_date);
        birthdateInput.setOnClickListener(v -> {

            DatePickerDialog datePickerDialog = new DatePickerDialog(this, getContext());
            datePickerDialog.init();
            datePickerDialog.open();

        });

        btnCancel.setOnClickListener(v -> {
            dismiss();
        });

        btnAdd.setOnClickListener(v -> {
            RadioButton genderButton = findViewById(genderGrp.getCheckedRadioButtonId());

            CharSequence gender = genderButton.getText();
            System.out.println("name : "+nameInput.getText()
                    + " birthdate : "+birthdateInput.getText()
            +"gender : "+gender);
        });


    }


    @Override
    public void onDatePick(int year, int month, int day) {
        birthdateInput.setText(day + "/"+month+"/"+year);
    }


}
