package com.groupe6.babycare.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.groupe6.babycare.R;
import com.groupe6.babycare.dtos.feeding.FoodDTO;
import com.groupe6.babycare.dtos.notes.NoteDTO;
import com.groupe6.babycare.listeners.OnItemClickListener;
import com.groupe6.babycare.listeners.OnItemDeleteListener;

import java.util.List;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.ViewHolder> {

    private List<NoteDTO> notes;

    private OnItemClickListener<NoteDTO> itemClickListener;

    private OnItemDeleteListener<NoteDTO> itemDeleteListener;

    public NoteAdapter(List<NoteDTO> notes, OnItemClickListener<NoteDTO> itemClickListener, OnItemDeleteListener<NoteDTO> itemDeleteListener) {
        this.notes = notes;
        this.itemClickListener = itemClickListener;
        this.itemDeleteListener = itemDeleteListener;
    }

    @NonNull
    @Override
    public NoteAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_note, parent, false);
        return new NoteAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteAdapter.ViewHolder holder, int position) {
        NoteDTO note = notes.get(position);
        holder.title.setText(note.getTitle());
        holder.content.setText(note.getContent());
        holder.date.setText(note.getDate());
        holder.container.setOnClickListener(v -> itemClickListener.onClick(note));
        holder.icDelete.setOnClickListener(v -> itemDeleteListener.onDeleteRequest(note));
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        CardView container;
        TextView title, content, date;
        ImageView icDelete;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            container = itemView.findViewById(R.id.notes_container);
            title = itemView.findViewById(R.id.txt_title);
            content = itemView.findViewById(R.id.txt_content);
            date = itemView.findViewById(R.id.txt_date);
            icDelete = itemView.findViewById(R.id.ic_delete);

        }
    }
}
