package com.groupe6.babycare.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.groupe6.babycare.R;
import com.groupe6.babycare.consts.GlobalKeys;
import com.groupe6.babycare.databinding.ActivityNoteInfoBinding;
import com.groupe6.babycare.dtos.notes.NoteDTO;

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

        binding.icBack.setActivity(this);
    }

    private void displayData() {
        binding.inputTitle.setText(note.getTitle());
        binding.inputContent.setText(note.getContent());
    }

    public void cancelChanges() {
        displayData();
    }
}