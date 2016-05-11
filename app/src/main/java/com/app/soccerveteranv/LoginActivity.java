package com.app.soccerveteranv;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.app.soccerveteranv.util.PrefUtil;
import com.app.soccerveteranv.vo.UserProfileVo;
import com.app.soccerveteranv.widget.VeteranToast;
import com.bumptech.glide.Glide;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.Profile;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.kakao.auth.AuthType;
import com.kakao.auth.ISessionCallback;
import com.kakao.auth.Session;
import com.kakao.network.ErrorResult;
import com.kakao.usermgmt.UserManagement;
import com.kakao.usermgmt.callback.LogoutResponseCallback;
import com.kakao.usermgmt.callback.MeResponseCallback;
import com.kakao.usermgmt.response.model.UserProfile;
import com.kakao.util.exception.KakaoException;
import com.kakao.util.helper.log.Logger;

import java.util.Arrays;

/**
 * Created by sungbo on 2016-04-28.
 */
public class LoginActivity extends AppCompatActivity {

    private UserProfileVo userProfileVo;
    private PrefUtil prefUtil;

    private CallbackManager callbackManager;
    private Button facebook_btn;
    private Button kakao_btn;

    private SessionCallback kakaoCallback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        prefUtil = new PrefUtil(this);

        userProfileVo = prefUtil.getUserProfile();

