package com.example.mine.mapping.utils.zgch.activity;

import android.os.Bundle;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.example.mine.mapping.R;
import com.example.mine.mapping.utils.http.IBindData;
import com.example.mine.mapping.utils.zgch.api.DeviceReqUtil;
import com.example.mine.mapping.utils.zgch.api.UserReqUtil;
import com.example.mine.mapping.utils.zgch.bean.Devices;
import com.example.mine.mapping.utils.zgch.bean.SimpleResult;
import com.example.mine.mapping.utils.zgch.bean.UserInfo;
import com.example.mine.mapping.utils.zgch.bean.Users;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void register(View view) {
        Toast.makeText(this, "register", Toast.LENGTH_SHORT).show();
        Users users = new Users();
        users.setUserName("test");
        users.setPwd("123");
        users.setEmail("123@qq.com");
        UserReqUtil.doRegister(MainActivity.this, iBindData, users,null);
    }

    public void login(View view) {
        Toast.makeText(this, "login", Toast.LENGTH_SHORT).show();
        Users users = new Users();
        users.setUserName("test");
        users.setPwd("123");
        users.setEmail("123@qq.com");
        UserReqUtil.doLogin(MainActivity.this, iBindData, users,null);
    }

    public void getUserParams(View view) {
        Toast.makeText(this, "getUserParams", Toast.LENGTH_SHORT).show();
        Users users = new Users();
        users.setUserName("test");
        users.setPwd("123");
        users.setEmail("123@qq.com");
        UserReqUtil.getUserInfoById(MainActivity.this, iBindData, 1,null);


    }

    public void addDevice(View view) {
        Toast.makeText(this, "addDevice", Toast.LENGTH_SHORT).show();
        Devices devices = new Devices();
        devices.setDeviceName("device");
        devices.setDeviceType("type");
        devices.setUserId(1);
        devices.setMac("123456789");
        DeviceReqUtil.doAddDevice(MainActivity.this, iBindData, devices,null);
    }

    public void updateDevice(View view) {
        Toast.makeText(this, "updateDevice", Toast.LENGTH_SHORT).show();
        Devices devices = new Devices();
        devices.setDeviceName("updatedevice");
        devices.setDeviceType("typexin");
        devices.setUserId(2);
        devices.setMac("987654321");
        DeviceReqUtil.doUpdateDevice(MainActivity.this, iBindData, devices,null);
    }

    public void controlDevice(View view) {
        Toast.makeText(this, "controlDevice", Toast.LENGTH_SHORT).show();
        //        CommonUtil.showToast("onClickText");
//        Devices devices = new Devices();
//        devices.setDeviceName("device");
//        devices.setDeviceType("type");
//        devices.setUserId(1);
//        devices.setMac("123456789");
//        DeviceReqUtil.doAcionControl(MainActivity.this, iBindData, devices,null);
    }



    //http请求的回调接口
    private IBindData iBindData=new IBindData() {
        Message m=new Message();
        @Override
        public void bindData(int tag, Object object) {
//            dismissLoadingDialog();//隐藏
            if(tag==2&&object!=null){//注册
                SimpleResult simpleResult=(SimpleResult) object;
                if(simpleResult.isResult()){
                    Toast.makeText(MainActivity.this, simpleResult.getReason(), Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(MainActivity.this, simpleResult.getReason(), Toast.LENGTH_SHORT).show();
                }

            }
            else if(tag==1&&object!=null) {//登陆
                SimpleResult simpleResult = (SimpleResult) object;
                if (simpleResult.isResult()) {
                    Toast.makeText(MainActivity.this, simpleResult.getReason(), Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, simpleResult.getReason(), Toast.LENGTH_SHORT).show();
                }
            }
            else if(tag==3&&object!=null) {//获取用户信息
//                Users users = (Users) object;
//                SimpleResult simpleResult = (SimpleResult) object;
                UserInfo userInfo = (UserInfo) object;
//                if (users.getUserName() != null) {
//                if (userInfo.) {
                    Toast.makeText(MainActivity.this, userInfo.toString(), Toast.LENGTH_SHORT).show();
//                } else {
//                    Toast.makeText(MainActivity.this, "unnull", Toast.LENGTH_SHORT).show();
//                }
            }

            else if(tag==4&&object!=null) {//添加设备
                SimpleResult simpleResult = (SimpleResult) object;
                if (simpleResult.isResult()) {
                    Toast.makeText(MainActivity.this, simpleResult.getReason(), Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, simpleResult.getReason(), Toast.LENGTH_SHORT).show();
                }
            }
            else if(tag==5&&object!=null) {//更新设备
                SimpleResult simpleResult = (SimpleResult) object;
                if (simpleResult.isResult()) {
                    Toast.makeText(MainActivity.this, simpleResult.getReason(), Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, simpleResult.getReason(), Toast.LENGTH_SHORT).show();
                }
            }
            else if(tag==6&&object!=null) {//控制设备
                SimpleResult simpleResult = (SimpleResult) object;
                if (simpleResult.isResult()) {
                    Toast.makeText(MainActivity.this, simpleResult.getReason(), Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, simpleResult.getReason(), Toast.LENGTH_SHORT).show();
                }
            }

            else if(tag==7&&object!=null) {//获取所有设备

            }

        }
    };

}
