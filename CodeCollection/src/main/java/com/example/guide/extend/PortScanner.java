package com.example.guide.extend;

import java.io.IOException;
import java.net.Socket;

//端口扫描工具
public class PortScanner {

    public static boolean scan(int port ,int timeOut){
        boolean flag =false;
        Socket socket =null;
        try {
            socket = new Socket("localhost",port);
            socket.setSoTimeout(timeOut);
            flag =true;
        }catch (IOException e){
//            e.printStackTrace();
        }
        finally {
            if (socket!=null){
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return flag;
    }

    public static void main(String[] args) throws IOException {

//        PortScanner portScan=new PortScanner();
        for (int i = 0; i <10000; i++) {
            if (scan(i,5000)){
                System.out.println("端口被占用："+i);
            }
        }
    }
}
