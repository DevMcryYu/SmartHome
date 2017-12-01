package com.devmcryyu.smarthome;

/**
 * Created by 92075 on 2017/11/22.
 */

public class receiveMessage {
    public float kit_Temperature;
    public int kit_Smog;
    public float liv_Temperature;
    public int liv_Light;
    public char liv_Body;
    public int cur_Light;
    public char cur_Shake;
    public char cur_State;
    public float bed_Temperature;
    public int bed_Light;
    public char bed_Body;

    public receiveMessage() {
        kit_Temperature = 0;
        kit_Smog = 0;
        liv_Temperature = 0;
        liv_Light = 0;
        liv_Body = '0';
        cur_Light = 0;
        cur_Shake = '0';
        cur_State = '0';
        bed_Temperature = 0;
        bed_Light = 0;
        bed_Body = '0';
    }

    public receiveMessage(String s) {
        kit_Temperature = (float) (Integer.parseInt(s.substring(0, 1)) * 10 +
                Integer.parseInt(s.substring(1, 2)) + Integer.parseInt(s.substring(2, 3)) * 0.1);
        kit_Smog = Integer.parseInt(s.substring(3, 4)) * 100 + Integer.parseInt(s.substring(4, 5)) * 10 +
                Integer.parseInt(s.substring(5, 6));
        liv_Temperature = (float) (Integer.parseInt(s.substring(6, 7)) * 10 +
                Integer.parseInt(s.substring(7, 8)) + Integer.parseInt(s.substring(8, 9)) * 0.1);
        liv_Light = Integer.parseInt(s.substring(9, 10)) * 10 + Integer.parseInt(s.substring(10, 11));
        liv_Body = s.charAt(11);
        cur_Light = (s.charAt(12) - 48) * 10 + (s.charAt(13) - 48);
        cur_Shake = s.charAt(14);
        cur_State = s.charAt(15);
        bed_Temperature = (float) (Integer.parseInt(s.substring(16, 17)) * 10 +
                Integer.parseInt(s.substring(17, 18)) + Integer.parseInt(s.substring(18, 19)) * 0.1);
        bed_Light = Integer.parseInt(s.substring(19, 20)) * 10 + Integer.parseInt(s.substring(20, 21));
        bed_Body = s.charAt(21);
    }

    public String toString() {
        return " kit_Temperature= " + kit_Temperature + " kit_Smog= " + kit_Smog + " liv_Temperature= " + liv_Temperature + " liv_Light= " + liv_Light +
                " liv_Body= " + liv_Body + "\n cur_Light= " + cur_Light + " cur_Shake= " + cur_Shake + " cur_State= " + cur_State + " bed_Temperature= " + bed_Temperature +
                " bed_Light= " + bed_Light + " bed_Body= " + bed_Body + "\n";
    }
}
