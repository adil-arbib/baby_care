package com.groupe6.babycare.activities.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Toast;

import com.groupe6.babycare.R;
import com.groupe6.babycare.activities.ChildInfoActivity;
import com.groupe6.babycare.activities.SelectChildActivity;
import com.groupe6.babycare.activities.dialogs.AddChildDialog;
import com.groupe6.babycare.adapters.ChildAdapter;
import com.groupe6.babycare.consts.GlobalKeys;
import com.groupe6.babycare.databinding.FragmentChildrenBinding;
import com.groupe6.babycare.dtos.children.ChildDTO;
import com.groupe6.babycare.dtos.error.ErrorDTO;
import com.groupe6.babycare.holders.GlobalObjectsHolder;
import com.groupe6.babycare.listeners.OnChildClickListener;
import com.groupe6.babycare.listeners.ResponseListener;
import com.groupe6.babycare.listeners.SelectChildListener;
import com.groupe6.babycare.repositories.implementations.ParentApiImpl;
import com.groupe6.babycare.utils.SharedPreferencesUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class ChildrenFragment extends Fragment implements OnChildClickListener, SelectChildListener {

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

        getChildren();

        //add click listener on add child button
        binding.icAdd.setOnClickListener(v -> {
            AddChildDialog dialog = new AddChildDialog(getActivity());
            WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
            lp.copyFrom(dialog.getWindow().getAttributes());
            lp.width = WindowManager.LayoutParams.MATCH_PARENT;
            lp.height = WindowManager.LayoutParams.WRAP_CONTENT ;
            dialog.show();
            dialog.getWindow().setAttributes(lp);
        });
    }

    public void getChildren() {
        binding.progressBar.setVisibility(View.VISIBLE);
        SharedPreferencesUtils sharedPreferencesUtils = SharedPreferencesUtils.getInstance(getContext());
        ParentApiImpl parentApi = ParentApiImpl.getInstance(getContext());
        parentApi.getChildren(Long.parseLong(sharedPreferencesUtils.getValue("parentId")), new ResponseListener<List<ChildDTO>>() {
            @Override
            public void onSuccess(List<ChildDTO> response) {
                ChildAdapter childAdapter = new ChildAdapter(getContext(), response, ChildrenFragment.this,ChildrenFragment.this);
                binding.grid.setAdapter(childAdapter);
                binding.progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onError(ErrorDTO error) {

            }
        });
    }

    @Override
    public void onChildClick(ChildDTO child) {
        Intent intent = new Intent(getActivity(), ChildInfoActivity.class);
        intent.putExtra(GlobalKeys.CHILD_KEY, child);
        startActivity(intent);
    }

    @Override
    public void onSelectChild(ChildDTO child, View viewToSelect, View previousView) {
        GlobalObjectsHolder.getInstance().setCurrentChild(child);
        viewToSelect.setVisibility(View.VISIBLE);
        Toast.makeText(getContext(), child.getFirstName()+" is the current child", Toast.LENGTH_SHORT).show();
        if(previousView != null) {
            previousView.setVisibility(View.GONE);
        }
    }
}