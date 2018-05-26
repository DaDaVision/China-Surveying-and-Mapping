package com.example.mine.mapping.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.example.mine.mapping.R;
import com.example.mine.mapping.db_table.ReferencePoint;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 魏健 on 2018/4/11.
 */

public class PointList extends AppCompatActivity {
    TextView tv_title;
    ListView lv;
    Button bt,bt_po,bt_ne,bt_back;
    EditText et_name,et_discribe;
    View v;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.point_list);

        //隐藏系统自带的标题栏
        ActionBar actionbar=getSupportActionBar();
        if(actionbar!=null){
            actionbar.hide();
        }
        v= LayoutInflater.from(PointList.this).inflate(R.layout.addrp_dialog,null);
        initWidget();
        initEvent();
        tv_title.setText("参考点列表");

        //为listview注册上下文菜单
        registerForContextMenu(lv);
        setListViewData();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.rp_lv_contextmenu,menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.rp_lv_contextmenu_delete:
                DataSupport.delete(ReferencePoint.class,which+1);
                setListViewData();
                break;
        }
        return true;
    }

    public void setListViewData(){
        List<ReferencePoint> querylist=DataSupport.findAll(ReferencePoint.class);

        List<Map<String,String>> datalist=new ArrayList<Map<String,String>>();
        Map<String,String> map;
        for(ReferencePoint rp:querylist){
            map=new HashMap<String,String>();
            map.put("name",rp.getPointName());
            map.put("discribe",rp.getPointDescription());
            datalist.add(map);
        }
        lv.setAdapter(new SimpleAdapter(this,datalist,R.layout.pl_listview_item,new String[]{"name","discribe"},new int[]{R.id.pl_lv_item_name,R.id.pl_lv_item_discribe}));
    }
    int which;
    public  void initEvent(){
        final AlertDialog.Builder builder=new AlertDialog.Builder(PointList.this);
        final AlertDialog[] dialog = new AlertDialog[1];
        //listview长按事件
        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                which=i;
                return false;
            }
        });
        //导航栏返回按钮
        bt_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        //添加参考点
        final int[] f = {0};
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(f[0] ==0){
                    builder.setView(v);
                    dialog[0] =builder.create();
                    dialog[0].show();
                }
                else{
                    dialog[0].show();
                }
                f[0] =1;
            }
        });
        bt_po.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name=et_name.getText().toString().trim();
                String discribe=et_discribe.getText().toString().trim();
                ReferencePoint rp=new ReferencePoint();
                rp.setPointName(name);
                rp.setPointDescription(discribe);
                rp.save();
                setListViewData();
                dialog[0].dismiss();

            }
        });
        bt_ne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog[0].dismiss();
            }
        });
    }
    public void initWidget(){
        tv_title=(TextView) findViewById(R.id.include3).findViewById(R.id.tv_tite);
        bt_back=findViewById(R.id.include3).findViewById(R.id.bt_back);
        lv=findViewById(R.id.point_listview);
        bt=findViewById(R.id.pl_bt_tjckd);
        bt_po=v.findViewById(R.id.bt_addrp_dia_po);
        bt_ne=v.findViewById(R.id.bt_addrp_dia_ne);
        et_name=v.findViewById(R.id.addrp_dia_et_name);
        et_discribe=v.findViewById(R.id.addrp_dia_et_discribe);
    }
}

