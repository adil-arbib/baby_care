package com.groupe6.babycare.activities.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import com.groupe6.babycare.R;
import com.groupe6.babycare.activities.ActivityInfoActivity;
import com.groupe6.babycare.activities.NoteInfoActivity;
import com.groupe6.babycare.activities.dialogs.AddFeedingDialog;
import com.groupe6.babycare.activities.dialogs.AddNoteDialog;
import com.groupe6.babycare.adapters.NoteAdapter;
import com.groupe6.babycare.adapters.SleepAdapter;
import com.groupe6.babycare.consts.GlobalKeys;
import com.groupe6.babycare.databinding.ActivityNoteInfoBinding;
import com.groupe6.babycare.databinding.FragmentNotesBinding;
import com.groupe6.babycare.databinding.FragmentSleepingBinding;
import com.groupe6.babycare.dtos.error.ErrorDTO;
import com.groupe6.babycare.dtos.notes.NoteDTO;
import com.groupe6.babycare.listeners.OnItemClickListener;
import com.groupe6.babycare.listeners.OnItemDeleteListener;
import com.groupe6.babycare.listeners.ResponseListener;
import com.groupe6.babycare.repositories.implementations.NoteApiImpl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class NotesFragment extends Fragment implements OnItemDeleteListener<NoteDTO>, OnItemClickListener<NoteDTO> {


    FragmentNotesBinding binding;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentNotesBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle(R.string.notes);

        getData();
        binding.icAdd.setOnClickListener(v -> {
            AddNoteDialog dialog = new AddNoteDialog(getActivity());
            WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
            lp.copyFrom(dialog.getWindow().getAttributes());
            lp.width = WindowManager.LayoutParams.MATCH_PARENT;
            lp.height = WindowManager.LayoutParams.WRAP_CONTENT ;
            dialog.show();
            dialog.getWindow().setAttributes(lp);
        });
    }

    private void getData() {
        NoteApiImpl noteApi = NoteApiImpl.getInstance(getContext());
        noteApi.getAllNotes(new ResponseListener<List<NoteDTO>>() {
            @Override
            public void onSuccess(List<NoteDTO> response) {
                NoteAdapter noteAdapter = new NoteAdapter(
                        response,
                        NotesFragment.this,
                        NotesFragment.this
                );
                binding.recyclerView.setAdapter(noteAdapter);
                binding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            }

            @Override
            public void onError(ErrorDTO error) {

            }
        });
    }

    @Override
    public void onClick(NoteDTO item) {
        System.out.println(item);
        Intent intent = new Intent(getActivity(), NoteInfoActivity.class);
        intent.putExtra(GlobalKeys.NOTE_KEY, item);
        startActivity(intent);
    }

    @Override
    public void onDeleteRequest(NoteDTO item) {

    }
}