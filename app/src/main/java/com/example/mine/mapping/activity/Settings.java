package com.example.mine.mapping.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mine.mapping.R;

/**
 * Created by 魏健 on 2018/3/15.
 */

public class Settings extends Fragment{

    TextView st_tv_ckd,st_tv_sb;
    Context context;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context=getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.setting,null);
        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initWidget(view);
        initEvent();
    }
    public void initEvent(){
        st_tv_ckd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(context,PointList.class));
            }
        });
        st_tv_sb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(context,DeviceList.class));
            }
        });
    }
    public void initWidget(View v){
        st_tv_ckd=v.findViewById(R.id.st_tv_ckd);
        st_tv_sb=v.findViewById(R.id.st_tv_sb);
    }
}
