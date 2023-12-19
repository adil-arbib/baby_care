package com.groupe6.babycare.activities.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import com.groupe6.babycare.R;
import com.groupe6.babycare.activities.ActivityInfoActivity;
import com.groupe6.babycare.activities.SleepInfoActivity;
import com.groupe6.babycare.activities.dialogs.AddDiaperDialog;
import com.groupe6.babycare.activities.dialogs.AddNoteDialog;
import com.groupe6.babycare.activities.dialogs.AddSleepDialog;
import com.groupe6.babycare.adapters.FoodAdapter;
import com.groupe6.babycare.adapters.SleepAdapter;
import com.groupe6.babycare.consts.GlobalKeys;
import com.groupe6.babycare.databinding.ActivitySleepInfoBinding;
import com.groupe6.babycare.databinding.FragmentFeedingBinding;
import com.groupe6.babycare.databinding.FragmentSleepingBinding;
import com.groupe6.babycare.dtos.error.ErrorDTO;
import com.groupe6.babycare.dtos.feeding.FoodDTO;
import com.groupe6.babycare.dtos.sleeping.SleepDTO;
import com.groupe6.babycare.holders.GlobalObjectsHolder;
import com.groupe6.babycare.listeners.OnItemClickListener;
import com.groupe6.babycare.listeners.ResponseListener;
import com.groupe6.babycare.repositories.implementations.ChildApiImpl;

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
        getSleepingData();

        binding.icAdd.setOnClickListener(v -> {
            AddSleepDialog dialog = new AddSleepDialog(getActivity());
            WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
            lp.copyFrom(dialog.getWindow().getAttributes());
            lp.width = WindowManager.LayoutParams.MATCH_PARENT;
            lp.height = WindowManager.LayoutParams.WRAP_CONTENT ;
            dialog.show();
            dialog.getWindow().setAttributes(lp);
        });


    }

    private void getSleepingData() {
        ChildApiImpl childApi = ChildApiImpl.getInstance(getContext());

        childApi.getChildSleep(GlobalObjectsHolder.getInstance().getCurrentChild().getId(),
                new ResponseListener<List<SleepDTO>>() {
            @Override
            public void onSuccess(List<SleepDTO> response) {
                SleepAdapter sleepAdapter = new SleepAdapter(response, SleepingFragment.this);
                binding.recyclerView.setAdapter(sleepAdapter);
                binding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

            }

            @Override
            public void onError(ErrorDTO error) {
                Log.e("Error", error.toString());
            }
        });
    }

    @Override
    public void onClick(SleepDTO item) {
        System.out.println(item);
        Intent intent = new Intent(getActivity(), SleepInfoActivity.class);
        intent.putExtra(GlobalKeys.SLEEP_KEY, item);
        startActivity(intent);
    }
}