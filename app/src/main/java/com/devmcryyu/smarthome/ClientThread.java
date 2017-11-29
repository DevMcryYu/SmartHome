package com.devmcryyu.smarthome;

import android.os.Handler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;

/**
 * Created by 92075 on 2017/11/22.
 */

public class ClientThread extends Thread {
    private Socket socket = null;
    private BufferedReader bufferedReader = null;
    private OutputStream outputStream = null;
    private boolean isConnected = false;
    private Handler handler = null;


    public ClientThread(String ipAddress, int port) throws IOException {
        try {
            this.handler = new Handler();
            socket = new Socket(ipAddress, port);
            isConnected = socket.isConnected();

        } catch (IOException e) {
            System.out.println("error");//出错
        }
    }

    public void run() {
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            outputStream = socket.getOutputStream();
            while (isConnected && !socket.isClosed()) {
                String receiveString = new String(bufferedReader.readLine());
                System.out.println(receiveString);//输出接收到的数据
//                handler.obtainMessage(1, new receiveMessage(receiveString));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public boolean isConnected() {
        return this.isConnected;
    }
}
