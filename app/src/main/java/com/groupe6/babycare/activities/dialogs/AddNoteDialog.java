package com.groupe6.babycare.activities.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.material.textfield.TextInputEditText;
import com.groupe6.babycare.R;
import com.groupe6.babycare.dtos.error.ErrorDTO;
import com.groupe6.babycare.dtos.notes.NoteDTO;
import com.groupe6.babycare.listeners.OnDatePickListener;
import com.groupe6.babycare.listeners.OnTimePickerListener;
import com.groupe6.babycare.listeners.ResponseListener;
import com.groupe6.babycare.repositories.apis.NoteApi;
import com.groupe6.babycare.repositories.implementations.NoteApiImpl;
import com.groupe6.babycare.utils.InputsUtils;

public class AddNoteDialog extends Dialog   {

    private Button btnAdd, btnCancel;
    private TextInputEditText inputTitle, inputContent;

    public AddNoteDialog(@NonNull Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_add_note);

        btnAdd = findViewById(R.id.btn_add);
        btnCancel = findViewById(R.id.btn_cancel);

        inputTitle = findViewById(R.id.input_title);
        inputContent = findViewById(R.id.input_content);

        btnCancel.setOnClickListener(v -> dismiss());

        btnAdd.setOnClickListener(v -> {
            save();
        });

    }

    private void save() {

        if (!InputsUtils.validateInputs(inputTitle, inputContent))
            return;

        NoteDTO noteDTO = new NoteDTO();
        noteDTO.setContent(inputContent.getText().toString());
        noteDTO.setTitle(inputTitle.getText().toString());

        NoteApiImpl noteApi = NoteApiImpl.getInstance(getContext());

        noteApi.createNote(noteDTO, new ResponseListener<NoteDTO>() {
            @Override
            public void onSuccess(NoteDTO response) {
                Toast.makeText(getContext(), "Note added successfully !!", Toast.LENGTH_SHORT).show();
                dismiss();
            }

            @Override
            public void onError(ErrorDTO error) {
                Toast.makeText(getContext(), "An error was occured !!", Toast.LENGTH_SHORT).show();
            }
        });

    }

}
