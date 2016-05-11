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

import com.app.soccerveteranv.fragment.FreeFragment;
import com.astuetz.PagerSlidingTabStrip;

/**
 * Created by sungbo on 2016-04-09.
 */
public class FreeActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //홈키버튼
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("프리스타일 챌린지");
        getSupportActionBar().setBackgroundDrawable(getResources().getDrawable(R.drawable.listhigh));
        //홈키디자인변경
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.homeback);

        setContentView(R.layout.middle_layout);

        // Get the ViewPager and set it's PagerAdapter so that it can display items
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.setAdapter(new ViewFragmentPagerAdapter(getSupportFragmentManager()));

        // Give the PagerSlidingTabStrip the ViewPager
        PagerSlidingTabStrip tabsStrip = (PagerSlidingTabStrip) findViewById(R.id.tabs);

        tabsStrip.setIndicatorColor(Color.parseColor("#FF99CC33"));
        tabsStrip.setTextColor(Color.parseColor("#0099CB"));

        // Attach the view pager to the tab strip
        tabsStrip.setViewPager(viewPager);
    }

    public class ViewFragmentPagerAdapter extends FragmentPagerAdapter {

        private LayoutInflater mInflater;

        private String tabTitles[] = new String[] { "Section Foot", "Section Body"};

        final int PAGE_COUNT = tabTitles.length;

        public ViewFragmentPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return PAGE_COUNT;
        }

        @Override
        public Fragment getItem(int position) {
            return FreeFragment.newInstance(position + 1);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return tabTitles[position];
        }

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //홈버튼클릭시
        if (id == android.R.id.home){
            finish(); //뒤로가기
            //overridePendingTransition(R.anim.fade, R.anim.hold); //애니메이션 효과
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
