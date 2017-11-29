package com.devmcryyu.smarthome;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import es.dmoral.toasty.Toasty;

/**
 * Created by 92075 on 2017/11/29.
 */

public class LoginActivity extends Activity {
    private TextView txt_ip;
    private Button btnConfirm;
    private Button btnCancel;
    private Handler handler;
    private ExecutorService mThreadPool;
    private Socket socket;
    private Context mContext;
    /**
     * 接收服务器消息 变量
     */
    // 输入流对象
    private InputStream is;
    // 输入流读取器对象
    private InputStreamReader isr;
    private BufferedReader br;
    // 接收服务器发送过来的消息
    private String response;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ip_fragment);
        mContext = this;
        btnConfirm = findViewById(R.id.btn_yes);
        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String ipAddressStr = txt_ip.getText().toString();
                handler = new Handler();
                if (isCorrect(ipAddressStr).isEmpty())
                    return;
                mThreadPool = Executors.newCachedThreadPool();                                              //实例化线程池
                mThreadPool.execute(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            // 创建Socket对象 & 指定服务端的IP 及 端口号
                            socket = new Socket(ipAddressStr, 8000);
                            if (socket.isConnected()) {
                                Intent intent = new Intent(mContext, MainActivity.class);
                                startActivity(intent);
                            }
                            // 判断客户端和服务器是否连接成功
                            System.out.println(socket.isConnected());
//                            Looper.prepare();
//                            Looper mLooper = Looper.myLooper();

                            while (socket.isConnected()) {
                                // 步骤1：创建输入流对象InputStream
                                is = socket.getInputStream();
                                // 步骤2：创建输入流读取器对象 并传入输入流对象
                                // 该对象作用：获取服务器返回的数据
                                isr = new InputStreamReader(is);
                                br = new BufferedReader(isr);
                                // 步骤3：通过输入流读取器对象 接收服务器发送过来的数据
                                response = br.readLine();
                                // 步骤4:通知主线程,将接收的消息显示到界面
                                System.out.println("0.0 " + response);
                                Message msg = Message.obtain();
                                msg.what = 0;
                                msg.obj = response;
                                System.out.println("QAQ " + msg.obj.toString());
                                handler.sendMessage(msg);
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
//                        Looper.loop();
                    }
                });

            }
//                Toasty.success(getActivity(), "连接成功 ", Toast.LENGTH_SHORT).show();
        });
        btnCancel = findViewById(R.id.btn_no);
        btnCancel.setOnClickListener(new View.OnClickListener()

        {
            @Override
            public void onClick(View view) {
                txt_ip.setText("");
            }
        });
        txt_ip = findViewById(R.id.text_ip);
    }


    public String isCorrect(String ipAddressStr) {
        if (ipAddressStr.equals("")) {
//                    Toast.makeText(btnConfirm.getContext(), "IP地址不能为空", Toast.LENGTH_SHORT).show();
            Toasty.error(btnConfirm.getContext(), "IP地址不能为空", Toast.LENGTH_SHORT).show();
            txt_ip.setText("");
            return "";
        }
        int dotNum = 0;
        for (int j = 0; j < ipAddressStr.length(); j++) {
            if (ipAddressStr.charAt(j) == '.') dotNum++;
            else if (ipAddressStr.charAt(j) > '9' || ipAddressStr.charAt(j) < '0') {
//                        Toast.makeText(btnConfirm.getContext(), "IP地址只能输入0-9和.", Toast.LENGTH_SHORT).show();
                Toasty.error(btnConfirm.getContext(), "IP地址只能输入0-9和.", Toast.LENGTH_SHORT).show();
                txt_ip.setText("");
                return "";
            }
        }
        if (dotNum != 3) {
//                    Toast.makeText(btnConfirm.getContext(), "IP地址需要4个值", Toast.LENGTH_SHORT).show();
            Toasty.error(btnConfirm.getContext(), "IP地址需要4个值", Toast.LENGTH_SHORT).show();
            txt_ip.setText("");
            return "";
        }
        String perStr = "";
        for (int j = 0; j < ipAddressStr.length(); j++) {
            if (ipAddressStr.charAt(j) != '.') {
                perStr += ipAddressStr.charAt(j);
            } else {
                if (Integer.parseInt(perStr) > 255) {
//                            Toast.makeText(btnConfirm.getContext(), "IP地址的每个值必须小于255", Toast.LENGTH_SHORT).show();
                    Toasty.error(btnConfirm.getContext(), "IP地址的每个值必须小于255", Toast.LENGTH_SHORT).show();
                    txt_ip.setText("");
                    return "";
                }
                perStr = "";
            }
        }
        return "isCorrect";
    }
}
