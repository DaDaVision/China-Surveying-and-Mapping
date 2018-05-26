package com.example.mine.mapping.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
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
import android.widget.Toast;

import com.example.mine.mapping.R;
import com.example.mine.mapping.UDPSocket;
import com.example.mine.mapping.db_table.Devices;
import com.example.mine.mapping.utils.http.IBindData;
import com.example.mine.mapping.utils.zgch.api.DeviceReqUtil;

import org.litepal.crud.DataSupport;

import java.net.SocketException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 魏健 on 2018/4/11.
 */

public class DeviceList extends AppCompatActivity {
    TextView tv_title,dialog_title,add_dialog_tv1,add_dialog_tv2;
    ListView lv;
    Button bt,bt_po,bt_ne,bt_back;
    EditText et_name,et_discribe;
    View v;
    int userid;
    UDPSocket socket;
    //广播地址
    String Adress1="255.255.255.255";
    static String ServIP="192.168.17.13";
    static int ServPort=10000;
    static String wifi_name="";
    static String wifi_ps="";
    static String configinfo="SMART_CONFIG:"+wifi_name+" "+wifi_ps+" " +ServIP+" "+ServPort+" ";
    String wifiinfo="";


    ProgressDialog progressDialog = null;
    Message msg=new Message();

    boolean ifserversended =false;//服务器消息是否发送成功
    boolean ifwifiinfosended=false;//wifi信息是否发送成功
    String configserverS=configinfo+"Config_Success";
    String configwifiS="";
    String configserverF="Config_Failed";
    SharedPreferences sp;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.device_list);
        try {
            socket=new UDPSocket();
        } catch (SocketException e) {
            e.printStackTrace();
        }
        new Thread(){
            @Override
            public void run() {
                while(true){
                    if(socket==null){
                        try {
                            socket=new UDPSocket();
                        } catch (SocketException e) {
                            e.printStackTrace();
                        }
                    }
                    String response=socket.ReceiveData();
                    if(!TextUtils.isEmpty(response)){
                        //收到来自服务器的消息,表示发送成功，取消对话框显示
                        if(!response.equals(configserverF)){
                            Message msg=new Message();
                            msg.what=1;
                            handler.sendMessage(msg);
                            msg.what=5;
                            handler.sendMessage(msg);
                            String temp[]=response.split(" ");
                            String ip=temp[1];
                            String mac=temp[2];

                            //测试向服务器发送设备信息是否能成功
                            com.example.mine.mapping.utils.zgch.bean.Devices device=new com.example.mine.mapping.utils.zgch.bean.Devices();
                            device.setUserId(userid);
                            device.setIP(ip);
                            device.setMac(mac);
                            DeviceReqUtil.doAddDevice(DeviceList.this,iBindData,device,handler);
                        }
//
//                            ifserversended =true;
//                        if(response.equals(configserverS)){
//                            //通过udp广播发送目标服务器ip和端口号
//                            Message msg=new Message();
//                            msg.what=1;
//                            handler.sendMessage(msg);
//
//                            ifserversended =true;
//                            try {
//                                //目前不重新开线程发送，可能造成接收线程堵塞，有消息从服务器过来时可能因为在发送消息而收不到
//                                int frequency=0;
//                                //显示发送WiFi信息的对话框
//                                msg.what=4;
//                                handler.sendMessage(msg);
//                                while(!ifwifiinfosended&&frequency<15){
//                                    socket.sendData(wifiinfo);
//                                    frequency=frequency+1;
//                                    Thread.sleep(2000);
//                                    //显示已发送
//                                    msg.what=2;
//                                    handler.sendMessage(msg);
//                                }
//                                //配置失败，取消对话框
//                                msg.what=3;
//                                handler.sendMessage(msg);
//                            } catch (Exception e) {
//                                e.printStackTrace();
//                            }
//                        }else if(!response.equals(configserverF)){
//                            ifwifiinfosended=true;//wifi信息发送成功
//                            String temp[]=response.split(" ");
//                            String ip=temp[temp.length-1];
//                            try {
//                                socket=new UDPSocket(ip);
//                                socket.sendData("Report_Yourself");
//                                socket=null;
//                            } catch (SocketException e) {
//                                e.printStackTrace();
//                            }
//
//
//                        }
                        System.out.println("receive data");
                    }
                }
            }
        }.start();

        sp=getSharedPreferences("userInfo", 0);
        userid=sp.getInt("userid",0);
        //隐藏系统自带的标题栏
        ActionBar actionbar=getSupportActionBar();
        if(actionbar!=null){
            actionbar.hide();
        }
        v= LayoutInflater.from(DeviceList.this).inflate(R.layout.addrp_dialog,null);
        initWidget();
        initEvent();
        tv_title.setText("设备列表");

        //为listview注册上下文菜单
        registerForContextMenu(lv);
        setListViewData();
    }

    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 1:
                    if(progressDialog!=null){
                        progressDialog.dismiss();
                        Toast.makeText(DeviceList.this,"发送成功",Toast.LENGTH_SHORT).show();
                    }
                    break;
                case 2:
                    Toast.makeText(DeviceList.this,"已发送",Toast.LENGTH_SHORT).show();
                    break;
                case 3:
                    if(progressDialog!=null){
                        progressDialog.dismiss();
                        tv_title.setText("设备列表");
                        Toast.makeText(DeviceList.this,"配置失败",Toast.LENGTH_SHORT).show();
                    }
                    break;
                case 4:
                    progressDialog=ProgressDialog.show(DeviceList.this,"","Wifi信息正在发送，请稍后...");
                    break;
                case 5:
                    setListViewData();
                    break;
            }
        }
    };
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.rp_lv_contextmenu,menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.rp_lv_contextmenu_delete:
                DataSupport.delete(Devices.class,which+1);
                setListViewData();
                break;
        }
        return true;
    }

    public void setListViewData(){
        Toast.makeText(DeviceList.this,"userid:"+userid,Toast.LENGTH_SHORT).show();
        DeviceReqUtil.doGetAllDeviceById(DeviceList.this, iBindData, userid,null);
    }

    private String getConnectWifiSsid(){
        WifiManager wifiManager = (WifiManager) DeviceList.this.getApplicationContext().getSystemService(WIFI_SERVICE);
        if(wifiManager.getWifiState()==WifiManager.WIFI_STATE_ENABLED){
            WifiInfo wifiInfo = wifiManager.getConnectionInfo();
            return wifiInfo.getSSID();
        }
        else{
            return "";
        }
    }
    int which;
    AlertDialog.Builder builder;
    AlertDialog dialog;
    public  void initEvent(){

        //listview长按事件
        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                which=i;
                return false;
            }
        });
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent=new Intent(DeviceList.this,Open_Off_Udp.class);
                intent.putExtra("ip",querylist.get(i).getIP());
                Toast.makeText(DeviceList.this,querylist.get(i).getIP(),Toast.LENGTH_SHORT).show();
                startActivity(intent);
            }
        });
        //导航栏返回按钮
        bt_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        final int[] f = {0};
        final String[] name1 = {""};
        //添加设备
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                builder=new AlertDialog.Builder(DeviceList.this);
                builder.setView(v);
                if(f[0]==0){
                    dialog_title.setText("添加设备");
                    add_dialog_tv1.setText("wifi名称");
                    add_dialog_tv2.setText("wifi密码");


                    //wifi名称
                    String name =getConnectWifiSsid();
                    String temp[]=name.split("");

                    for(int i=2;i<temp.length-1;i++){
                        name1[0] = name1[0] +temp[i];
                    }
                    System.out.println(name1[0]);
                    et_name.setText(name1[0]);

                    wifi_name=name1[0];

                    dialog =builder.create();
                    dialog.show();
                }else{
                    dialog.show();
                }
                f[0] =1;
            }
        });
        bt_po.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //wifi密码
                String discribe=et_discribe.getText().toString().trim();

                wifi_ps=discribe;
                wifiinfo="SET_SSID_PASSWORD:"+ name1[0] +" "+discribe+" ";

                try {
                    tv_title.setText("正在发送...");

                    new Thread(){
                        @Override
                        public void run() {
                            int frequency=0;
                            while (!ifserversended &&frequency<15) {
                                socket.sendData(configinfo);
                                frequency=frequency+1;
                                try {
                                    Thread.sleep(2000);
                                    Message msg=new Message();
                                    msg.what=2;
                                    handler.sendMessage(msg);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }
                            //已发送15次，配置失败
                            Message msg=new Message();
                            msg.what=3;
                            handler.sendMessage(msg);
                        }
                    }.start();
                    progressDialog=ProgressDialog.show(DeviceList.this,"","配置信息正在发送，请稍后...");
                    progressDialog.setCancelable(true);
//                    handler.postDelayed(new Runnable()
//                    {
//                        public void run()
//                        {
//                            progressDialog.dismiss();
//                        }
//                    }, 2000);
//                    tv_title.setText("设备列表");
                } catch (Exception e) {
                    System.out.println("发送失败（exception）");
                    e.printStackTrace();
                }
                //从服务器获取数据填充到listview
//                setListViewData();
                dialog.dismiss();

            }
        });
        bt_ne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dialog.dismiss();
            }
        });
    }
    public void initWidget(){
        tv_title=(TextView) findViewById(R.id.device_list_include).findViewById(R.id.tv_tite);
        bt_back=findViewById(R.id.device_list_include).findViewById(R.id.bt_back);
        lv=findViewById(R.id.devicce_listview);
        bt=findViewById(R.id.dl_bt_tjsb);
        dialog_title=v.findViewById(R.id.add_dialog_title);
        add_dialog_tv1=v.findViewById(R.id.add_dialog_tv1);
        add_dialog_tv2=v.findViewById(R.id.add_dialog_tv2);
        bt_po=v.findViewById(R.id.bt_addrp_dia_po);
        bt_ne=v.findViewById(R.id.bt_addrp_dia_ne);
        et_name=v.findViewById(R.id.addrp_dia_et_name);
        et_discribe=v.findViewById(R.id.addrp_dia_et_discribe);
    }
    List<com.example.mine.mapping.utils.zgch.bean.Devices> querylist;
    //http请求的回调接口
    private IBindData iBindData=new IBindData() {
        Message m=new Message();
        @Override
        public void bindData(int tag, Object object) {
            com.example.mine.mapping.utils.zgch.bean.DeviceList devicelist=(com.example.mine.mapping.utils.zgch.bean.DeviceList)object;
            if(devicelist!=null){
                querylist=devicelist.getList();
            }
            else{
                querylist=new ArrayList<>();
            }
            List<Map<String,String>> datalist=new ArrayList<Map<String,String>>();
            Map<String,String> map;
            for(com.example.mine.mapping.utils.zgch.bean.Devices device:querylist){
                map=new HashMap<String,String>();
                map.put("name",device.getIP());
                map.put("discribe",device.getMac());
                datalist.add(map);
            }
            lv.setAdapter(new SimpleAdapter(DeviceList.this,datalist,R.layout.dv_listview_item,new String[]{"name","discribe"},new int[]{R.id.dv_lv_item_ip,R.id.dv_lv_item_mac}));
        }
    };
}

