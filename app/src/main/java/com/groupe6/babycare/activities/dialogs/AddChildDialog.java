package com.groupe6.babycare.activities.dialogs;


import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Window;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.material.textfield.TextInputEditText;
import com.groupe6.babycare.R;
import com.groupe6.babycare.dtos.children.ChildDTO;
import com.groupe6.babycare.dtos.error.ErrorDTO;
import com.groupe6.babycare.listeners.OnDatePickListener;
import com.groupe6.babycare.listeners.ResponseListener;
import com.groupe6.babycare.repositories.implementations.ParentApiImpl;
import com.groupe6.babycare.utils.InputsUtils;
import com.groupe6.babycare.utils.SharedPreferencesUtils;



public class AddChildDialog extends Dialog implements OnDatePickListener {

    private TextInputEditText birthdateInput;
    private String selectedDate;


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
        TextInputEditText firstnameInput = findViewById(R.id.input_firstname);
        TextInputEditText lastnameInput = findViewById(R.id.input_lastname);
        TextInputEditText weightInput = findViewById(R.id.input_weight);
        TextInputEditText heightInput = findViewById(R.id.input_height);
        RadioButton radioBoy = findViewById(R.id.radioBoy);

        birthdateInput = findViewById(R.id.input_date);
        birthdateInput.setOnClickListener(v -> {

            DatePickerDialog datePickerDialog = new DatePickerDialog(this, getContext());
            datePickerDialog.init();
            datePickerDialog.open();

        });

        btnCancel.setOnClickListener(v -> dismiss());

        btnAdd.setOnClickListener(v -> {
            ChildDTO child = new ChildDTO();
            if(!InputsUtils.validateInputs(firstnameInput, lastnameInput, birthdateInput,weightInput, heightInput ))
                return;
            child.setFirstName(firstnameInput.getText().toString());
            child.setLastName(lastnameInput.getText().toString());
            child.setBirthDate(selectedDate);
            child.setWeight(Double.parseDouble(weightInput.getText().toString()));
            child.setHeight(Double.parseDouble(heightInput.getText().toString()));
            String gender = radioBoy.isSelected() ? "MALE" : "FEMALE";
            child.setGender(gender);
            SharedPreferencesUtils sharedPreferencesUtils = SharedPreferencesUtils.getInstance(getContext());
            ParentApiImpl parentApi = ParentApiImpl.getInstance(getContext());
            parentApi.addChild(child, Long.parseLong(sharedPreferencesUtils.getValue("parentId")), new ResponseListener<Void>() {
                @Override
                public void onSuccess(Void response) {
                    Toast.makeText(getContext(), "Added Successfully !!", Toast.LENGTH_SHORT).show();
                    cancel();
                }

                @Override
                public void onError(ErrorDTO error) {

                }
            });
        });


    }


    @Override
    public void onDatePick(int year, int month, int day) {
        selectedDate = year + "-" +month +"-" + day;
        birthdateInput.setText(day + "/"+month+"/"+year);
    }


}
