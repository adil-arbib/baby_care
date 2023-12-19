package com.groupe6.babycare.activities.fragments;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Toast;

import com.groupe6.babycare.R;
import com.groupe6.babycare.activities.ActivityInfoActivity;
import com.groupe6.babycare.activities.SleepInfoActivity;
import com.groupe6.babycare.activities.dialogs.AddDiaperDialog;
import com.groupe6.babycare.activities.dialogs.AddNoteDialog;
import com.groupe6.babycare.activities.dialogs.AddSleepDialog;
import com.groupe6.babycare.activities.dialogs.DeleteDialog;
import com.groupe6.babycare.adapters.FoodAdapter;
import com.groupe6.babycare.adapters.SleepAdapter;
import com.groupe6.babycare.consts.GlobalKeys;
import com.groupe6.babycare.databinding.ActivitySleepInfoBinding;
import com.groupe6.babycare.databinding.FragmentFeedingBinding;
import com.groupe6.babycare.databinding.FragmentSleepingBinding;
import com.groupe6.babycare.dtos.diaper.DiaperDTO;
import com.groupe6.babycare.dtos.activities.ActivityDTO;
import com.groupe6.babycare.dtos.error.ErrorDTO;
import com.groupe6.babycare.dtos.feeding.FoodDTO;
import com.groupe6.babycare.dtos.sleeping.SleepDTO;
import com.groupe6.babycare.holders.GlobalObjectsHolder;
import com.groupe6.babycare.listeners.OnDeleteConfirmationListener;
import com.groupe6.babycare.listeners.OnItemClickListener;
import com.groupe6.babycare.listeners.ResponseListener;
import com.groupe6.babycare.listeners.SwipeListener;
import com.groupe6.babycare.listeners.SwipeToDeleteCallback;
import com.groupe6.babycare.repositories.implementations.ChildApiImpl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class SleepingFragment extends Fragment implements OnItemClickListener<SleepDTO>, OnDeleteConfirmationListener<SleepDTO>, SwipeListener {

    FragmentSleepingBinding binding;

    private List<SleepDTO> sleepList;

    private SleepAdapter sleepAdapter;

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

        binding.searchBar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.length() == 0) sleepAdapter.setSleepList(sleepList);
                else sleepAdapter.setSleepList(searchSleeps(s.toString()));
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


    }

    private void getSleepingData() {
        ChildApiImpl childApi = ChildApiImpl.getInstance(getContext());

        childApi.getChildSleep(GlobalObjectsHolder.getInstance().getCurrentChild().getId(),
                new ResponseListener<List<SleepDTO>>() {
            @Override
            public void onSuccess(List<SleepDTO> response) {
                sleepList = response;
                sleepAdapter = new SleepAdapter(response, SleepingFragment.this);
                binding.recyclerView.setAdapter(sleepAdapter);
                binding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

                SwipeToDeleteCallback swipeToDeleteCallback = new SwipeToDeleteCallback(sleepAdapter, SleepingFragment.this);
                ItemTouchHelper itemTouchHelper = new ItemTouchHelper(swipeToDeleteCallback);
                itemTouchHelper.attachToRecyclerView(binding.recyclerView);
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

    private List<SleepDTO> searchSleeps(String input) {
        ArrayList<SleepDTO> sleepDTOS = new ArrayList<>();
        for(SleepDTO sleepDTO : sleepList) {
            if(sleepDTO.getStartDate().toLowerCase().startsWith(input)
            || sleepDTO.getEndDate().toLowerCase().startsWith(input))
                sleepDTOS.add(sleepDTO);
        }

        return sleepDTOS;
    }

    @Override
    public void onConfirm(int itemPosition) {
        if(itemPosition != -1){
            Toast.makeText(getContext(), "Activity deleted successfully !!", Toast.LENGTH_SHORT).show();
            sleepAdapter.getSleepList().remove(itemPosition);
            sleepAdapter.notifyItemRemoved(itemPosition);
        }else {
            sleepAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onItemSwiped(int position) {
        DeleteDialog<SleepDTO> deleteDialog = new DeleteDialog<>(getContext(),position,SleepingFragment.this);
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(deleteDialog.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT ;
        deleteDialog.show();
        deleteDialog.getWindow().setAttributes(lp);
    }




}