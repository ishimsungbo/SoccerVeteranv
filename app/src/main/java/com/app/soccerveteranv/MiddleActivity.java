package com.app.soccerveteranv;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.MenuItem;

import com.app.soccerveteranv.fragment.BasicFragment;
import com.astuetz.PagerSlidingTabStrip;

/**
 * Created by sungbo on 2016-03-29.
 */
public class MiddleActivity extends AppCompatActivity {

    PagerSlidingTabStrip tabsStrip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        //홈키버튼
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("기초 챌린지");
        getSupportActionBar().setBackgroundDrawable(getResources().getDrawable(R.drawable.listhigh));
        //홈키디자인변경
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.homeback);
        setContentView(R.layout.middle_layout);


        // Get the ViewPager and set it's PagerAdapter so that it can display items
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.setAdapter(new ViewFragmentPagerAdapter(getSupportFragmentManager()));

        // Give the PagerSlidingTabStrip the ViewPager
        tabsStrip = (PagerSlidingTabStrip) findViewById(R.id.tabs);


        tabsStrip.setIndicatorColor(Color.parseColor("#FF99CC33"));
        tabsStrip.setTextColor(Color.parseColor("#0099CB"));
        // Attach the view pager to the tab strip
        tabsStrip.setViewPager(viewPager);

    }

    public class ViewFragmentPagerAdapter extends FragmentPagerAdapter {

        private LayoutInflater mInflater;
        final int PAGE_COUNT = 3;

        private String tabTitles[] = new String[] { "기초 리프팅", "기초 드리블", "기초 트래핑" };

        public ViewFragmentPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return PAGE_COUNT;
        }

        @Override
        public Fragment getItem(int position) {
            return BasicFragment.newInstance(position + 1);
        }

        /*텍스트를 사용했을 경우*/
        @Override
        public CharSequence getPageTitle(int position) {
            return tabTitles[position];
        }


/*      아이콘을 사용했을 경우 implement 해야 된다.
        @Override
        public int getPageIconResId(int position) {
            return tabIcons[position];
        }*/


    }


    /*
    액션 버튼을 생성하기 위한 메소드

    private int tabIcons[] = {R.drawable.player, R.drawable.sports, R.drawable.people};

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_test, menu);
        return true;
    }
    */

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
