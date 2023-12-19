package com.groupe6.babycare.activities.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Window;
import android.widget.Button;

import androidx.annotation.NonNull;

import com.groupe6.babycare.R;
import com.groupe6.babycare.listeners.OnChildDeleteListener;
import com.groupe6.babycare.listeners.OnDeleteConfirmationListener;

public class SimpleDeleteDialog  extends Dialog {

    private String itemName;

    private final OnChildDeleteListener listener;

    public SimpleDeleteDialog(@NonNull Context context, String itemName, OnChildDeleteListener listener) {
        super(context);
        this.itemName = itemName;
        this.listener = listener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_delete);
        Button btnCancel = findViewById(R.id.btnCancel);
        Button btnDelete = findViewById(R.id.btnDelete);

        btnCancel.setOnClickListener(v -> {
            listener.onDelete(false);
            cancel();
        });

        btnDelete.setOnClickListener(v -> {
            listener.onDelete(true);
            cancel();
        });
    }
}
