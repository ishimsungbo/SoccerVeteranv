package com.app.soccerveteranv;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;

/**
 * Created by sungbo on 2016-04-25.
 * 탭메뉴를 생성한다
 */
public class TabNaviComplaintBase extends RelativeLayout {

    Context context;

    public TabNaviComplaintBase(Context context,AttributeSet attrs) {
        super(context,attrs);
        this.context = context;

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.tab_navi_complatint,this,true);

    }
}
