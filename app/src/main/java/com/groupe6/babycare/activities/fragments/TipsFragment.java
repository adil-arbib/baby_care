package com.groupe6.babycare.activities.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.groupe6.babycare.R;
import com.groupe6.babycare.activities.TipsInfoActivity;
import com.groupe6.babycare.adapters.TipsAdapter;
import com.groupe6.babycare.consts.GlobalKeys;
import com.groupe6.babycare.databinding.FragmentTipsBinding;
import com.groupe6.babycare.dtos.error.ErrorDTO;
import com.groupe6.babycare.dtos.tips.TipsDTO;
import com.groupe6.babycare.listeners.OnItemClickListener;
import com.groupe6.babycare.listeners.ResponseListener;
import com.groupe6.babycare.repositories.implementations.TipsApiImpl;

import java.util.List;


public class TipsFragment extends Fragment implements OnItemClickListener<TipsDTO> {


    FragmentTipsBinding binding;
    private TipsAdapter adapter;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentTipsBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle(R.string.tips);

        getData();
    }

    private void getData() {
        TipsApiImpl tipApi = TipsApiImpl.getInstance(getContext());
        tipApi.getAllTips(new ResponseListener<List<TipsDTO>>() {
            @Override
            public void onSuccess(List<TipsDTO> response) {
                TipsAdapter tipsAdapter = new TipsAdapter(response, TipsFragment.this);
                binding.recyclerView.setAdapter(tipsAdapter);
                binding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            }

            @Override
            public void onError(ErrorDTO error) {

            }
        });
    }

    @Override
    public void onClick(TipsDTO item) {
        Intent intent = new Intent(getActivity(), TipsInfoActivity.class);
        intent.putExtra(GlobalKeys.TIPS_KEY, item);
        startActivity(intent);
    }

}