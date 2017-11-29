package com.devmcryyu.smarthome;

import android.app.Fragment;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by 92075 on 2017/11/5.
 */

public class LivingRoom_Fragment extends Fragment implements Runnable {
    private TextView txtTemperature;
    private TextView txtLight;
    private TextView txtBody;
//    public Handler handler = new Handler() {
//        public void handleMessage(Message msg) {
//            super.handleMessage(msg);
//            receiveMessage = (com.devmcryyu.smarthome.receiveMessage) msg.obj;
//
//        }
//    };
//    private receiveMessage receiveMessage;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.livingroom_fragment, container, false);
        txtTemperature = view.findViewById(R.id.txt_liv_Temperature);
        txtLight = view.findViewById(R.id.txt_liv_Light);
        txtBody = view.findViewById(R.id.txt_liv_Body);
        return view;

    }

    @Override
    public void run() {
//        txtBody.post(new Runnable() {
//            @Override
//            public void run() {
//
//                txtTemperature.setText("" + receiveMessage.liv_Temperature + "℃");
//                txtLight.setText("" + receiveMessage.liv_Light);
//                if (receiveMessage.liv_Body == '1') txtBody.setText("有");
//                else txtBody.setText("无");
//            }
//        });
    }
}
