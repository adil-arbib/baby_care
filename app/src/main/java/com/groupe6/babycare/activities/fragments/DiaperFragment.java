package com.groupe6.babycare.activities.fragments;

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
import com.groupe6.babycare.activities.dialogs.AddChildDialog;
import com.groupe6.babycare.activities.dialogs.AddDiaperDialog;
import com.groupe6.babycare.adapters.DiaperAdapter;
import com.groupe6.babycare.databinding.FragmentChildrenBinding;
import com.groupe6.babycare.databinding.FragmentDiaperBinding;
import com.groupe6.babycare.dtos.diaper.DiaperDTO;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class DiaperFragment extends Fragment {

    private FragmentDiaperBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getActivity().setTitle(R.string.diaper);
        binding = FragmentDiaperBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        DiaperAdapter diaperAdapter = new DiaperAdapter(getDate());
        binding.recyclerView.setAdapter(diaperAdapter);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        binding.icAdd.setOnClickListener(v -> {
            AddDiaperDialog dialog = new AddDiaperDialog(getActivity());
            WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
            lp.copyFrom(dialog.getWindow().getAttributes());
            lp.width = WindowManager.LayoutParams.MATCH_PARENT;
            lp.height = WindowManager.LayoutParams.WRAP_CONTENT ;
            dialog.show();
            dialog.getWindow().setAttributes(lp);
        });

    }

    private static List<DiaperDTO> getDate() {
        return new ArrayList<>(Arrays.asList(
                new DiaperDTO(1L, "DRY"),
                new DiaperDTO(1L, "WET"),
                new DiaperDTO(1L, "LIQUID"),
                new DiaperDTO(1L, "DRY"),
                new DiaperDTO(1L, "DRY"),
                new DiaperDTO(1L, "LIQUID")

        ));
    }



}