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
import com.groupe6.babycare.activities.FeedingInfoActivity;
import com.groupe6.babycare.activities.dialogs.AddFeedingDialog;
import com.groupe6.babycare.adapters.FoodAdapter;
import com.groupe6.babycare.consts.GlobalKeys;
import com.groupe6.babycare.databinding.FragmentFeedingBinding;
import com.groupe6.babycare.dtos.error.ErrorDTO;
import com.groupe6.babycare.dtos.feeding.FoodDTO;
import com.groupe6.babycare.holders.GlobalObjectsHolder;
import com.groupe6.babycare.listeners.OnItemClickListener;
import com.groupe6.babycare.listeners.ResponseListener;
import com.groupe6.babycare.repositories.implementations.ChildApiImpl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class FeedingFragment extends Fragment implements OnItemClickListener<FoodDTO> {

    FragmentFeedingBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentFeedingBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle(R.string.feeding);

        getNutritions();

        binding.icAdd.setOnClickListener(v -> {
            AddFeedingDialog dialog = new AddFeedingDialog(getActivity());
            WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
            lp.copyFrom(dialog.getWindow().getAttributes());
            lp.width = WindowManager.LayoutParams.MATCH_PARENT;
            lp.height = WindowManager.LayoutParams.WRAP_CONTENT ;
            dialog.show();
            dialog.getWindow().setAttributes(lp);
        });
    }

    private void getNutritions() {
        ChildApiImpl childApi = ChildApiImpl.getInstance(getContext());
        Log.e("Call", "gnfad");
        childApi.getChildNutrition(GlobalObjectsHolder.getInstance().getCurrentChild().getId(), new ResponseListener<List<FoodDTO>>() {
            @Override
            public void onSuccess(List<FoodDTO> response) {
                FoodAdapter foodAdapter = new FoodAdapter(response, FeedingFragment.this);
                binding.recyclerFeeding.setAdapter(foodAdapter);
                binding.recyclerFeeding.setLayoutManager(new LinearLayoutManager(getContext()));

            }

            @Override
            public void onError(ErrorDTO error) {
                Log.e("Error", error.toString());
            }
        });
    }

    @Override
    public void onClick(FoodDTO item) {
        System.out.println(item);
        Intent intent = new Intent(getActivity(), FeedingInfoActivity.class);
        intent.putExtra(GlobalKeys.FEEDING_KEY, item);
        startActivity(intent);
    }
}