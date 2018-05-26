package com.example.mine.mapping.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.mine.mapping.MyPagerAdapter;
import com.example.mine.mapping.R;
import com.example.mine.mapping.db_table.HistoryTable;

import org.litepal.tablemanager.Connector;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 魏健 on 2017/11/2.
 */

public class MainMenu extends AppCompatActivity{
    BottomNavigationView ng;
    ViewPager vp;
    TextView tv;
    Button bt;

    public void insertdata(){
        HistoryTable ht=new HistoryTable();
        ht.setUserId("JianWei");
        ht.setCategory("static");
        ht.setPhone("18071427576");
        ht.setDiscription("nothing");
        ht.setTime("2018/3/16");
        ht.save();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainmenu);

        tv=(TextView) findViewById(R.id.include2).findViewById(R.id.tv_tite);
        bt=findViewById(R.id.include2).findViewById(R.id.bt_back);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        Connector.getDatabase();
        //初始化数据
        insertdata();

        ActionBar actionbar=getSupportActionBar();
        if(actionbar!=null){
            actionbar.hide();
        }
        ng=(BottomNavigationView) findViewById(R.id.navigation);
        vp=(ViewPager) findViewById(R.id.viewpager);

        List<Fragment> viewlist=new ArrayList<Fragment>();

        viewlist.add(new Testing());
        viewlist.add(new History());
        viewlist.add(new Settings());

        MyPagerAdapter adapter=new MyPagerAdapter(getSupportFragmentManager(),viewlist);
        vp.setAdapter(adapter);
        ng.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.menu_ceshi:
                        vp.setCurrentItem(0);
                        tv.setText("测试");
                        break;
                    case R.id.menu_lishi:
                        vp.setCurrentItem(1);
                        tv.setText("历史");
                        break;
                    case R.id.menu_shezhi:
                        vp.setCurrentItem(2);
                        tv.setText("设置");
                        break;
                }
                return false;
            }
        });

        vp.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                ng.getMenu().getItem(position).setChecked(true);
                tv.setText(ng.getMenu().getItem(position).getTitle());
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
}
