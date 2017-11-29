package com.devmcryyu.smarthome;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.IdRes;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.concurrent.ExecutorService;

import es.dmoral.toasty.Toasty;

public class MainActivity extends Activity {
    private RadioGroup radiogroup;
    private long firstTime = 0;
    private Context mContext;
    private Handler handler;
    /**
     * 接收服务器消息 变量
     */
    // 输入流对象
    InputStream is;

    // 输入流读取器对象
    InputStreamReader isr;
    BufferedReader br;

    // 接收服务器发送过来的消息
    String response;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);
        mContext = this;
//        IP_Fragment ip_frag = new IP_Fragment();
//        FragmentManager fm = getFragmentManager();
//        FragmentTransaction bt = fm.beginTransaction();
//        bt.replace(R.id.frame, ip_frag);
//        bt.commit();
        init();
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
                        LivingRoom_Fragment liv_frag = new LivingRoom_Fragment();
                        FragmentManager fm = getFragmentManager();
                        FragmentTransaction bt = fm.beginTransaction();
                        bt.replace(R.id.frame, liv_frag);
                        bt.commit();
//                        Toast.makeText(mContext, "现在打开的是客厅", Toast.LENGTH_SHORT).show();
                        Toasty.info(mContext, "现在打开的是客厅", Toast.LENGTH_SHORT).show();

                        break;
                    }
                    case R.id.btn_kitchen: {
                        Kitchen_Fragment kit_frag = new Kitchen_Fragment();
                        FragmentManager fm = getFragmentManager();
                        FragmentTransaction bt = fm.beginTransaction();
                        bt.replace(R.id.frame, kit_frag);
                        bt.commit();
//                        Toast.makeText(mContext, "现在打开的是厨房", Toast.LENGTH_SHORT).show();
                        Toasty.info(mContext, "现在打开的是厨房", Toast.LENGTH_SHORT).show();
                        break;
                    }
                    case R.id.btn_curtain: {
                        Curtain_Fragment cur_frag = new Curtain_Fragment();
                        FragmentManager fm = getFragmentManager();
                        FragmentTransaction bt = fm.beginTransaction();
                        bt.replace(R.id.frame, cur_frag);
                        bt.commit();
//                        Toast.makeText(mContext, "现在打开的是窗帘", Toast.LENGTH_SHORT).show();
                        Toasty.info(mContext, "现在打开的是窗帘", Toast.LENGTH_SHORT).show();
                        break;
                    }
                    case R.id.btn_bedroom: {
                        BedRoom_Fragment bed_frag = new BedRoom_Fragment();
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
