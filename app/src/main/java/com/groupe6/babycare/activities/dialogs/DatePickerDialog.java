package com.groupe6.babycare.activities.dialogs;

import android.app.AlertDialog;
import android.content.Context;
import android.widget.DatePicker;

import com.groupe6.babycare.listeners.OnDatePickListener;

import java.util.Calendar;

public class DatePickerDialog {
    private android.app.DatePickerDialog datePickerDialog;

    private OnDatePickListener onDatePickListener;

    private Context context;

    public DatePickerDialog(OnDatePickListener onDatePickListener, Context context) {
        this.onDatePickListener = onDatePickListener;
        this.context = context;
    }

    public void init() {

        android.app.DatePickerDialog.OnDateSetListener dateSetListener = (view, year, month, dayOfMonth) -> {
            month++;
            onDatePickListener.onDatePick(year, month, dayOfMonth);
        };
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        int style = AlertDialog.THEME_HOLO_DARK;

        datePickerDialog = new android.app.DatePickerDialog(context, style,
                dateSetListener, year, month, day);
    }

    public void open() {
        datePickerDialog.show();
    }

}
