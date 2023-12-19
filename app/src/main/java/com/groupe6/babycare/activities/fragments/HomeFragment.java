package com.groupe6.babycare.activities.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.groupe6.babycare.R;
import com.groupe6.babycare.adapters.ActivityLogAdapter;
import com.groupe6.babycare.databinding.FragmentHomeBinding;
import com.groupe6.babycare.dtos.activities.SimpleActivityDTO;
import com.groupe6.babycare.dtos.error.ErrorDTO;
import com.groupe6.babycare.dtos.reminder.ReminderDTO;
import com.groupe6.babycare.dtos.todayInfo.TodayInfo;
import com.groupe6.babycare.holders.GlobalObjectsHolder;
import com.groupe6.babycare.listeners.OnItemClickListener;
import com.groupe6.babycare.listeners.ResponseListener;
import com.groupe6.babycare.repositories.implementations.ChildApiImpl;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class HomeFragment extends Fragment implements OnItemClickListener<ReminderDTO> {

    private FragmentHomeBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        getActivity().setTitle(R.string.home);

        ChildApiImpl childApi = ChildApiImpl.getInstance(getContext());
        childApi.todayInfo(GlobalObjectsHolder.getInstance().getCurrentChild().getId(),
                new ResponseListener<TodayInfo>() {
                    @Override
                    public void onSuccess(TodayInfo response) {
                        double sum = response.getRemindersCompleted() + response.getRemindersNotCompleted();
                        double percentage = (double) (response.getRemindersCompleted() / sum ) *100;
                        DecimalFormat decimalFormat = new DecimalFormat("##.##");
                        Log.e("completed", response.getRemindersCompleted()+"");
                        Log.e("nor completed", response.getRemindersNotCompleted()+"");
                        Log.e("percentage", percentage+"");
                        binding.txtPercentage.setText(decimalFormat.format(percentage) + " %");
                        binding.txtRemainingActivities.setText(response.getRemindersCompleted()+" activities have been completed, and "+response.getRemindersNotCompleted()+" are still remaining");
                        binding.txtTip.setText(response.getTip());
                        binding.progressBar.setProgress((int)percentage);
                        ActivityLogAdapter adapter =
                                new ActivityLogAdapter( response.getTodaysReminders(),HomeFragment.this);
                        binding.recyclerActivities.setAdapter(adapter);
                        binding.recyclerActivities.setLayoutManager(new LinearLayoutManager(getContext()));
                    }

                    @Override
                    public void onError(ErrorDTO error) {

                    }
                });



    }




    @Override
    public void onClick(ReminderDTO item) {
        System.out.println(item);
    }
}