package com.groupe6.babycare.activities.dialogs;

import static androidx.core.content.ContentProviderCompat.requireContext;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupMenu;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.material.textfield.TextInputEditText;
import com.groupe6.babycare.R;
import com.groupe6.babycare.dtos.error.ErrorDTO;
import com.groupe6.babycare.dtos.feeding.FoodCreateDTO;
import com.groupe6.babycare.dtos.feeding.FoodDTO;
import com.groupe6.babycare.holders.GlobalObjectsHolder;
import com.groupe6.babycare.listeners.OnDatePickListener;
import com.groupe6.babycare.listeners.OnTimePickerListener;
import com.groupe6.babycare.listeners.ResponseListener;
import com.groupe6.babycare.repositories.implementations.FoodApiImpl;
import com.groupe6.babycare.utils.InputsUtils;

public class AddFeedingDialog extends Dialog implements OnDatePickListener, OnTimePickerListener {

    private Button btnAdd, btnCancel;

    private TextInputEditText inputLabel, inputQuantity, inputDate, inputTime;

    private EditText inputType;

    private Context context;

    private String selectedDate;

    private String selectedTime;

    public AddFeedingDialog(@NonNull Context context) {
        super(context);
        this.context = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_add_feeding);

        inputLabel = findViewById(R.id.input_label);
        inputType = findViewById(R.id.input_type);
        inputDate = findViewById(R.id.input_date);
        inputTime = findViewById(R.id.input_time);
        inputQuantity = findViewById(R.id.input_quantity);
        btnAdd = findViewById(R.id.btn_add);
        btnCancel = findViewById(R.id.btn_cancel);

        inputType.setOnClickListener(v -> {
            PopupMenu popupMenu = new PopupMenu(context, inputType);
            popupMenu.getMenuInflater().inflate(R.menu.feeding_type_menu, popupMenu.getMenu());

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

        if (!InputsUtils.validateInputs(inputLabel, inputDate, inputTime, inputQuantity))
            return;
        if (!inputType.getText().toString().isEmpty()) {
            String label = inputLabel.getText().toString();
            String nutritionType = inputType.getText().toString();
            double quantity = Double.parseDouble(inputQuantity.getText().toString());
            String date = selectedDate + "T" + selectedTime;

            FoodCreateDTO food = new FoodCreateDTO(label, nutritionType, quantity, date, GlobalObjectsHolder.getInstance().getCurrentChild().getId());

            FoodApiImpl foodApi = FoodApiImpl.getInstance(getContext());
            foodApi.createFood(food, new ResponseListener<FoodCreateDTO>() {
                @Override
                public void onSuccess(FoodCreateDTO response) {
                    Toast.makeText(context, "Nutrition added successfully !!", Toast.LENGTH_SHORT).show();
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
