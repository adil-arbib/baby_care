package com.groupe6.babycare.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.groupe6.babycare.R;
import com.groupe6.babycare.dtos.diaper.DiaperDTO;

import java.util.List;

public class DiaperAdapter extends RecyclerView.Adapter<DiaperAdapter.ViewHolder> {

    private List<DiaperDTO> diaperList;

    public DiaperAdapter(List<DiaperDTO> diaperList) {
        this.diaperList = diaperList;
    }

    @NonNull
    @Override
    public DiaperAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_diaper, parent, false);
        return new DiaperAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DiaperAdapter.ViewHolder holder, int position) {
        DiaperDTO diaperDTO = diaperList.get(position);
        holder.txtType.setText(diaperDTO.getDiaperType());

    }

    @Override
    public int getItemCount() {
        return diaperList.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView txtType, txtDate;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtType = itemView.findViewById(R.id.txt_type_value);
            txtType = itemView.findViewById(R.id.txt_date_value);
        }
    }

}
