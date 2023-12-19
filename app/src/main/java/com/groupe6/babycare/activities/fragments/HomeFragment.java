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
import android.widget.TextView;

import com.groupe6.babycare.R;
import com.groupe6.babycare.adapters.ActivityLogAdapter;
import com.groupe6.babycare.databinding.FragmentHomeBinding;
import com.groupe6.babycare.dtos.activities.SimpleActivityDTO;
import com.groupe6.babycare.listeners.OnItemClickListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class HomeFragment extends Fragment implements OnItemClickListener<SimpleActivityDTO> {

    private FragmentHomeBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        getActivity().setTitle(R.string.home);
        binding.progressBar.setProgress(70);
        binding.txtPercentage.setText("70%");

        ActivityLogAdapter adapter =
                new ActivityLogAdapter( getStaticActivities(),this);
        binding.recyclerActivities.setAdapter(adapter);
        binding.recyclerActivities.setLayoutManager(new LinearLayoutManager(getContext()));

        TextView learnMoreTextView = view.findViewById(R.id.txt_learn_more);

        learnMoreTextView.setOnClickListener(v -> startActivity(new Intent(getContext(), TipsFragment.class)));
    }


    public List<SimpleActivityDTO> getStaticActivities() {
        return new ArrayList<>(Arrays.asList(
                new SimpleActivityDTO(1L,"sleeping", "2023-12-12 23:30:00"),
                new SimpleActivityDTO(2L,"diaper", "2023-12-12 23:15:00"),
                new SimpleActivityDTO(3L,"feeding", "2023-12-12 23:00:00")
        ));
    }


    @Override
    public void onClick(SimpleActivityDTO item) {
        System.out.println(item);
    }
}