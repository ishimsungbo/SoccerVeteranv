package com.app.soccerveteranv.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.app.soccerveteranv.ContentActivity;
import com.app.soccerveteranv.R;
import com.app.soccerveteranv.adapter.YouTubeMissionAdapter;
import com.app.soccerveteranv.vo.MisstionVo;

import java.util.ArrayList;

/**
 * Created by sungbo on 2016-04-09.
 */
public class NomalYoutubeFragment extends Fragment {

    private String YOUTUBE_API_KEY;
    public static final String ARG_PAGE = "ARG_PAGE";
    private int mPage;

    YouTubeMissionAdapter youTubeMissionAdapter;
    ListView lv_mlist;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPage = getArguments().getInt(ARG_PAGE);
    }

    public static NomalYoutubeFragment newInstance(int page) {
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, page);
        NomalYoutubeFragment fragment = new NomalYoutubeFragment();
        fragment.setArguments(args);

        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootview = null;

        //// TODO: 2016-04-11 서버에 저장 되어 있는 일반성인 리프팅 영상 목록을 가져온다.
        if(mPage==1){

            final ArrayList<MisstionVo> items = new ArrayList<MisstionVo>();
            items.add(new MisstionVo("1","KagnY_Z2N90","인스텝 7개"));
            items.add(new MisstionVo("2","xh8E6vqW7yk","인사이드 7개"));
            items.add(new MisstionVo("3","5Dsn4g7Mqx4","무릎 7개"));
            items.add(new MisstionVo("4","re0VRK6ouwI","헤딩 7개"));
            items.add(new MisstionVo("5","blB_X38YSxQ","엘레베이터"));
            items.add(new MisstionVo("6","Bu927_ul_X0","Low Low High"));
            items.add(new MisstionVo("7","3I24bSteJpw","복합"));
            items.add(new MisstionVo("8","BqnPbdd0V9E","준비중"));
            items.add(new MisstionVo("9","Hjas-lZikiA","준비중"));
            items.add(new MisstionVo("10","A6gLxrwCPak","준비중"));


            rootview = inflater.inflate(R.layout.middle_layout_lifting_basic,container,false);
            lv_mlist = (ListView) rootview.findViewById(R.id.nomal_lifting_listview);

            youTubeMissionAdapter = new YouTubeMissionAdapter(getActivity(), R.layout.mission_youtube_view_item,items);
            lv_mlist.setAdapter(youTubeMissionAdapter);

            lv_mlist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent intentContent = new Intent(getContext(), ContentActivity.class);
                    intentContent.putExtra("VIDEOID",items.get(position).getVideoid());
                    startActivity(intentContent);
                }
            });


        }else if(mPage==2){

            final ArrayList<MisstionVo> items = new ArrayList<MisstionVo>();
            items.add(new MisstionVo("1","qX4I6X_OMCs","육룡이 7개"));
            items.add(new MisstionVo("2","czL62WvX0ig","인사이드 7개"));
            items.add(new MisstionVo("3","N3uu4OtDo60","무릎 7개"));

            // TODO: 2016-04-14
            // 1. 뷰를 만들고...서버에서 데이터를 가져온다
            // 2. 데이터가 담길 리스트를 찾아오고
            // 3. 리스트에 데이터를 담는다
            rootview =  inflater.inflate(R.layout.middle_layout_dribble_basic, container, false);
            lv_mlist = (ListView) rootview.findViewById(R.id.nomal_dribble_listview);
            youTubeMissionAdapter = new YouTubeMissionAdapter(getActivity(), R.layout.mission_youtube_view_item,items);
            lv_mlist.setAdapter(youTubeMissionAdapter);

            lv_mlist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent intentContent = new Intent(getContext(), ContentActivity.class);
                    intentContent.putExtra("VIDEOID", items.get(position).getVideoid());
                    startActivity(intentContent);
                }
            });

        }else if(mPage==3){
            rootview =  inflater.inflate(R.layout.middle_layout_trapping_basic, container, false);
        }
        return rootview;
    }
}
