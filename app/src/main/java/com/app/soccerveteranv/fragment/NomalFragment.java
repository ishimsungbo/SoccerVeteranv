package com.app.soccerveteranv.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.app.soccerveteranv.R;
import com.app.soccerveteranv.adapter.MittionAdapter;
import com.app.soccerveteranv.widget.VeteranToast;

/**
 * Created by sungbo on 2016-04-09.
 */
public class NomalFragment extends Fragment {

    public static final String ARG_PAGE = "ARG_PAGE";
    private int mPage;

    MittionAdapter adapter;
    ListView lv_mlist;

    public static NomalFragment newInstance(int page) {
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, page);
        NomalFragment fragment = new NomalFragment();
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

        if(mPage==1){
            VeteranToast.makeToast(getContext(),"일반리프팅섹션", Toast.LENGTH_SHORT).show();

        }else if(mPage==2){
            view =  inflater.inflate(R.layout.middle_layout_dribble_basic, container, false);
        }else if(mPage==3){
            view =  inflater.inflate(R.layout.middle_layout_trapping_basic, container, false);
        }


        return view;
    }


}
