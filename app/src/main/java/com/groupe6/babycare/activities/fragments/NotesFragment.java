package com.groupe6.babycare.activities.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.groupe6.babycare.R;
import com.groupe6.babycare.adapters.NoteAdapter;
import com.groupe6.babycare.adapters.SleepAdapter;
import com.groupe6.babycare.databinding.FragmentNotesBinding;
import com.groupe6.babycare.databinding.FragmentSleepingBinding;
import com.groupe6.babycare.dtos.notes.NoteDTO;
import com.groupe6.babycare.listeners.OnItemClickListener;
import com.groupe6.babycare.listeners.OnItemDeleteListener;

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

        NoteAdapter adapter =
                new NoteAdapter(getStaticNotesData(),this, this);
        binding.recyclerView.setAdapter(adapter);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    private List<NoteDTO> getStaticNotesData() {
        return new ArrayList<>(
                Arrays.asList(
                        new NoteDTO(1L, "Morning Reflections","ook a quiet moment to reflect on goals and priorities today. Excited about the possibilities ahead.","December 7, 2023"),
                        new NoteDTO(1L, "Morning Reflections","ook a quiet moment to reflect on goals and priorities today. Excited about the possibilities ahead.","December 7, 2023"),
                        new NoteDTO(1L, "Morning Reflections","ook a quiet moment to reflect on goals and priorities today. Excited about the possibilities ahead.","December 7, 2023"),
                        new NoteDTO(1L, "Morning Reflections","ook a quiet moment to reflect on goals and priorities today. Excited about the possibilities ahead.","December 7, 2023"),
                        new NoteDTO(1L, "Morning Reflections","ook a quiet moment to reflect on goals and priorities today. Excited about the possibilities ahead.","December 7, 2023"),
                        new NoteDTO(1L, "Morning Reflections","ook a quiet moment to reflect on goals and priorities today. Excited about the possibilities ahead.","December 7, 2023"),
                        new NoteDTO(1L, "Morning Reflections","ook a quiet moment to reflect on goals and priorities today. Excited about the possibilities ahead.","December 7, 2023"),
                        new NoteDTO(1L, "Morning Reflections","ook a quiet moment to reflect on goals and priorities today. Excited about the possibilities ahead.","December 7, 2023"),
                        new NoteDTO(1L, "Morning Reflections","ook a quiet moment to reflect on goals and priorities today. Excited about the possibilities ahead.","December 7, 2023"),
                        new NoteDTO(1L, "Morning Reflections","ook a quiet moment to reflect on goals and priorities today. Excited about the possibilities ahead.","December 7, 2023")
                )
        );
    }

    @Override
    public void onClick(NoteDTO item) {

    }

    @Override
    public void onDeleteRequest(NoteDTO item) {

    }
}