package com.groupe6.babycare.activities.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Window;
import android.widget.Button;

import androidx.annotation.NonNull;

import com.groupe6.babycare.R;
import com.groupe6.babycare.listeners.OnDeleteConfirmationListener;
import com.groupe6.babycare.listeners.OnItemDeleteListener;

public class DeleteDialog<T> extends Dialog {

    private int itemPosition;

    private final OnDeleteConfirmationListener<T> onDeleteConfirmationListener;

    public DeleteDialog(@NonNull Context context, int itemPosition, final OnDeleteConfirmationListener<T> onDeleteConfirmationListener) {
        super(context);
        this.onDeleteConfirmationListener = onDeleteConfirmationListener;
        this.itemPosition = itemPosition;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_delete);

        Button btnCancel = findViewById(R.id.btnCancel);
        Button btnDelete = findViewById(R.id.btnDelete);

        btnCancel.setOnClickListener(v -> {
            cancel();
            onDeleteConfirmationListener.onConfirm(-1);
        });

        btnDelete.setOnClickListener(v -> {
            onDeleteConfirmationListener.onConfirm(itemPosition);
            cancel();
        });

    }
}
