package com.groupe6.babycare.activities.dialogs;

import static androidx.core.content.ContentProviderCompat.requireContext;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupMenu;

import androidx.annotation.NonNull;

import com.google.android.material.textfield.TextInputEditText;
import com.groupe6.babycare.R;

public class AddFeedingDialog extends Dialog {

    private Button btnAdd, btnCancel;

    private TextInputEditText inputLabel, inputQuantity, inputDate;

    private EditText inputType;

    private Context context;

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
        inputQuantity = findViewById(R.id.input_quantity);

        inputType.setOnClickListener(v -> {
            PopupMenu popupMenu = new PopupMenu(context, inputType);
            popupMenu.getMenuInflater().inflate(R.menu.feeding_type_menu, popupMenu.getMenu());

            popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem item) {
                    inputType.setText(item.getTitle());
                    return true;
                }
            });

            popupMenu.show();
        });

    }
}
