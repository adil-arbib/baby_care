package com.groupe6.babycare.activities.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import com.groupe6.babycare.R;
import com.groupe6.babycare.activities.FeedingInfoActivity;
import com.groupe6.babycare.activities.dialogs.AddChildDialog;
import com.groupe6.babycare.activities.dialogs.AddDiaperDialog;
import com.groupe6.babycare.adapters.DiaperAdapter;
import com.groupe6.babycare.adapters.FoodAdapter;

import com.groupe6.babycare.consts.GlobalKeys;
import com.groupe6.babycare.databinding.FragmentDiaperBinding;
import com.groupe6.babycare.dtos.diaper.DiaperDTO;
import com.groupe6.babycare.dtos.error.ErrorDTO;
import com.groupe6.babycare.dtos.feeding.FoodDTO;
import com.groupe6.babycare.holders.GlobalObjectsHolder;
import com.groupe6.babycare.listeners.OnItemClickListener;
import com.groupe6.babycare.listeners.ResponseListener;
import com.groupe6.babycare.listeners.SwipeToDeleteCallback;
import com.groupe6.babycare.repositories.implementations.ChildApiImpl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class DiaperFragment extends Fragment implements OnItemClickListener<DiaperDTO> {

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

//        binding.recyclerView.setAdapter(diaperAdapter);
//        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
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

//                SwipeToDeleteCallback swipeToDeleteCallback = new SwipeToDeleteCallback(foodAdapter, FeedingFragment.this);
//                ItemTouchHelper itemTouchHelper = new ItemTouchHelper(swipeToDeleteCallback);
//                itemTouchHelper.attachToRecyclerView(binding.recyclerFeeding);

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
}