package com.groupe6.babycare.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.groupe6.babycare.R;
import com.groupe6.babycare.dtos.tips.TipsDTO;
import com.groupe6.babycare.listeners.OnItemClickListener;

import java.util.List;

public class TipsAdapter extends RecyclerView.Adapter<TipsAdapter.ViewHolder> {

    private List<TipsDTO> tipsDTOS;

    private OnItemClickListener<TipsDTO> itemClickListener;

    public TipsAdapter(List<TipsDTO> tips, OnItemClickListener<TipsDTO> itemClickListener) {
        this.tipsDTOS = tips;
        this.itemClickListener = itemClickListener;
    }

    @NonNull
    @Override
    public TipsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_tips, parent, false);
        return new TipsAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TipsAdapter.ViewHolder holder, int position) {
        TipsDTO tips = tipsDTOS.get(position);
        holder.category.setText(tips.getCategory());
        holder.description.setText(tips.getDescription());
        holder.container.setOnClickListener(v -> itemClickListener.onClick(tips));
    }

    @Override
    public int getItemCount() {
        return tipsDTOS.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        CardView container;
        TextView description, category;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            container = itemView.findViewById(R.id.tips_container);
            description = itemView.findViewById(R.id.txt_description);
            category = itemView.findViewById(R.id.txt_Category);
        }
    }

    public List<TipsDTO> getTips(){return tipsDTOS;}
}
