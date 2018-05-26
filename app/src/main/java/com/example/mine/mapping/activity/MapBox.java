package com.example.mine.mapping.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mine.mapping.R;

import java.io.FileOutputStream;

/**
 * Created by 魏健 on 2018/4/11.
 */

public class MapBox extends AppCompatActivity {
    Button bt_daochu,bt_qxt,bt_csjg,bt_tbsj,bt_back;
    TextView tv_title;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mapbox);
        tv_title=findViewById(R.id.include4).findViewById(R.id.tv_tite);
        bt_back=findViewById(R.id.include4).findViewById(R.id.bt_back);
        String title=getIntent().getStringExtra("title");
        tv_title.setText(title);
        ActionBar actionbar=getSupportActionBar();
        if(actionbar!=null){
            actionbar.hide();
        }

        initWidget();
        initEvent();
    }

    public void write(String msg){
        try {
            FileOutputStream fos=openFileOutput("Test_data.txt",MODE_APPEND);
            fos.write(msg.getBytes());
            fos.close();
            Toast.makeText(this,"writesuucess",Toast.LENGTH_SHORT).show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //四个按钮的点击事件
    public void  initEvent(){
        //导航栏返回键功能
        bt_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        bt_daochu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                write("daochu");
            }
        });

        bt_qxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MapBox.this,Picture.class));
            }
        });

        bt_csjg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MapBox.this,Report.class));
            }
        });

        bt_tbsj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
    public void initWidget(){
        bt_daochu=(Button) findViewById(R.id.mb_bt_daochu);
        bt_qxt=(Button) findViewById(R.id.mb_bt_qxt);
        bt_csjg=(Button) findViewById(R.id.mb_bt_csjg);
        bt_tbsj=(Button) findViewById(R.id.mb_bt_tbsj);
    }
}
