package com.groupe6.babycare.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.groupe6.babycare.R;
import com.groupe6.babycare.consts.GlobalKeys;
import com.groupe6.babycare.databinding.ActivityNoteInfoBinding;
import com.groupe6.babycare.dtos.activities.ActivityDTO;
import com.groupe6.babycare.dtos.error.ErrorDTO;
import com.groupe6.babycare.dtos.notes.NoteDTO;
import com.groupe6.babycare.listeners.ResponseListener;
import com.groupe6.babycare.repositories.implementations.ActivityApiImpl;
import com.groupe6.babycare.repositories.implementations.NoteApiImpl;
import com.groupe6.babycare.utils.InputsUtils;

public class NoteInfoActivity extends AppCompatActivity {

    private ActivityNoteInfoBinding binding;

    private NoteDTO note;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityNoteInfoBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            note = extras.getParcelable(GlobalKeys.NOTE_KEY);
        }
        displayData();

        binding.btnAdd.setOnClickListener(v -> saveChanges());
        binding.btnCancel.setOnClickListener(v -> cancelChanges());


        binding.icBack.setActivity(this);
    }

    private void displayData() {
        binding.inputTitle.setText(note.getTitle());
        binding.inputContent.setText(note.getContent());
    }

    private void saveChanges() {
        if(!InputsUtils.validateInputs(binding.inputContent, binding.inputTitle))
            return;

        NoteDTO noteDTO = new NoteDTO();
        noteDTO.setId(note.getId());
        noteDTO.setContent(binding.inputContent.getText().toString());
        noteDTO.setDate(binding.inputTitle.getText().toString());


        NoteApiImpl noteApi = NoteApiImpl.getInstance(getApplicationContext());
        noteApi.updateNote(noteDTO, note.getId(), new ResponseListener<NoteDTO>() {
            @Override
            public void onSuccess(NoteDTO response) {
                Toast.makeText(NoteInfoActivity.this, "Updated successfully!!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(ErrorDTO error) {
                Toast.makeText(NoteInfoActivity.this, "An error was occurred!!", Toast.LENGTH_SHORT).show();

            }
        });

    }

    public void cancelChanges() {
        displayData();
    }
}