        if(userProfileVo.getSnsname() == null) {
            setContentView(R.layout.layout_login);
            setCustomActionbar();

            facebook_btn = (Button) findViewById(R.id.facebook_login_btn);
            facebook_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    facebook_login();
                }
            });
            kakao_btn = (Button) findViewById(R.id.kakao_login_btn);
            kakao_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    kakao_login();
                }
            });
        }else {
            setContentView(R.layout.layout_login_after);
            setCustomActionbarLoginAfter();

            //알파설정
            //Drawable drawable = findViewById(R.id.layout_after).getBackground();
            //drawable.setAlpha(80);

            TextView textView = (TextView) findViewById(R.id.nav_bar_header_txt);
            textView.setText(userProfileVo.getUsername()+" 설정");

            // TODO: 2016-04-14
            // 로그 아웃시 SNS 토큰무효화 작업. 그리고 네비헤더의 사진과 이름을 null로 바꾸어준다. 그리고 메인으로 이동

            Button button = (Button) findViewById(R.id.btn_logout);
            //progress = (ProgressBarCircularIndeterminate) findViewById(R.id.progress_cu);
            //progress.setVisibility(View.INVISIBLE);

            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    VeteranToast.makeToast(getApplicationContext(),"로그아웃 합니다",Toast.LENGTH_SHORT).show();

                    //progress.setVisibility(View.VISIBLE);

                    if(userProfileVo.getSnsname()=="KAKAO"){
                        //VeteranToast.makeToast(getApplicationContext(),"카카오로 회원가입",Toast.LENGTH_SHORT).show();
                        Session.getCurrentSession().isAvailableOpenByRefreshToken();
                        onClickLogout();
                    }else if(userProfileVo.getSnsname()=="FACEBOOK"){
                        //VeteranToast.makeToast(getApplicationContext(), "페이스북으로 회원가입", Toast.LENGTH_SHORT).show();
                        FacebookSdk.sdkInitialize(getApplicationContext());
                        LoginManager.getInstance().logOut();
                    }else{
                        VeteranToast.makeToast(getApplicationContext(),"싸커비로 회원가입",Toast.LENGTH_SHORT).show();
                        // TODO: 2016-04-14 이곳에 사커비기능으로 회원 가입한 사람을 강제 로그아웃시킨다.
                    }
                    MainActivity.txt_username.setText("로그인이 필요합니다");
                    Glide.with(MainActivity.mainActivity)
                            .load(R.drawable.userimg)
                            .into(MainActivity.profileImgView)
                    ;
                    prefUtil.clearToken();
                    MainActivity.navigationView.getMenu().findItem(R.id.nav_login).setTitle("로그인");


                }
            });
        }
    }


    //로그인 후 네비바의 사용자 정보 업데이트
    public void reset_nav_header(UserProfileVo userProfileVo,String snsTag){

        MainActivity.txt_username.setText(userProfileVo.getUsername());

        Glide.with(MainActivity.mainActivity)
                .load(userProfileVo.getProfileImgUrl())
                .into(MainActivity.profileImgView);

        VeteranToast.makeToast(getApplicationContext(), "유저 정보를 Soccer Veteran 에 반영합니다 (" + snsTag + ")", Toast.LENGTH_SHORT).show();
        MainActivity.navigationView.getMenu().findItem(R.id.nav_login).setTitle("설정");
        finish();
    }

    //카카오톡 로그인한 유저
    public void kakao_login(){

        //VeteranToast.makeToast(getApplicationContext(), "카카오 로그인 시작", Toast.LENGTH_SHORT).show();

        //카카오세션 오픈
        kakaoCallback = new SessionCallback();
        Session.getCurrentSession().addCallback(kakaoCallback);
        Session.getCurrentSession().checkAndImplicitOpen();
        Session.getCurrentSession().open(AuthType.KAKAO_TALK_EXCLUDE_NATIVE_LOGIN, LoginActivity.this);

        //세션 및 상태 확인
        //String s = Session.getCurrentSession().getAccessToken();
        //VeteranToast.makeToast(getApplicationContext(), "카카오 토큰 : " + s, Toast.LENGTH_SHORT).show();
        //if(Session.getCurrentSession().hasValidAccessToken()){
        //    VeteranToast.makeToast(getApplicationContext(), "유효한 토큰", Toast.LENGTH_SHORT).show();
        //}
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        //카카오 callback
        if (Session.getCurrentSession().handleActivityResult(requestCode, resultCode, data)) {
            return;
        }

        //페이스북 callback
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Session.getCurrentSession().removeCallback(kakaoCallback);
    }

    private class SessionCallback implements ISessionCallback{

        @Override
        public void onSessionOpened() {
            kakaoMe();
        }

        @Override
        public void onSessionOpenFailed(KakaoException exception) {
            if(exception != null) {
                Logger.e(exception);
            }
            setContentView(R.layout.layout_login);
        }
    }


    private void onClickLogout() {
        UserManagement.requestLogout(new LogoutResponseCallback() {
            @Override
            public void onCompleteLogout() {
            }
        });
    }

    //페이스북으로 로그인한 유저
    public void facebook_login(){
        FacebookSdk.sdkInitialize(getApplicationContext());
        callbackManager = CallbackManager.Factory.create();

        LoginManager.getInstance().logInWithReadPermissions(this, Arrays.asList("public_profile", "email"));
        LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {

                Profile profile = Profile.getCurrentProfile();
                String accessToken = loginResult.getAccessToken().getToken();
                String profileImgUrl = "https://graph.facebook.com/" + profile.getId() + "/picture?type=large";

                UserProfileVo userProfileVo = new UserProfileVo("FACEBOOK", profile.getId(), profile.getName(), profileImgUrl, accessToken);

                prefUtil.saveUser(userProfileVo);
                VeteranToast.makeToast(getApplicationContext(), profile.getName() + "님께서 로그인 하셨습니다 [FACEBOOK]", Toast.LENGTH_SHORT).show();
                reset_nav_header(userProfileVo, "FACEBOOK");
            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {
                error.printStackTrace();
            }
        });
    }


    protected void kakaoMe() {

        UserManagement.requestMe(new MeResponseCallback() {
            @Override
            public void onFailure(ErrorResult errorResult) {
                int ErrorCode = errorResult.getErrorCode();
                int ClientErrorCode = -777;

                if (ErrorCode == ClientErrorCode) {
                    VeteranToast.makeToast(getApplicationContext(), "카카오톡 서버의 네트워크가 불안정합니다. 잠시 후 다시 시도해주세요.", Toast.LENGTH_SHORT).show();
                } else {
                    Log.d("KAKAO_LOG", "오류로 카카오로그인 실패 ");
                }
            }

            @Override
            public void onSessionClosed(ErrorResult errorResult) {
                Log.d("TAG", "오류로 카카오로그인 실패 ");
            }

            @Override
            public void onSuccess(UserProfile userProfile) {

                String getuserid = String.valueOf(userProfile.getId());

                UserProfileVo userProfileVo = new UserProfileVo("KAKAO",
                        getuserid,
                        userProfile.getNickname(),
                        userProfile.getProfileImagePath(),
                        Session.getCurrentSession().getAccessToken());

                prefUtil.saveUser(userProfileVo);
                VeteranToast.makeToast(getApplicationContext(), userProfile.getNickname() + "님께서 로그인 하셨습니다 [KAKAO]", Toast.LENGTH_SHORT).show();
                reset_nav_header(userProfileVo, "KAKAO");
            }

            @Override
            public void onNotSignedUp() {

            }
        });
    }

    private void setCustomActionbar(){
        ActionBar actionBar = getSupportActionBar();

        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(false);
        actionBar.setDisplayShowTitleEnabled(false);

        //만든 레이아웃을 붙힌다
        View mCustomView = LayoutInflater.from(this).inflate(R.layout.actionbar_main,null);

        //actionBar.setBackgroundDrawable(getResources().getDrawable(R.drawable.ac));
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.argb(255, 255, 255, 255)));
        ActionBar.LayoutParams layoutParams = new ActionBar.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT, android.app.ActionBar.LayoutParams.MATCH_PARENT);
        actionBar.setCustomView(mCustomView, layoutParams);
    }

    public void mBackbtn(View v){
        finish();
    }

    //커스텀 액션바 만들기 로그인후
    private void setCustomActionbarLoginAfter(){
        ActionBar actionBar = getSupportActionBar();

        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(false);
        actionBar.setDisplayShowTitleEnabled(false);

        //만든 레이아웃을 붙힌다
        View mCustomView = LayoutInflater.from(this).inflate(R.layout.actionbar_login_after,null);

        //actionBar.setBackgroundDrawable(getResources().getDrawable(R.drawable.ac));
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.argb(255, 255, 255, 255)));

        ActionBar.LayoutParams layoutParams = new ActionBar.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT, android.app.ActionBar.LayoutParams.MATCH_PARENT);

        actionBar.setCustomView(mCustomView, layoutParams);

    }

}
