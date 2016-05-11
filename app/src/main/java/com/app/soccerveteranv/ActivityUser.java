package com.app.soccerveteranv;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;
import android.widget.ListView;

import com.app.soccerveteranv.adapter.UserContentAdapter;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerSupportFragment;

import butterknife.ButterKnife;

/**
 * Created by sungbo on 2016-04-25.
 */
public class ActivityUser extends AppCompatActivity implements YouTubePlayer.OnInitializedListener{


    private YouTubePlayer player;
    private YouTubePlayer.Provider provider;
    String VIDEOID;

    ListView lv_content;
    UserContentAdapter userContentAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //안드로이드 키보드창 위로 ui올리기
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("홍길동 영상");
        getSupportActionBar().setBackgroundDrawable(getResources().getDrawable(R.drawable.listhigh));
        //홈키디자인변경
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.homeback);

        setContentView(R.layout.user_content);

        lv_content = (ListView) findViewById(R.id.userContent_lv);

        ButterKnife.bind(this);

        //유저의 비디오를 본다
       Intent intent = getIntent();
        if(intent!=null){
            VIDEOID = intent.getStringExtra("VIDEOID");
        }

        YouTubePlayerSupportFragment frag =
                (YouTubePlayerSupportFragment) getSupportFragmentManager().findFragmentById(R.id.uyoutube_fragment);
        frag.initialize(String.valueOf(R.string.youtube_api_key), this);
    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
        if(!b){
            youTubePlayer.cueVideo(VIDEOID);
        }
    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

    }



}
