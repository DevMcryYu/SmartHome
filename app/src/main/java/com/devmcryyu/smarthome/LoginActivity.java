package com.devmcryyu.smarthome;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
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
    private long firstTime = 0;
    private BufferedReader bufferedReader;
    private receiveMessage receiveMsg;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ip_fragment);
        handler = new Handler(Looper.getMainLooper());
        mThreadPool = Executors.newCachedThreadPool();                                              //实例化线程池
        mContext = this;
        btnConfirm = findViewById(R.id.btn_yes);
        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String ipAddressStr = txt_ip.getText().toString();
                if (isCorrect(ipAddressStr).equals("isCorrect")) {
                    Intent intent = new Intent(mContext, MainActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("ipAddress", ipAddressStr);
                    intent.putExtras(bundle);
                    startActivity(intent);
                    finish();
                }
            }
        });
        btnCancel = findViewById(R.id.btn_no);
        btnCancel.setOnClickListener(new View.OnClickListener() {
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

    /**
     * Double click Back to exit
     */

    public void onBackPressed() {
        long secondTime = System.currentTimeMillis();
        if (secondTime - firstTime > 2000) {
            Toasty.info(LoginActivity.this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
            firstTime = secondTime;
        } else {
            System.exit(0);
        }
    }
}
