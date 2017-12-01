package com.devmcryyu.smarthome;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.IdRes;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ConnectException;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import es.dmoral.toasty.Toasty;

public class MainActivity extends Activity {
    private RadioGroup radiogroup;
    private long firstTime = 0;
    private Context mContext;
    private Bundle sendBundle;
    private Socket socket;
    private BufferedReader bufferedReader;
    private OutputStream outputStream;
    private String ipAddress;
    protected Handler handler;
    private LivingRoom_Fragment liv_frag;
    private Kitchen_Fragment kit_frag;
    private Curtain_Fragment cur_frag;
    private BedRoom_Fragment bed_frag;
    private mBroadcastReceiver mBroadcastReceiver = new mBroadcastReceiver() {
        @Override
        public void onReceive(Context context, final Intent intent) {
            final byte msg = intent.getExtras().getByte("send");
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        outputStream.write(msg);
                        outputStream.flush();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
            thread.start();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);
        mContext = this;
        handler = new Handler();
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        ipAddress = bundle.getString("ipAddress");
        System.out.println(ipAddress);
        ExecutorService mThreadPool = Executors.newFixedThreadPool(10);
        mThreadPool.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    // 创建Socket对象 & 指定服务端的IP 及 端口号
                    try {
                        socket = new Socket(ipAddress, 8000);
                    } catch (ConnectException e) {
                        System.out.println("not connect");
                    }
                    bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    outputStream = socket.getOutputStream();
                    // 判断客户端和服务器是否连接成功
                    System.out.println(socket.isConnected());
                    if (socket != null && socket.isConnected())
//                        System.out.println("connected");
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                Toasty.success(mContext, "连接成功", Toast.LENGTH_SHORT).show();
                            }
                        });
                    while (socket.isConnected()) {
                        char[] buffer = new char[32];
                        int length;
                        length = bufferedReader.read(buffer);
                        char[] msgReceiveArray = new char[length];
                        for (int i = 0; i < length; i++)
                            msgReceiveArray[i] = buffer[i];
                        sendBundle = new Bundle();
                        sendBundle.putString("receiveMsg", new String(msgReceiveArray));
                        Intent mIntent = new Intent();
                        mIntent.putExtras(sendBundle);
                        mIntent.setAction("updateUI");
                        sendBroadcast(mIntent);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        init();


    }

    @Override
    public void onResume() {
        super.onResume();
        //实例化BroadcastReceiver子类 &  IntentFilter
        IntentFilter intentFilter = new IntentFilter();

        //设置接收广播的类型
        intentFilter.addAction("send");

        //调用Context的registerReceiver（）方法进行动态注册
        mContext.registerReceiver(mBroadcastReceiver, intentFilter);

    }

    @Override
    public void onPause() {
        super.onPause();
        //销毁在onResume()方法中的广播
        mContext.unregisterReceiver(mBroadcastReceiver);
    }

    public void init() {
        /**
         * 加载按钮对应实例
         */
        radiogroup = findViewById(R.id.radioGroup);
        radiogroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                switch (radiogroup.getCheckedRadioButtonId()) {
                    case R.id.btn_livingroom: {
                        if (liv_frag == null)
                            liv_frag = new LivingRoom_Fragment();
                        FragmentManager fm = getFragmentManager();
                        FragmentTransaction bt = fm.beginTransaction();
                        bt.replace(R.id.frame, liv_frag);
                        bt.commit();
//                        Toast.makeText(mContext, "现在打开的是客厅", Toast.LENGTH_SHORT).show();
                        Toasty.info(mContext, "现在打开的是客厅", Toast.LENGTH_SHORT).show();

                        break;
                    }
                    case R.id.btn_kitchen: {
                        if (kit_frag == null)
                            kit_frag = new Kitchen_Fragment();
                        FragmentManager fm = getFragmentManager();
                        FragmentTransaction bt = fm.beginTransaction();
                        bt.replace(R.id.frame, kit_frag);
                        bt.commit();
//                        Toast.makeText(mContext, "现在打开的是厨房", Toast.LENGTH_SHORT).show();
                        Toasty.info(mContext, "现在打开的是厨房", Toast.LENGTH_SHORT).show();
                        break;
                    }
                    case R.id.btn_curtain: {
                        if (cur_frag == null)
                            cur_frag = new Curtain_Fragment();
                        FragmentManager fm = getFragmentManager();
                        FragmentTransaction bt = fm.beginTransaction();
                        bt.replace(R.id.frame, cur_frag);
                        bt.commit();
//                        Toast.makeText(mContext, "现在打开的是窗帘", Toast.LENGTH_SHORT).show();
                        Toasty.info(mContext, "现在打开的是窗帘", Toast.LENGTH_SHORT).show();
                        break;
                    }
                    case R.id.btn_bedroom: {
                        if (bed_frag == null)
                            bed_frag = new BedRoom_Fragment();
                        FragmentManager fm = getFragmentManager();
                        FragmentTransaction bt = fm.beginTransaction();
                        bt.replace(R.id.frame, bed_frag);
                        bt.commit();
//                        Toast.makeText(mContext, "现在打开的是卧室", Toast.LENGTH_SHORT).show();
                        Toasty.info(mContext, "现在打开的是卧室", Toast.LENGTH_SHORT).show();
                        break;
                    }
                    default:
                        break;
                }

            }
        });
    }

    /**
     * Double click Back to exit
     */

    public void onBackPressed() {
        long secondTime = System.currentTimeMillis();
        if (secondTime - firstTime > 2000) {
            Toasty.info(MainActivity.this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
            firstTime = secondTime;
        } else {
            System.exit(0);
        }
    }

}
