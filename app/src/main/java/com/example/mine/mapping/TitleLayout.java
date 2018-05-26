package com.example.mine.mapping;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by 魏健 on 2018/3/16.
 */

public class TitleLayout extends LinearLayout {
    Button bt_back;
    TextView tv_title;
    public TitleLayout(final Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        View v=LayoutInflater.from(context).inflate(R.layout.title_layout,this);
        bt_back=v.findViewById(R.id.bt_back);
        tv_title=v.findViewById(R.id.tv_tite);
        bt_back.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context,"button",Toast.LENGTH_SHORT).show();
            }
        });


    }
}
