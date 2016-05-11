package com.app.soccerveteranv;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.ImageButton;
import android.widget.Toast;

import com.app.soccerveteranv.widget.VeteranToast;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubeIntents;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerSupportFragment;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by sungbo on 2016-04-07.
 */
public class ContentActivity extends AppCompatActivity implements YouTubePlayer.OnInitializedListener{

    private YouTubePlayer player;
    String VIDEOID;

    ImageButton camera,upload,board,sharing;

    //유투브 업로드를 위해 필요한 값
    private static final String EXTRA_LOCAL_ONLY = "android.intent.extra.LOCAL_ONLY";


    //영상 촬영시, 영상 업로드시
    private static final int PICK_FROM_CAMERA = 1;
    private static final int PICK_FROM_GALLERY = 2;

    ImageButton tab_btn01;
    ImageButton tab_btn02;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //홈키버튼
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("도전할 영상");
        getSupportActionBar().setBackgroundDrawable(getResources().getDrawable(R.drawable.listhigh));
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.homeback);


        setContentView(R.layout.layout_video_content);

        Intent intent = getIntent();
        if(intent!=null){
            VIDEOID = intent.getStringExtra("VIDEOID");
        }

        ButterKnife.bind(this);

        YouTubePlayerSupportFragment frag =
                (YouTubePlayerSupportFragment) getSupportFragmentManager().findFragmentById(R.id.youtube_fragment);
        frag.initialize(String.valueOf(R.string.youtube_api_key), this);

        sharing = (ImageButton) findViewById(R.id.youtube_sharing);
        camera  = (ImageButton) findViewById(R.id.youtube_camera);
        upload  = (ImageButton) findViewById(R.id.youtube_upload);
        board   = (ImageButton) findViewById(R.id.youtube_board);

        Drawable drawble_sharing = sharing.getBackground();
        drawble_sharing.setAlpha(10);

        Drawable drawble_camera = camera.getBackground();
        drawble_camera.setAlpha(10);

        Drawable drawble_upload = upload.getBackground();
        drawble_upload.setAlpha(10);

        Drawable drawble_board = board.getBackground();
        drawble_board.setAlpha(10);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        /*
        if (id == R.id.action_button) {
            Toast.makeText(this, "액션버튼 이벤트", Toast.LENGTH_SHORT).show();
            return true;
        }
        */

        //홈버튼클릭시
        if (id == android.R.id.home){
            finish(); //뒤로가기
            //overridePendingTransition(R.anim.fade, R.anim.hold); //애니메이션 효과
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @OnClick(R.id.youtube_camera)
    void execute_Camera(){
        Intent intent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);

        intent.putExtra(MediaStore.EXTRA_OUTPUT,
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI.toString());

        try {
            intent.putExtra("return-data", true);
            startActivityForResult(intent, PICK_FROM_CAMERA);

        } catch (ActivityNotFoundException e) {

        }
    }

    @OnClick(R.id.youtube_upload)
    void execute_Upload(){
        Intent intent = new Intent(Intent.ACTION_PICK, null).setType("video/*");
        intent.putExtra(EXTRA_LOCAL_ONLY, true);
        startActivityForResult(intent, PICK_FROM_GALLERY);

    }

    /*공유 할수 있는...
    * 카톡 이나 카카오톡에 올릴수 있게 해준다.
    * 메일/ 전화걸기 / 기타 등등 가능함.
    * */
    @OnClick(R.id.youtube_sharing)
    void sharing(){

        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_SUBJECT, "사커베테랑 미션영상");
        intent.putExtra(Intent.EXTRA_TEXT,"https://youtu.be/"+VIDEOID);
        intent.putExtra(Intent.EXTRA_TITLE, "사커베테랑 미션을 영상을 공유합니다");
        startActivity(Intent.createChooser(intent, "Soccer Veteran"));

    }

    /*다른 유저들의 업로드 동영상를 볼수 있다
    * */
    @OnClick(R.id.youtube_board)
    void uploadUser(){
        Intent intentContent = new Intent(this, ActivityUserUpload.class);
        intentContent.putExtra("VIDEOID", VIDEOID);
        startActivity(intentContent);
        overridePendingTransition(R.anim.in_from_right, R.anim.out_to_left);
    }


    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
        this.player = youTubePlayer;
        player.setPlayerStyle(YouTubePlayer.PlayerStyle.DEFAULT);
        if(!b){
            youTubePlayer.cueVideo(VIDEOID);

        }
    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult error) {
        VeteranToast.makeToast(getApplicationContext(),error.toString() +VIDEOID, Toast.LENGTH_SHORT).show();
    }

    public void onActivityResult(int requestCode,
                                 int resultCode,
                                 Intent data) {

        if (requestCode == PICK_FROM_CAMERA) {
            VeteranToast.makeToast(getApplicationContext(), "영상을 찍음", Toast.LENGTH_SHORT).show();
        }
        if (requestCode == PICK_FROM_GALLERY) {
            try{

                VeteranToast.makeToast(getApplicationContext(),"파일명은 : "+data.getData().getPath(),Toast.LENGTH_SHORT).show();

                Uri i = data.getData();
                String name = getName(i);

                Intent intent = YouTubeIntents.createUploadIntent(this, data.getData());
                //intent.setData(YouTube.Videos.Update.USER_AGENT_SUFFIX,i);
                startActivity(intent);
                startActivityForResult(intent, 205);
                VeteranToast.makeToast(getApplicationContext(), "유투브 업로드 하기?" + name, Toast.LENGTH_SHORT).show();
            }catch (NullPointerException e){
                VeteranToast.makeToast(getApplicationContext(), "업로드를 취소하셨습니다", Toast.LENGTH_SHORT).show();
            }
        }

        if(requestCode==205){
            VeteranToast.makeToast(getApplicationContext(), "업 데이터 : ", Toast.LENGTH_SHORT).show();
        }

        //super.onActivityResult(requestCode, resultCode, data);
    }

    private String getName(Uri uri)
    {
        String[] projection = { MediaStore.Images.ImageColumns.DISPLAY_NAME };
        Cursor cursor = managedQuery(uri, projection, null, null, null);
        int column_index = cursor
                .getColumnIndexOrThrow(MediaStore.Images.ImageColumns.DISPLAY_NAME);
        cursor.moveToFirst();
        return cursor.getString(column_index);
    }
}
