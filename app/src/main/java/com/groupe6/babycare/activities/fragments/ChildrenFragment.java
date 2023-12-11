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

import com.groupe6.babycare.R;
import com.groupe6.babycare.activities.ChildInfoActivity;
import com.groupe6.babycare.activities.dialogs.AddChildDialog;
import com.groupe6.babycare.adapters.ChildAdapter;
import com.groupe6.babycare.consts.GlobalKeys;
import com.groupe6.babycare.databinding.FragmentChildrenBinding;
import com.groupe6.babycare.dtos.children.ChildDTO;
import com.groupe6.babycare.listeners.OnChildClickListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class ChildrenFragment extends Fragment implements OnChildClickListener {

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
        // static data
        ChildAdapter childAdapter = new ChildAdapter(getActivity(), getStaticData(), this);
        binding.grid.setAdapter(childAdapter);

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

    public List<ChildDTO> getStaticData() {
        return new ArrayList<>(Arrays.asList(
                new ChildDTO(1L,"Adil","boy")  ,
                new ChildDTO(1L,"Aissam","boy")  ,
                new ChildDTO(1L,"Salma","girl")
        ));
    }

    @Override
    public void onChildClick(ChildDTO child) {
        Intent intent = new Intent(getActivity(), ChildInfoActivity.class);
        ChildDTO childDTO = new ChildDTO(1L, "Adil","Arbib",4,"30/05/2020","boy",10,100);
        intent.putExtra(GlobalKeys.CHILD_KEY, childDTO);
        startActivity(intent);
    }
}