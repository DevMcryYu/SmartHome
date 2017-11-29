package com.devmcryyu.smarthome;

import android.app.Fragment;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by 92075 on 2017/11/5.
 */

public class Kitchen_Fragment extends Fragment {
    private TextView txtTemperature;
    private TextView txtSmog;
    private boolean state;
    private MediaPlayer mediaPlayer1;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.kitchen_fragment, container, false);
        txtTemperature = view.findViewById(R.id.txt_kit_Temperature);
        txtSmog = view.findViewById(R.id.txt_kit_Smog);
        state = false;
        return view;
    }
//    @Override
//    public void dataReceiveEvent(final DataReceiveEvent dataReceiveEvent) {
//        char[] array=new char[dataReceiveEvent.getReceiveData().length()];
//        for (int i=0; i<dataReceiveEvent.getReceiveData().length(); i++){
//            array[i]=dataReceiveEvent.getReceiveData().charAt(i);
//        }//把数据移到新的数组里面
//        String s=new String(array);//把array数组转换成s字符串
//        final float numTemperature= (float) (Integer.parseInt(s.substring(0,1))*10+
//                Integer.parseInt(s.substring(1,2))+Integer.parseInt(s.substring(2,3))*0.1);
//        final int numSmog=Integer.parseInt(s.substring(3,4))*100+Integer.parseInt(s.substring(4,5))*10+
//                Integer.parseInt(s.substring(5,6));
//        if (numSmog >= 30&&!state)
//        {
//            mediaPlayer1 = null;
//            mediaPlayer1 = MediaPlayer.create(this.getActivity(),R.raw.enen);
//            mediaPlayer1.start();
//            mediaPlayer1.setLooping(true);
//            state=true;
//    }
//        else
//        {
//            mediaPlayer1.stop();
//            state=false;
//        }
//        txtSmog.post(new Runnable() {
//            @Override
//            public void run() {
//                txtTemperature.setText(""+ numTemperature + " ℃");
//                if (numSmog >= 30) txtSmog.setText( "有");
//                else txtSmog.setText("无");
//            }
//        });
//    }
}
