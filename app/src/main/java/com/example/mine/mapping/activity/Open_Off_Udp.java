package com.example.mine.mapping.activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.example.mine.mapping.R;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

/**
 * Created by 魏健 on 2018/5/11.
 */

public class Open_Off_Udp extends Activity {
    String open="mac:$mac:cmd:turn_on";
    String off="mac:$mac:cmd:turn_off";
    String open_s="mac:$mac:deviceId:$deviceId:controlStatus:turn_on_success";
    String off_s="mac:$mac:deviceId:$deviceId:controlStatus:turn_on_fail";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_factory);
            new Thread(){
                @Override
                public void run() {
                    while(true){
                        String response=ReceiveServerSocketData();
                        if(!TextUtils.isEmpty(response)){
                            handler.sendEmptyMessage(1);
                            System.out.println("receive data");
                        }
                    }
                }
            }.start();
    }
Handler handler=new Handler(){
    @Override
    public void handleMessage(Message msg) {
        switch (msg.what){
            case 1:
                Toast.makeText(Open_Off_Udp.this,"receive data",Toast.LENGTH_SHORT).show();
                break;
        }
    }
};


    public void open(View v){
        new Thread(){
            @Override
            public void run() {
                sendDataWithUDPSocket(open);
            }
        }.start();
    }

    public void onClickFactoryTest(View v){
        new Thread(){
            @Override
            public void run() {
                sendDataWithUDPSocket(off);
            }
        }.start();
    }
    public void sendDataWithUDPSocket(String str) {
        try {
            DatagramSocket socket = new DatagramSocket();
            String ip=getIntent().getStringExtra("ip");
            InetAddress serverAddress = InetAddress.getByName(ip);
            byte data[] = str.getBytes();
            DatagramPacket packet = new DatagramPacket(data, data.length ,serverAddress ,1025);
            socket.send(packet);
            socket.close();
            System.out.println("start udp");
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String ReceiveServerSocketData() {
        DatagramSocket socket;
        String result="";
        try {
            socket = new DatagramSocket(1025);
            byte data[] = new byte[4 * 1024];
            DatagramPacket packet = new DatagramPacket(data, data.length);
            socket.receive(packet);
            result = new String(packet.getData(), packet.getOffset(),
                    packet.getLength());
            socket.close();
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}
