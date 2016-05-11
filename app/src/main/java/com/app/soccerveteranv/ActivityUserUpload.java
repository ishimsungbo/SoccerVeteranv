package com.app.soccerveteranv;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.app.soccerveteranv.adapter.YouTubeUserVideoAdapter;
import com.app.soccerveteranv.vo.UserVideoVo;

import java.util.ArrayList;

/**
 * Created by sungbo on 2016-04-25.
 */
public class ActivityUserUpload extends AppCompatActivity {

    String VIDEOID;
    ListView lv_userlist;
    YouTubeUserVideoAdapter uAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //홈키버튼
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setBackgroundDrawable(getResources().getDrawable(R.drawable.listhigh));
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.homeback);
        getSupportActionBar().setTitle("유저영상목록");

        setContentView(R.layout.activity_userupload);

        Intent intent = getIntent();

        if(intent!=null){
            VIDEOID = intent.getStringExtra("VIDEOID");
        }

        //VeteranToast.makeToast(getApplicationContext(),"V ID : "+ VIDEOID, Toast.LENGTH_SHORT).show();
        lv_userlist = (ListView) findViewById(R.id.listview_up_user);

        final ArrayList<UserVideoVo> items = new ArrayList<UserVideoVo>();

        items.add(new UserVideoVo(1,"aaa","KagnY_Z2N90","구선혜","선혜가 도전한 영상","10"));
        items.add(new UserVideoVo(2,"dsad","KagnY_Z2N90","장나라","나라가 도전한 영상","8"));
        items.add(new UserVideoVo(3,"weqwe","KagnY_Z2N90","걸스구","걸스가 도전한 영상","6"));
        items.add(new UserVideoVo(4,"zxczxc","KagnY_Z2N90","미연방","미연방가 도전한 영상","7"));
        items.add(new UserVideoVo(5,"www","KagnY_Z2N90","장동건","동건이가 도전한 영상","9"));
        items.add(new UserVideoVo(6,"lfofk","KagnY_Z2N90","구구짱","구구가 도전한 영상","7"));
        items.add(new UserVideoVo(7,"bliuekd","KagnY_Z2N90","로스","로스가 도전한 영상","6"));
        items.add(new UserVideoVo(8,"talasod","KagnY_Z2N90","로이","로이가 도전한 영상","5"));
        items.add(new UserVideoVo(9,"ajdimf","KagnY_Z2N90","임파스","임파스가 도전한 영상","3"));
        items.add(new UserVideoVo(10,"mania","KagnY_Z2N90","맥스","맥스가 도전한 영상","5"));
        items.add(new UserVideoVo(11,"test","KagnY_Z2N90","볼트","볼트가 도전한 영상","8"));
        items.add(new UserVideoVo(12,"good","KagnY_Z2N90","뽀로로","뽀로로가 도전한 영상","6"));
        items.add(new UserVideoVo(13,"keke","KagnY_Z2N90","구찌","구찌가 도전한 영상","6"));

        uAdapter = new YouTubeUserVideoAdapter(getApplication(), R.layout.uservideolist_view_item,items);
        lv_userlist.setAdapter(uAdapter);

        lv_userlist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //다른 유저의 동영상 콘텐츠
                Intent intentContent = new Intent(getApplicationContext(), ActivityUser.class);
                intentContent.putExtra("VIDEOID",items.get(position).getVedioid());
                startActivity(intentContent);
            }
        });
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
}
