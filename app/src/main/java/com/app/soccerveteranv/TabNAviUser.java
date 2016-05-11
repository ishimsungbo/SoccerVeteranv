package com.app.soccerveteranv;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;

/**
 * Created by sungbo on 2016-04-26.
 */
public class TabNAviUser extends RelativeLayout {

    Context context;

    public TabNAviUser(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.tab_navi_user, this, true);
    }
}
