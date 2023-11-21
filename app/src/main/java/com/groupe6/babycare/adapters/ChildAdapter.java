package com.groupe6.babycare.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.groupe6.babycare.R;
import com.groupe6.babycare.dtos.ChildDTO;
import com.groupe6.babycare.listeners.ChildOnClickListener;

import java.util.List;

public class ChildAdapter extends BaseAdapter {

    private List<ChildDTO> children;
    private Context context;

    private ChildOnClickListener childOnClickListener;

    public ChildAdapter(Context context, List<ChildDTO> children, final ChildOnClickListener childOnClickListener) {
        this.children = children;
        this.context = context;
        this.childOnClickListener = childOnClickListener;
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
        ChildDTO child = children.get(position);
        childPic.setBackgroundResource(
                child.getGender().equalsIgnoreCase("boy")
                ? R.drawable.boy : R.drawable.girl
        );
        childName.setText(child.getName());

        childItem.setOnClickListener(v -> childOnClickListener.onChildClick(child));

        return convertView;
    }
}