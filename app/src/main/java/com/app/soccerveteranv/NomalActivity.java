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

import com.app.soccerveteranv.fragment.NomalYoutubeFragment;
import com.astuetz.PagerSlidingTabStrip;

/**
 * Created by sungbo on 2016-04-09.
 */
public class NomalActivity extends AppCompatActivity {

    PagerSlidingTabStrip tabsStrip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //홈키버튼
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("일반 챌린지");
        getSupportActionBar().setBackgroundDrawable(getResources().getDrawable(R.drawable.listhigh));

        //홈키디자인변경
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.homeback);



        setContentView(R.layout.middle_layout);

        //middle_layout 셋팅한 ViewPager를 찾고
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);

        //콘텐트의 백그라운드
        //viewPager.setBackground(getDrawable(R.drawable.mainback));

        //뷰페이퍼에 만든 프래그먼트를 붙여준다.
        viewPager.setAdapter(new ViewFragmentPagerAdapter(getSupportFragmentManager()));

        // Give the PagerSlidingTabStrip the ViewPager
        tabsStrip = (PagerSlidingTabStrip) findViewById(R.id.tabs);
        // Attach the view pager to the tab striptabsStrip.setBackground(Color.GRAY);

        tabsStrip.setIndicatorColor(Color.parseColor("#FF99CC33"));
        tabsStrip.setTextColor(Color.parseColor("#0099CB"));
        tabsStrip.setViewPager(viewPager);



    }

    public class ViewFragmentPagerAdapter extends FragmentPagerAdapter {

        private LayoutInflater mInflater;
        final int PAGE_COUNT = 3;

        private String tabTitles[] = new String[] { "일반 리프팅", "일반 드리블", "일반 트래핑" };

        public ViewFragmentPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return PAGE_COUNT;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return tabTitles[position];
        }

        @Override
        public Fragment getItem(int position) {

            return NomalYoutubeFragment.newInstance(position + 1);
        }

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

/*
    private void setActionBar() {
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setCustomView(R.layout.header_actionbar);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.actionbar_blue)));
        TextView tvHeader = (TextView) findViewById(R.id.tv_title_header_actionbar);
        TextView tvSubheader = (TextView) findViewById(R.id.tv_subtitle_header_actionbar);
        tvSubheader.setVisibility(View.GONE);
    }

*/

}
