package com.app.soccerveteranv.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.app.soccerveteranv.R;
import com.app.soccerveteranv.adapter.MittionAdapter;

/**
 * Created by sungbo on 2016-04-09.
 * 일반 관련 프래그먼트
 *
 *
 */
public class FreeFragment extends Fragment {

    public static final String ARG_PAGE = "ARG_PAGE";
    private int mPage;

    MittionAdapter adapter;
    ListView lv_mlist;

    public static FreeFragment newInstance(int page) {
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, page);
        FreeFragment fragment = new FreeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPage = getArguments().getInt(ARG_PAGE);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = null;

        //XML Layout 뷰는 성격에 맞게 분류 해준다.

        if(mPage==1){
            view =  inflater.inflate(R.layout.middle_layout_lifting_basic, container, false);
        }else if(mPage==2){
            view =  inflater.inflate(R.layout.middle_layout_dribble_basic, container, false);
        }else if(mPage==3){
            view =  inflater.inflate(R.layout.middle_layout_trapping_basic, container, false);
        }


        return view;
    }

}
