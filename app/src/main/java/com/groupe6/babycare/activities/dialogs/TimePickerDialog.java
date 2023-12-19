package com.groupe6.babycare.activities.dialogs;

import android.app.AlertDialog;
import android.content.Context;
import android.widget.TimePicker;

import com.groupe6.babycare.R;
import com.groupe6.babycare.listeners.OnTimePickerListener;

import java.util.Calendar;

public class TimePickerDialog {

    private android.app.TimePickerDialog timePickerDialog;

    private OnTimePickerListener onTimePickerListener;

    private Context context;

    public TimePickerDialog(OnTimePickerListener onTimePickerListener, Context context) {
        this.onTimePickerListener = onTimePickerListener;
        this.context = context;
    }

    public void init() {
        android.app.TimePickerDialog.OnTimeSetListener onTimeSetListener = (view, hourOfDay, minute) -> {
            onTimePickerListener.onTimePick(hourOfDay, minute);
        };
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR);
        int minute = calendar.get(Calendar.MINUTE);


        timePickerDialog = new android.app.TimePickerDialog(context, R.style.TimePickerDialogStyle, onTimeSetListener, hour, minute, true);
    }

    public void open() {
        timePickerDialog.show();
    }
}
