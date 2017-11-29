package com.devmcryyu.smarthome;

import android.app.Fragment;
import android.media.MediaPlayer;
import android.os.Bundle;
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

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.curtain_fragment, container, false);
        txtLight = view.findViewById(R.id.txt_cur_Light);
        txtShake = view.findViewById(R.id.txt_cur_Shake);
        txtState = view.findViewById(R.id.txt_cur_State);
        Button btnClose = view.findViewById(R.id.btnClose);
        btnClose.setOnClickListener(this);
        Button btnOpen = view.findViewById(R.id.btnOpen);
        btnOpen.setOnClickListener(this);
        return view;
    }

//    @Override
//    public void dataReceiveEvent(DataReceiveEvent dataReceiveEvent) {
//        char[] array = new char[dataReceiveEvent.getReceiveData().length()];
//        for (int i = 0; i < dataReceiveEvent.getReceiveData().length(); i++) {
//            array[i] = dataReceiveEvent.getReceiveData().charAt(i);
//        }//把数据移到新的数组里面
//        String s = new String(array);//把array数组转换成s字符串
//        final int numLight = (s.charAt(12) - 48) * 10 + (s.charAt(13) - 48);
//        final char numShake = s.charAt(14);
//        final char numState = s.charAt(15);
//        //当玻璃有震动时，发出警报声
//        if (numShake == '1') {
//            MediaPlayer mediaPlayer1 = MediaPlayer.create(this.getActivity(), R.raw.geng);
//            mediaPlayer1.start();
//        }
//        txtLight.post(new Runnable() {
//            @Override
//            public void run() {
//                txtLight.setText("" + numLight);
//                if (numShake == '0') txtShake.setText("无震动");
//                else txtShake.setText("有震动");
//                if (numState == '0') txtState.setText("关");
//                else txtState.setText("开");
//            }
//        });
//
//    }
//
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
