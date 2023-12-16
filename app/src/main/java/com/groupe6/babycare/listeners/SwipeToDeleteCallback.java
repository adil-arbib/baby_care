package com.groupe6.babycare.listeners;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;

public class SwipeToDeleteCallback extends ItemTouchHelper.SimpleCallback {

    private final ColorDrawable background;
    private final int backgroundColor;

    private RecyclerView.Adapter mAdapter;

    private final SwipeListener swipeListener;

    public SwipeToDeleteCallback(RecyclerView.Adapter adapter, final SwipeListener swipeListener) {
        super(0, ItemTouchHelper.LEFT );
        mAdapter = adapter;
        this.swipeListener = swipeListener;
        backgroundColor = Color.parseColor("#FF736F");
        background = new ColorDrawable(backgroundColor);
    }

    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        return false;
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        int position = viewHolder.getAdapterPosition();
        swipeListener.onItemSwiped(position);
    }

    @Override
    public void onChildDraw(Canvas canvas, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
        View itemView = viewHolder.itemView;

        background.setBounds(
                itemView.getRight() + (int) dX,
                itemView.getTop(),
                itemView.getRight(),
                itemView.getBottom()
        );
        background.draw(canvas);

        super.onChildDraw(canvas, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
    }
}

