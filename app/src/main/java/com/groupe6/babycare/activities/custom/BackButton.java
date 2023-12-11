package com.groupe6.babycare.activities.custom;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.fragment.app.FragmentActivity;

public class BackButton extends AppCompatImageButton implements View.OnClickListener {


    private Activity activity;

    public BackButton(@NonNull Context context) {
        super(context);
        setOnClickListener(this);
    }

    public BackButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        setOnClickListener(this);

    }

    public BackButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setOnClickListener(this);

    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    @Override
    public void onClick(View v) {
        if(activity != null) {
            activity.finish();
        }
    }
}
