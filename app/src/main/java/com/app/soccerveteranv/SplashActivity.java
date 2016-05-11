package com.app.soccerveteranv;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;

import com.app.soccerveteranv.util.PrefUtil;

import java.util.Random;


/**
 * Created by sungbo on 2016-03-28.
 */
public class SplashActivity extends Activity {

    private PrefUtil prefUtil;

    String sfName = "soccerVeteranSetup";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //랜덤으로 레이아웃을 바꿔준다.
        Random random = new Random();

        int data = 0;
        data = random.nextInt(2);

        if(data==0){
            setContentView(R.layout.activity_splash_n01);
        }else if(data==1){
            setContentView(R.layout.activity_splash_n02);
        }else if(data==2){
            setContentView(R.layout.activity_splash_n03);
        }

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                finish();
            }
        }, 2000);

    }

}
