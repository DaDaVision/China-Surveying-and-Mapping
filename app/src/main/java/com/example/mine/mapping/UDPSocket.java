package com.example.mine.mapping;

import android.text.TextUtils;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

/**
 * Created by dell on 2018/5/15.
 */

public class UDPSocket {

    /**
     * 使用DatagramSocket进行基于UDP的Socket通信
     */
    private DatagramSocket socket = new DatagramSocket(1985);
    private String ipAddr;

    /**
     * 对方的ip地址在构造方法中传入
     * @param ipAddr
     * @throws SocketException
     */
    public UDPSocket(String ipAddr) throws SocketException {
        this.ipAddr = ipAddr;
    }
    public UDPSocket() throws SocketException {
        ipAddr="255.255.255.255";
    }

    public void sendData(String str) {
        try {
            InetAddress serverAddress = InetAddress.getByName(ipAddr);
            byte data[] = str.getBytes();
            DatagramPacket packet = new DatagramPacket(data, data.length ,serverAddress ,10025);
            socket.send(packet);
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 客户端接收服务端返回的数据(保留方法，方便后期扩展)
     */
    public String ReceiveData() {
        DatagramSocket socket;
        String result="";
        try {
            socket = new DatagramSocket(1985);
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

    /**
     * 接收消息的方法
     */
    public void ServerReceviedByUdp(){
        DatagramSocket socket;
        try {
            socket = new DatagramSocket(10025);
            while (true){
                byte data[] = new byte[4*1024];
                DatagramPacket packet = new DatagramPacket(data,data.length);
                socket.receive(packet);
                String result = new String(packet.getData(),packet.getOffset() ,packet.getLength());
                if(!TextUtils.isEmpty(result)){
//                    WordsEvent wordsEvent=new WordsEvent(result);
//                    EventBus.getDefault().post(wordsEvent);
//                    EventBus.getDefault().post(result);
                }
                System.out.println("收到信息为："+result);
            }

        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void disconnect(){
        socket.close();
        socket.disconnect();
    }

}
