package com.devmcryyu.smarthome;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * Created by 92075 on 2017/11/5.
 */

public class BedRoom_Fragment extends Fragment  {
    private TextView txtTemperature;
    private TextView txtLight;
    private TextView txtBody;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.bedroom_fragment,container,false);
        txtTemperature=view.findViewById(R.id.txt_bed_Temperature);
        txtLight=view.findViewById(R.id.txt_bed_Light);
        txtBody=view.findViewById(R.id.txt_bed_Body);

        return view;

    }
//
//    @Override
//    public void dataReceiveEvent(DataReceiveEvent dataReceiveEvent) {
//        char[] array=new char[dataReceiveEvent.getReceiveData().length()];
//        for (int i=0; i<dataReceiveEvent.getReceiveData().length(); i++){
//            array[i]=dataReceiveEvent.getReceiveData().charAt(i);
//        }//把数据移到新的数组里面
//        String s=new String(array);//把array数组转换成s字符串
//        final float numTemperature= (float) (Integer.parseInt(s.substring(16,17))*10+
//                Integer.parseInt(s.substring(17,18))+Integer.parseInt(s.substring(18,19))*0.1);
//        final int numLight= Integer.parseInt(s.substring(19,20))*10+Integer.parseInt(s.substring(20,21));
//        final char numBody=s.charAt(21);
//        txtBody.post(new Runnable() {
//            @Override
//            public void run() {
//                txtTemperature.setText("" + numTemperature + "℃");
//                txtLight.setText(""+ numLight);
//                if (numBody == '1') txtBody.setText( "有");
//                else txtBody.setText("无");
//            }
//        });
//
//    }
}
