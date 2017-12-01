package com.devmcryyu.smarthome;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by 92075 on 2017/11/5.
 */

public class LivingRoom_Fragment extends Fragment {
    private TextView txtTemperature;
    private TextView txtLight;
    private TextView txtBody;
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
                    txtTemperature.setText(""+receiveMessage.liv_Temperature+ "℃");
                    txtLight.setText("" + receiveMessage.liv_Light);
                    if (receiveMessage.liv_Body == '1')
                        txtBody.setText("有");
                    else
                        txtBody.setText("无");
                }
            });
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.livingroom_fragment, container, false);
        txtTemperature = view.findViewById(R.id.txt_liv_Temperature);
        txtLight = view.findViewById(R.id.txt_liv_Light);
        txtBody = view.findViewById(R.id.txt_liv_Body);
        mContext = this.getActivity();
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


}
