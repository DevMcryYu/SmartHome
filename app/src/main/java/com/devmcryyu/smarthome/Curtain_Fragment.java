package com.devmcryyu.smarthome;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import es.dmoral.toasty.Toasty;

/**
 * Created by 92075 on 2017/11/5.
 */

public class Curtain_Fragment extends Fragment implements View.OnClickListener {
    private TextView txtLight;
    private TextView txtShake;
    private TextView txtState;
    private Context mContext;
    private receiveMessage receiveMessage;
    private mBroadcastReceiver mBroadcastReceiver = new mBroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String msg = intent.getExtras().getString("receiveMsg");
            System.out.println(msg);
            receiveMessage = new receiveMessage(msg);
            System.out.println(receiveMessage.toString());
            Handler handler = new Handler();
            handler.post(new Runnable() {
                @Override
                public void run() {
                    if (receiveMessage.cur_State == '1') {
                        MediaPlayer mediaPlayer1 = MediaPlayer.create(mContext, R.raw.geng);
                        mediaPlayer1.start();
                    }
                    txtLight.setText("" + receiveMessage.cur_Light);
                    if (receiveMessage.cur_Shake == '0')
                        txtShake.setText("无震动");
                    else
                        txtShake.setText("有震动");
                    if (receiveMessage.cur_State == '0')
                        txtState.setText("关");
                    else
                        txtState.setText("开");
                }
            });
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.curtain_fragment, container, false);
        txtLight = view.findViewById(R.id.txt_cur_Light);
        txtShake = view.findViewById(R.id.txt_cur_Shake);
        txtState = view.findViewById(R.id.txt_cur_State);
        mContext = this.getActivity();
        Button btnClose = view.findViewById(R.id.btnClose);
        btnClose.setOnClickListener(this);
        Button btnOpen = view.findViewById(R.id.btnOpen);
        btnOpen.setOnClickListener(this);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        //实例化BroadcastReceiver子类 &  IntentFilter
        IntentFilter intentFilter = new IntentFilter();

        //设置接收广播的类型
        intentFilter.addAction("updateUI");

        //调用Context的registerReceiver（）方法进行动态注册
        mContext.registerReceiver(mBroadcastReceiver, intentFilter);

    }

    @Override
    public void onPause() {
        super.onPause();
        //销毁在onResume()方法中的广播
        mContext.unregisterReceiver(mBroadcastReceiver);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnOpen: {
                try {
//                    Toast.makeText(this.getActivity(), "正在开窗帘", Toast.LENGTH_SHORT).show();
                    Toasty.info(this.getActivity(), "正在开窗帘", Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
//                    Toast.makeText(this.getActivity(), "开窗帘失败", Toast.LENGTH_SHORT).show();
                    Toasty.error(this.getActivity(), "开窗帘失败", Toast.LENGTH_SHORT).show();

                }
                break;
            }
            case R.id.btnClose: {
                try {
//                    Toast.makeText(this.getActivity(), "正在关窗帘", Toast.LENGTH_SHORT).show();
                    Toasty.info(this.getActivity(), "正在关窗帘", Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
//                    Toast.makeText(this.getActivity(), "关窗帘失败", Toast.LENGTH_SHORT).show();
                    Toasty.error(this.getActivity(), "关窗帘失败", Toast.LENGTH_SHORT).show();
                }
                break;
            }
            default:
                break;
        }
    }
}
