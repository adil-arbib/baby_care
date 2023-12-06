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
import com.groupe6.babycare.adapters.FoodAdapter;
import com.groupe6.babycare.adapters.SleepAdapter;
import com.groupe6.babycare.databinding.FragmentFeedingBinding;
import com.groupe6.babycare.databinding.FragmentSleepingBinding;
import com.groupe6.babycare.dtos.feeding.FoodDTO;
import com.groupe6.babycare.dtos.sleeping.SleepDTO;
import com.groupe6.babycare.listeners.OnItemClickListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class SleepingFragment extends Fragment implements OnItemClickListener<SleepDTO> {

    FragmentSleepingBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentSleepingBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle(R.string.sleeping);

        SleepAdapter adapter =
                new SleepAdapter(getStaticSleepingData(),this);
        binding.recyclerView.setAdapter(adapter);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

    }

    private List<SleepDTO> getStaticSleepingData() {
        return new ArrayList<>(
                Arrays.asList(
                        new SleepDTO(1L,"nap","done","10:00 PM Nov 25, 2032","10:00 PM Nov 25, 2032","10:30 PM",0),
                        new SleepDTO(1L,"deep","done","10:00 PM Nov 25, 2032","10:00 PM Nov 25, 2032","10:30 PM",0),
                        new SleepDTO(1L,"nap","undone","10:00 PM Nov 25, 2032","10:00 PM Nov 25, 2032","0:30 PM",0),
                        new SleepDTO(1L,"nap","done","10:00 PM Nov 25, 2032","10:00 PM Nov 25, 2032","10:30 PM",0),
                        new SleepDTO(1L,"deep","undone","10:00 PM Nov 25, 2032","10:00 PM Nov 25, 2032","10:30 PM",0),
                        new SleepDTO(1L,"deep","done","10:00 PM Nov 25, 2032","10:00 PM Nov 25, 2032","10:30 PM",0),
                        new SleepDTO(1L,"nap","undone","10:00 PM Nov 25, 2032","10:00 PM Nov 25, 2032","10:30 PM",0),
                        new SleepDTO(1L,"deep","done","10:00 PM Nov 25, 2032","10:00 PM Nov 25, 2032","10:30 PM",0)
                )
        );
    }

    @Override
    public void onClick(SleepDTO item) {
        System.out.println(item);
    }
}