package com.groupe6.babycare.activities.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.groupe6.babycare.R;
import com.groupe6.babycare.activities.MainActivity;
import com.groupe6.babycare.activities.SelectChildActivity;
import com.groupe6.babycare.adapters.ChildAdapter;
import com.groupe6.babycare.databinding.FragmentChildrenBinding;
import com.groupe6.babycare.databinding.FragmentHomeBinding;
import com.groupe6.babycare.dtos.ChildDTO;
import com.groupe6.babycare.holders.GlobalObjectsHolder;
import com.groupe6.babycare.listeners.ChildOnClickListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class ChildrenFragment extends Fragment implements ChildOnClickListener {

    private FragmentChildrenBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentChildrenBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle(R.string.children);
        // static data
        ChildAdapter childAdapter = new ChildAdapter(getActivity(), getStaticData(), this);
        binding.grid.setAdapter(childAdapter);
    }

    public List<ChildDTO> getStaticData() {
        return new ArrayList<>(Arrays.asList(
                new ChildDTO("Adil","boy")  ,
                new ChildDTO("Aissam","boy")  ,
                new ChildDTO("Salma","girl")
        ));
    }

    @Override
    public void onChildClick(ChildDTO child) {
        System.out.println(child);
    }
}