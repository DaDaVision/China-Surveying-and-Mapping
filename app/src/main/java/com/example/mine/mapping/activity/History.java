package com.example.mine.mapping.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.example.mine.mapping.R;
import com.example.mine.mapping.db_table.HistoryTable;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 魏健 on 2018/3/15.
 */

public class History extends Fragment{
    ListView lv;
    TextView tv_leibie,tv_shijian;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.history,null);
        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        lv=view.findViewById(R.id.listview);
        setListviewData();

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                startActivity(new Intent(getActivity(),MapBox.class));
            }
        });
    }
    public void setListviewData(){
        List<Map<String,String>> list=new ArrayList<Map<String, String>>();
        List<HistoryTable> querylist=DataSupport.findAll(HistoryTable.class);
        for(HistoryTable h:querylist){
            Map<String,String> map=new HashMap<String,String>();
            map.put("category",h.getCategory());
            map.put("time",h.getTime());
            list.add(map);
        }
        lv.setAdapter(new SimpleAdapter(getActivity(),list,R.layout.list_item,new String[]{"category","time"},new int[]{R.id.tv_leibie,R.id.tv_shijian}));
    }
}
