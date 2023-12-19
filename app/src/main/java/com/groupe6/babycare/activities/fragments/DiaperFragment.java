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
import com.groupe6.babycare.activities.FeedingInfoActivity;
import com.groupe6.babycare.activities.dialogs.AddChildDialog;
import com.groupe6.babycare.activities.dialogs.AddDiaperDialog;
import com.groupe6.babycare.activities.dialogs.DeleteDialog;
import com.groupe6.babycare.adapters.DiaperAdapter;
import com.groupe6.babycare.adapters.FoodAdapter;

import com.groupe6.babycare.consts.GlobalKeys;
import com.groupe6.babycare.databinding.FragmentDiaperBinding;
import com.groupe6.babycare.dtos.diaper.DiaperDTO;
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


public class DiaperFragment extends Fragment implements OnItemClickListener<DiaperDTO>, SwipeListener, OnDeleteConfirmationListener<DiaperDTO> {

    private FragmentDiaperBinding binding;

    private DiaperAdapter diaperAdapter;

    private List<DiaperDTO> diaperList;


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

        getData();
        binding.icAdd.setOnClickListener(v -> {
            AddDiaperDialog dialog = new AddDiaperDialog(getActivity());
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
                if(s.length() == 0) diaperAdapter.setDiaperList(diaperList);
                else diaperAdapter.setDiaperList(searchDiapers(s.toString()));
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }

    private List<DiaperDTO> searchDiapers(String input) {
        ArrayList<DiaperDTO> diaperDTOS = new ArrayList<>();
        for(DiaperDTO diaperDTO : diaperList) {
            if(diaperDTO.getReminderDate().toLowerCase().startsWith(input))
                diaperDTOS.add(diaperDTO);
        }

        return diaperDTOS;
    }

    private void getData() {
        ChildApiImpl childApi = ChildApiImpl.getInstance(getContext());
        childApi.getChildDiaper(GlobalObjectsHolder.getInstance().getCurrentChild().getId(), new ResponseListener<List<DiaperDTO>>() {
            @Override
            public void onSuccess(List<DiaperDTO> response) {
                diaperList = response;
                diaperAdapter = new DiaperAdapter(response, DiaperFragment.this);
                binding.recyclerView.setAdapter(diaperAdapter);
                binding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

                SwipeToDeleteCallback swipeToDeleteCallback = new SwipeToDeleteCallback(diaperAdapter, DiaperFragment.this);
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
    public void onClick(DiaperDTO item) {
        Intent intent = new Intent(getActivity(), FeedingInfoActivity.class);
        intent.putExtra(GlobalKeys.DIAPER_KEY, (CharSequence) item);
        startActivity(intent);
    }

    @Override
    public void onConfirm(int itemPosition) {
        if(itemPosition != -1){
            Toast.makeText(getContext(), "Activity deleted successfully !!", Toast.LENGTH_SHORT).show();
            diaperAdapter.getDiaperList().remove(itemPosition);
            diaperAdapter.notifyItemRemoved(itemPosition);
        }else {
            diaperAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onItemSwiped(int position) {
        DeleteDialog<DiaperDTO> deleteDialog = new DeleteDialog<>(getContext(),position,DiaperFragment.this);
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(deleteDialog.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT ;
        deleteDialog.show();
        deleteDialog.getWindow().setAttributes(lp);
    }
}