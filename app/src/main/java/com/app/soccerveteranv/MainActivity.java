package com.app.soccerveteranv;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.soccerveteranv.util.PrefUtil;
import com.app.soccerveteranv.vo.UserProfileVo;
import com.app.soccerveteranv.widget.DialogBuilder;
import com.bumptech.glide.Glide;

import java.security.MessageDigest;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by sungbo on 2016-04-03.
 * 4월 2일 SharedPreferences 담은 내용 가져와서 navigation.getheader.findview.... 이미지를 매치해준다.
 * 4월 9일 getSupportActionBar().setTitle("") 액티비티의 메일 타이틀 제목을 설정해준다.
 * 4월 11~12 SNS 로그인, 로그아웃, 유투브 동영상 썸네일 및 재생 화면 구성 fragment,shrapreperence
 */
public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    public static ImageView profileImgView;
    public static NavigationView navigationView;
    public static TextView txt_username;
    public static Menu menu;

    public static Activity mainActivity;
    private PrefUtil prefUtil;
    private UserProfileVo userProfileVo;

    ImageButton imageButton01;
    ImageButton imageButton02;
    ImageButton imageButton03;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mainActivity = this;

        prefUtil = new PrefUtil(this);
        setContentView(R.layout.activity_main);

        //이미지 버튼 투명처리
        imageButton01 = (ImageButton) findViewById(R.id.btn01);
        imageButton02 = (ImageButton) findViewById(R.id.btn02);
        imageButton03 = (ImageButton) findViewById(R.id.btn03);

        Drawable drawable1 = imageButton01.getBackground();
        drawable1.setAlpha(5);

        Drawable drawable2 = imageButton02.getBackground();
        drawable2.setAlpha(5);

        Drawable drawable3 = imageButton03.getBackground();
        drawable3.setAlpha(5);


        //로그인 정보가 있다면 로직 분기
        PrefUtil prefUtil = new PrefUtil(this);
        userProfileVo = prefUtil.getUserProfile();

        startActivity(new Intent(this, SplashActivity.class));


        //만약 로그인을 받는 다면

        /*
        setSupportActionBar() 메서드는 인자로 받은 툴바를 액티비티의 액션바로 대체하는 역할을 합니다.
        기본으로 제공되는 액션바 외에 별도로 툴바를 사용하고 싶다면 이 메서드를
        호출하지 않고 툴바만 단독으로 사용하는 것도 가능합니다.
        */

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setLogo(R.drawable.slogo);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        // TODO: 2016-04-14 메뉴바를 제어한다

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        View header = navigationView.getHeaderView(0);
        profileImgView = (ImageView) header.findViewById(R.id.iv_user_profile);
        txt_username = (TextView) header.findViewById(R.id.txt_username);

       if(userProfileVo.getSnsname() != null){

           Glide.with(MainActivity.this)
                   .load(userProfileVo.getProfileImgUrl())
                   .into(profileImgView);

           navigationView.getMenu().findItem(R.id.nav_login).setTitle("설정");
       }



        ButterKnife.bind(this);
        getAppKeyHash();

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    /*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main_drawer, menu);
        menu.findItem(R.id.nav_login).setIcon(R.drawable.kakaostory_icon);
        return true;
    }
    */



    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_login) {
            overridePendingTransition(R.anim.in_from_right, R.anim.out_to_left);
            Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.in_from_right, R.anim.out_to_left);

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @OnClick(R.id.btn01)
    void modiOnclick_01(){


        Intent basicIntent = new Intent(getApplicationContext(),MiddleActivity.class);    //beginning
        startActivity(basicIntent);

        //액티비티 전환시 애니메이션 효과
        overridePendingTransition(R.anim.in_from_right, R.anim.out_to_left);

    }

    @OnClick(R.id.btn02)
    void modiOnclick_02(){

        Intent nomalIntent = new Intent(getApplicationContext(),NomalActivity.class);
        startActivity(nomalIntent);

        //액티비티 전환시 애니메이션 효과
        overridePendingTransition(R.anim.in_from_right, R.anim.out_to_left);
    }

    @OnClick(R.id.btn03)
    void modiOnclick_03(){

        Intent freeIntent = new Intent(getApplicationContext(),FreeActivity.class);
        startActivity(freeIntent);

        //액티비티 전환시 애니메이션 효과
        overridePendingTransition(R.anim.in_from_right, R.anim.out_to_left);
    }

    private void getAppKeyHash() {
        try {
            PackageInfo info = getPackageManager().getPackageInfo(getPackageName(), PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md;
                md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                String something = new String(Base64.encode(md.digest(), 0));
                Log.d("Hash key", something);
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            Log.e("name not found", e.toString());
        }
    }

        /* 메일 아이콘
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/


    //빽버튼 클릭시 앱종료
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        switch (keyCode) {
            //하드웨어 뒤로가기 버튼에 따른 이벤트 설정
            case KeyEvent.KEYCODE_BACK:

                new DialogBuilder(MainActivity.this)
                        //.setTitle("질문")
                        .setMessage("앱을 종료하시겠습니까?")
                        .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        })
                        .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                                android.os.Process.killProcess(android.os.Process.myPid());
                            }
                        }).create().show();
                break;
            default:
                break;
        }

        return super.onKeyDown(keyCode, event);
    }

    public void LiClick(View v){
        switch (v.getId()){
            case R.id.mlinearLayout1:
                Intent basicIntent = new Intent(getApplicationContext(),MiddleActivity.class);    //beginning
                startActivity(basicIntent);

                //액티비티 전환시 애니메이션 효과
                overridePendingTransition(R.anim.in_from_right, R.anim.out_to_left);
                break;
            case R.id.mlinearLayout2:
                Intent nomalIntent = new Intent(getApplicationContext(),NomalActivity.class);
                startActivity(nomalIntent);

                //액티비티 전환시 애니메이션 효과
                overridePendingTransition(R.anim.in_from_right, R.anim.out_to_left);
                break;
            case R.id.mlinearLayout3:
                Intent freeIntent = new Intent(getApplicationContext(),FreeActivity.class);
                startActivity(freeIntent);

                //액티비티 전환시 애니메이션 효과
                overridePendingTransition(R.anim.in_from_right, R.anim.out_to_left);
                break;
        }

    }

}
