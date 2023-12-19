package com.groupe6.babycare.activities.fragments;

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
import com.groupe6.babycare.activities.HealthCareInfoActivity;
import com.groupe6.babycare.activities.dialogs.AddHealthCare;
import com.groupe6.babycare.activities.dialogs.DeleteDialog;
import com.groupe6.babycare.adapters.HealthCareAdapter;
import com.groupe6.babycare.consts.GlobalKeys;
import com.groupe6.babycare.databinding.FragmentHealthcareBinding;
import com.groupe6.babycare.dtos.error.ErrorDTO;
import com.groupe6.babycare.dtos.healthcare.HealthCareDTO;
import com.groupe6.babycare.holders.GlobalObjectsHolder;
import com.groupe6.babycare.listeners.OnDeleteConfirmationListener;
import com.groupe6.babycare.listeners.OnItemClickListener;
import com.groupe6.babycare.listeners.ResponseListener;
import com.groupe6.babycare.listeners.SwipeListener;
import com.groupe6.babycare.listeners.SwipeToDeleteCallback;
import com.groupe6.babycare.repositories.implementations.ChildApiImpl;

import java.util.ArrayList;
import java.util.List;


public class HealthcareFragment extends Fragment implements OnItemClickListener<HealthCareDTO>, SwipeListener, OnDeleteConfirmationListener<HealthCareDTO> {

    FragmentHealthcareBinding binding;

    private List<HealthCareDTO> healthCareDTOS;

    private HealthCareAdapter healthCareAdapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentHealthcareBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle(R.string.healthcare);
        getHealthCare();
        binding.searchBar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.length() == 0) healthCareAdapter.setHealthCare(healthCareDTOS);
                else healthCareAdapter.setHealthCare(searchHealthCare(s.toString()));
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        binding.icAdd.setOnClickListener(v -> {
            AddHealthCare dialog = new AddHealthCare(getActivity());
            WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
            lp.copyFrom(dialog.getWindow().getAttributes());
            lp.width = WindowManager.LayoutParams.MATCH_PARENT;
            lp.height = WindowManager.LayoutParams.WRAP_CONTENT ;
            dialog.show();
            dialog.getWindow().setAttributes(lp);
        });
    }

    private void getHealthCare() {
        ChildApiImpl childApi = ChildApiImpl.getInstance(getContext());
        Log.e("Call", "gnfad");
        childApi.getChildHealthCare(GlobalObjectsHolder.getInstance().getCurrentChild().getId(), new ResponseListener<List<HealthCareDTO>>() {
            @Override
            public void onSuccess(List<HealthCareDTO> response) {
                healthCareDTOS = response;
                healthCareAdapter = new HealthCareAdapter(response, HealthcareFragment.this);
                binding.recyclerView.setAdapter(healthCareAdapter);
                binding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

                SwipeToDeleteCallback swipeToDeleteCallback = new SwipeToDeleteCallback(healthCareAdapter, HealthcareFragment.this);
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
    public void onClick(HealthCareDTO item) {
        System.out.println(item);
        Intent intent = new Intent(getActivity(), HealthCareInfoActivity.class);
        intent.putExtra(GlobalKeys.HEALTHCARE_KEY, item);
        startActivity(intent);
    }

    private List<HealthCareDTO> searchHealthCare(String input) {
        ArrayList<HealthCareDTO> healthCareDTOS1 = new ArrayList<>();
        for(HealthCareDTO health : healthCareDTOS) {
            if(health.getNotes().toLowerCase().startsWith(input) || health.getReminderDate().startsWith(input)
                    || health.getReminderState().toLowerCase().equals(input))
                healthCareDTOS1.add(health);
        }

        return healthCareDTOS1;
    }


    @Override
    public void onItemSwiped(int position) {
        DeleteDialog<HealthCareDTO> deleteDialog = new DeleteDialog<>(getContext(),position,HealthcareFragment.this);
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(deleteDialog.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT ;
        deleteDialog.show();
        deleteDialog.getWindow().setAttributes(lp);

    }

    @Override
    public void onConfirm(int itemPosition) {
        if(itemPosition != -1){
            HealthCareDTO health = healthCareAdapter.gethealthCareList().get(itemPosition);
            healthCareAdapter.gethealthCareList().remove(itemPosition);
            Toast.makeText(getContext(), health.getNotes() +" deleted successfully !!", Toast.LENGTH_SHORT).show();
            healthCareAdapter.notifyItemRemoved(itemPosition);
        }else {
            healthCareAdapter.notifyDataSetChanged();
        }
    }


}