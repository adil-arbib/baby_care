package com.groupe6.babycare.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.groupe6.babycare.R;
import com.groupe6.babycare.dtos.children.ChildDTO;
import com.groupe6.babycare.holders.GlobalObjectsHolder;
import com.groupe6.babycare.listeners.OnChildClickListener;
import com.groupe6.babycare.listeners.SelectChildListener;

import java.util.List;

public class ChildAdapter extends BaseAdapter {

    private List<ChildDTO> children;
    private Context context;

    private OnChildClickListener childOnClickListener;

    private View previousView = null;

    private SelectChildListener selectChildListener;

    public ChildAdapter(Context context, List<ChildDTO> children, final OnChildClickListener childOnClickListener) {
        this.children = children;
        this.context = context;
        this.childOnClickListener = childOnClickListener;
    }

    public ChildAdapter(Context context, List<ChildDTO> children, OnChildClickListener childOnClickListener, SelectChildListener selectChildListener) {
        this.children = children;
        this.context = context;
        this.childOnClickListener = childOnClickListener;
        this.selectChildListener = selectChildListener;
    }

    @Override
    public int getCount() {
        return children.size();
    }

    @Override
    public Object getItem(int position) {
        return children.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null) {
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_child, null);
        }
        ImageView childPic = convertView.findViewById(R.id.img_gender);
        TextView childName = convertView.findViewById(R.id.txt_child_name);
        LinearLayout childItem = convertView.findViewById(R.id.child_item);
        View dottedView = convertView.findViewById(R.id.dotted);
        ChildDTO child = children.get(position);
        childPic.setBackgroundResource(
                child.getGender().equalsIgnoreCase("MALE")
                ? R.drawable.boy : R.drawable.girl
        );
        childName.setText(child.getFirstName());

        childItem.setOnLongClickListener(v -> {
            System.out.println("Long press");
            selectChildListener.onSelectChild(child, dottedView, previousView);
            previousView = dottedView;
            return false;
        });

        childItem.setOnClickListener(v -> childOnClickListener.onChildClick(child));




        if(selectChildListener != null && GlobalObjectsHolder.getInstance().getCurrentChild().getId() == child.getId()){
            previousView = dottedView;
            dottedView.setVisibility(View.VISIBLE);
        }

        return convertView;
    }
}
