package com.example.mine.mapping.activity;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.mine.mapping.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 魏健 on 2017/11/2.
 */

public class Testing extends Fragment {

    Button bt_qidong,bt_start,bt_ljlysb;
    CheckBox cb_yinyin,cb_shijue,cb_guangyuan,cb_lanya,cb_5g,cb_wifi;
    RadioGroup rg1,rg2;
    Spinner spinner;
    Context context;
    List<CheckBox> checkBoxes=new ArrayList<CheckBox>();
    
    int BLUTOOTH_CONNECT=0;

    BluetoothAdapter mBluetoothAdapter;
    BluetoothChatService mChatService;

    String titlestr="";
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context=getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.testing,null);
        return view;
    }
    public void init(View v){
        bt_qidong=(Button)v.findViewById(R.id.bt_qidong);
        bt_start=(Button) v.findViewById(R.id.bt_st);
        bt_ljlysb=(Button)v.findViewById(R.id.bt_ljlysb);


        cb_yinyin=v.findViewById(R.id.cb_yinpin);
        cb_shijue=v.findViewById(R.id.cb_shijue);
        cb_guangyuan=v.findViewById(R.id.cb_guangyuan);
        cb_lanya=v.findViewById(R.id.cb_lanya);
        cb_5g=v.findViewById(R.id.cb_5g);
        cb_wifi=v.findViewById(R.id.cb_wifi);

        checkBoxes.add(cb_5g);
        checkBoxes.add(cb_guangyuan);
        checkBoxes.add(cb_lanya);
        checkBoxes.add(cb_shijue);
        checkBoxes.add(cb_wifi);
        checkBoxes.add(cb_yinyin);


        rg1=v.findViewById(R.id.rg1);
        rg2=v.findViewById(R.id.rg2);
        spinner=v.findViewById(R.id.spinner);
    }


    /*public void kaishicehui(View view) {
        Toast.makeText(context,"bt",Toast.LENGTH_SHORT).show();
    }*/

    private final Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            FragmentActivity activity = getActivity();
            switch (msg.what) {
                case Constants.MESSAGE_STATE_CHANGE:
                    switch (msg.arg1) {
                        case BluetoothChatService.STATE_CONNECTED:
//                            setStatus(getString(R.string.title_connected_to, mConnectedDeviceName));
//                            mConversationArrayAdapter.clear();
                            break;
                        case BluetoothChatService.STATE_CONNECTING:
//                            setStatus(R.string.title_connecting);
                            break;
                        case BluetoothChatService.STATE_LISTEN:
                        case BluetoothChatService.STATE_NONE:
//                            setStatus(R.string.title_not_connected);
                            break;
                    }
                    break;
                case Constants.MESSAGE_WRITE:
                    byte[] writeBuf = (byte[]) msg.obj;
                    // construct a string from the buffer
                    String writeMessage = new String(writeBuf);
//                    mConversationArrayAdapter.add("Me:  " + writeMessage);
                    break;
                case Constants.MESSAGE_READ:
                    byte[] readBuf = (byte[]) msg.obj;
                    // construct a string from the valid bytes in the buffer
                    String readMessage = new String(readBuf, 0, msg.arg1);
//                    mConversationArrayAdapter.add(mConnectedDeviceName + ":  " + readMessage);
                    break;
                case Constants.MESSAGE_DEVICE_NAME:
                    // save the connected device's name
//                    mConnectedDeviceName = msg.getData().getString(Constants.DEVICE_NAME);
                    if (null != activity) {
//                        Toast.makeText(activity, "Connected to "
//                                + mConnectedDeviceName, Toast.LENGTH_SHORT).show();
                    }
                    break;
                case Constants.MESSAGE_TOAST:
                    if (null != activity) {
                        Toast.makeText(activity, msg.getData().getString(Constants.TOAST),
                                Toast.LENGTH_SHORT).show();
                    }
                    break;
            }
        }
    };

    public void onActivityResult(int requestCode, int resultCode, Intent data){
        switch (requestCode){
            case 0:
                if(resultCode== Activity.RESULT_CANCELED){
                    connectDevice(data, true);
                }
                break;
        }
    }
    //利用onActivityResult返回的信息连接响应的蓝牙设备
    private void connectDevice(Intent data, boolean secure) {
        // Get the device MAC address
        String address = data.getExtras()
                .getString("device_address");
        // Get the BluetoothDevice object
        BluetoothDevice device = mBluetoothAdapter.getRemoteDevice(address);
        // Attempt to connect to the device
        mChatService.connect(device, secure);
    }
    //通过蓝牙对相应的设备发送蓝牙消息
    private void sendMessage(String message) {
        // Check that we're actually connected before trying anything
        if (mChatService.getState() != BluetoothChatService.STATE_CONNECTED) {
            Toast.makeText(getActivity(), "you are not connect to a device", Toast.LENGTH_SHORT).show();
            return;
        }

        // Check that there's actually something to send
        if (message.length() > 0) {
            // Get the message bytes and tell the BluetoothChatService to write
            byte[] send = message.getBytes();
            mChatService.write(send);

            // Reset out string buffer to zero and clear the edit text field
//            mOutStringBuffer.setLength(0);
//            mOutEditText.setText(mOutStringBuffer);
        }
    }
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init(view);
        mBluetoothAdapter=BluetoothAdapter.getDefaultAdapter();
        mChatService=new BluetoothChatService(getActivity(),mHandler);
//        bt_start.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(context,"bt_start",Toast.LENGTH_SHORT).show();
//                startActivity(new Intent(getActivity(),MapBox.class));
//            }
//        });


        bt_qidong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context,"bt_qidong",Toast.LENGTH_SHORT).show();
                SimpleDateFormat    sDateFormat    =   new SimpleDateFormat("yyyy-MM-dd    hh:mm:ss");
                String    date    =    sDateFormat.format(new    java.util.Date());
                //向全站仪发送当前系统时间
                sendMessage(date);
            }
        });

        //spinner处理
        final List<String> splist=new ArrayList<String>();
        splist.add("111");
        splist.add("222");
        splist.add("333");
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(getActivity(),android.R.layout.simple_spinner_item,splist);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//                Toast.makeText(context,splist.get(i),Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        rg1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

            }
        });

        bt_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                titlestr="";

                //判断多选框哪个被选中
                for(CheckBox checkbox:checkBoxes){
                    if(checkbox.isChecked()){
                        titlestr=titlestr+" "+checkbox.getText().toString();
                    }
                }
                //第一个单选框
                int whichrb1=rg1.getCheckedRadioButtonId();
                switch (whichrb1){
                    case R.id.rb_jingtai:
                        titlestr=titlestr+" "+"静态";
                        break;
                    case R.id.rb_dongtai:
                        titlestr=titlestr+" "+"动态";
                        break;
                }

                //第二个单选框
                int whichrb2=rg2.getCheckedRadioButtonId();
                switch (whichrb2){
                    case R.id.rb_dianci:
                        titlestr=titlestr+" "+"电磁";
                        break;
                    case R.id.rb_renwei:
                        titlestr=titlestr+" "+"人为";
                        break;
                    case R.id.rb_zuaiwu:
                        titlestr=titlestr+" "+"阻碍物";
                        break;
                }

                Intent intent=new Intent(context,MapBox.class);
                intent.putExtra("title",titlestr);
                startActivity(intent);
            }
        });

        bt_ljlysb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(context,BuleToothDeviceListActivity.class),BLUTOOTH_CONNECT);
            }
        });

    }


}
