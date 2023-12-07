package com.groupe6.babycare.activities.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import com.groupe6.babycare.R;
import com.groupe6.babycare.activities.dialogs.AddChildDialog;
import com.groupe6.babycare.activities.dialogs.AddFeedingDialog;
import com.groupe6.babycare.adapters.ActivityLogAdapter;
import com.groupe6.babycare.adapters.FoodAdapter;
import com.groupe6.babycare.databinding.FragmentChildrenBinding;
import com.groupe6.babycare.databinding.FragmentFeedingBinding;
import com.groupe6.babycare.dtos.feeding.FoodDTO;
import com.groupe6.babycare.listeners.OnItemClickListener;

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

        FoodAdapter adapter =
                new FoodAdapter(getStaticFeedingData(),this);
        binding.recyclerFeeding.setAdapter(adapter);
        binding.recyclerFeeding.setLayoutManager(new LinearLayoutManager(getContext()));

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

    private List<FoodDTO> getStaticFeedingData() {
        return new ArrayList<>(
                Arrays.asList(
                        new FoodDTO(1L,"solid","Mashed Banana",1.,"done","Nov 26, 2032 10:30 PM"),
                        new FoodDTO(1L,"liquid","Cereal",1.,"undone","Nov 26, 2032 10:30 PM"),
                        new FoodDTO(1L,"breast","Breast feeding",1.,"done","Nov 26, 2032 10:30 PM"),
                        new FoodDTO(1L,"solid","Avocado Puree",1.,"done","Nov 26, 2032 10:30 PM"),
                        new FoodDTO(1L,"liquid","Vegetable Soup",1.,"undone","Nov 26, 2032 10:30 PM"),
                        new FoodDTO(1L,"solid","Applesauce",1.,"done","Nov 26, 2032 10:30 PM"),
                        new FoodDTO(1L,"solid","Applesauce",1.,"done","Nov 26, 2032 10:30 PM"),
                        new FoodDTO(1L,"solid","Applesauce",1.,"done","Nov 26, 2032 10:30 PM"),
                        new FoodDTO(1L,"solid","Applesauce",1.,"done","Nov 26, 2032 10:30 PM"),
                        new FoodDTO(1L,"solid","Applesauce",1.,"done","Nov 26, 2032 10:30 PM"),
                        new FoodDTO(1L,"solid","Applesauce",1.,"done","Nov 26, 2032 10:30 PM")
                )
        );
    }

    @Override
    public void onClick(FoodDTO item) {
        System.out.println(item);
    }
}