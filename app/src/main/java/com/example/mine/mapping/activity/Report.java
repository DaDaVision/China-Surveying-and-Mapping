package com.example.mine.mapping.activity;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.mine.mapping.R;

/**
 * Created by 魏健 on 2017/11/2.
 */

public class Report extends AppCompatActivity {

    TextView tv_title;
    Button bt_back;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.report);

        ActionBar actionbar=getSupportActionBar();
        if(actionbar!=null){
            actionbar.hide();
        }


        tv_title=findViewById(R.id.include6).findViewById(R.id.tv_tite);
        tv_title.setText("声源定位测试结果统计");
        bt_back=findViewById(R.id.include6).findViewById(R.id.bt_back);
        //导航栏返回键功能
        bt_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
