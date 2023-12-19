package com.groupe6.babycare.activities.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Toast;

import com.groupe6.babycare.R;
import com.groupe6.babycare.activities.ActivityInfoActivity;
import com.groupe6.babycare.activities.FeedingInfoActivity;
import com.groupe6.babycare.activities.MainActivity;
import com.groupe6.babycare.activities.dialogs.AddActivityDialog;
import com.groupe6.babycare.activities.dialogs.AddDiaperDialog;
import com.groupe6.babycare.adapters.ActivityAdapter;
import com.groupe6.babycare.adapters.SleepAdapter;
import com.groupe6.babycare.consts.GlobalKeys;
import com.groupe6.babycare.databinding.FragmentActivitiesBinding;
import com.groupe6.babycare.databinding.FragmentSleepingBinding;
import com.groupe6.babycare.dtos.activities.ActivityDTO;
import com.groupe6.babycare.dtos.sleeping.SleepDTO;
import com.groupe6.babycare.listeners.OnItemClickListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class ActivitiesFragment extends Fragment implements OnItemClickListener<ActivityDTO> {

    FragmentActivitiesBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentActivitiesBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle(R.string.activities);

        ActivityAdapter adapter =
                new ActivityAdapter(getStaticActivityData(),this);

        binding.recyclerView.setAdapter(adapter);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        binding.icAdd.setOnClickListener(v -> {
            AddActivityDialog dialog = new AddActivityDialog(getActivity());
            WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
            lp.copyFrom(dialog.getWindow().getAttributes());
            lp.width = WindowManager.LayoutParams.MATCH_PARENT;
            lp.height = WindowManager.LayoutParams.WRAP_CONTENT ;
            dialog.show();
            dialog.getWindow().setAttributes(lp);
        });
    }

    private List<ActivityDTO> getStaticActivityData() {
        return new ArrayList<>(
                Arrays.asList(
                        new ActivityDTO(1L,"type","10:00 PM Nov 25, 2032", "done", "Reading picture books together"),
                        new ActivityDTO(1L,"type","10:00 PM Nov 25, 2032", "done", "Reading picture books together"),
                        new ActivityDTO(1L,"type","10:00 PM Nov 25, 2032", "undone", "Reading picture books together"),
                        new ActivityDTO(1L,"type","10:00 PM Nov 25, 2032", "done", "Reading picture books together"),
                        new ActivityDTO(1L,"type","10:00 PM Nov 25, 2032", "undone", "Reading picture books together"),
                        new ActivityDTO(1L,"type","10:00 PM Nov 25, 2032", "undone", "Reading picture books together"),
                        new ActivityDTO(1L,"type","10:00 PM Nov 25, 2032", "done", "Reading picture books together"),
                        new ActivityDTO(1L,"type","10:00 PM Nov 25, 2032", "undone", "Reading picture books together")
                )
        );
    }

    @Override
    public void onClick(ActivityDTO item) {
        System.out.println(item);
        Intent intent = new Intent(getActivity(), ActivityInfoActivity.class);
        intent.putExtra(GlobalKeys.ACTIVITY_KEY, item);
        startActivity(intent);
    }
}