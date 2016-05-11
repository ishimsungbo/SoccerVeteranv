package com.app.soccerveteranv.sns;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.app.soccerveteranv.widget.VeteranToast;
import com.kakao.auth.ISessionCallback;
import com.kakao.auth.Session;
import com.kakao.util.exception.KakaoException;
import com.kakao.util.helper.log.Logger;

/**
 * Created by sungbo on 2016-04-14.
 */
public class KakaoActivity extends Activity {

    private SessionCallback callback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void test(){
        SessionCallback callback = new SessionCallback();

        Session.getCurrentSession().addCallback(callback);
        Session.getCurrentSession().checkAndImplicitOpen();

        String s = Session.getCurrentSession().getAccessToken();
        VeteranToast.makeToast(getApplicationContext(), "카카오 토큰 : " + s, Toast.LENGTH_SHORT).show();

        if(Session.getCurrentSession().hasValidAccessToken()){
            VeteranToast.makeToast(getApplicationContext(), "유효한 토큰", Toast.LENGTH_SHORT).show();
        }
    }


    public class SessionCallback implements ISessionCallback {
        @Override
        public void onSessionOpened() {
            VeteranToast.makeToast(getApplicationContext(), "세션이 오픈 되었습니다.", Toast.LENGTH_SHORT).show();
            getApplicationContext();
        }

        @Override
        public void onSessionOpenFailed(KakaoException exception) {
            if(exception != null) {
                Logger.e(exception);
            }
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (Session.getCurrentSession().handleActivityResult(requestCode, resultCode, data)) {
            VeteranToast.makeToast(getApplicationContext(), "값이 돌아왔음", Toast.LENGTH_SHORT).show();
            return;
        }

        super.onActivityResult(requestCode, resultCode, data);
    }


}
