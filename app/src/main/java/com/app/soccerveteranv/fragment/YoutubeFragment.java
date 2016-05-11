package com.app.soccerveteranv.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.app.soccerveteranv.R;
import com.app.soccerveteranv.adapter.MittionAdapter;
import com.app.soccerveteranv.widget.VeteranToast;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerSupportFragment;

/**
 * Created by sungbo on 2016-04-09.
 */
public class YoutubeFragment extends Fragment {

    public static final String ARG_PAGE = "ARG_PAGE";
    private int mPage;

    MittionAdapter adapter;
    ListView lv_mlist;
    public static final String API_KEY = "AIzaSyB7f2Pbu2ZpqyFsaVYR9q-gtyToB-6ip18";
    public static final String VIDEO_ID = "KagnY_Z2N90";


    public static YoutubeFragment newInstance(int page) {
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, page);
        YoutubeFragment fragment = new YoutubeFragment();
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

        View rootview = null;

        if(mPage==1) {

            VeteranToast.makeToast(getContext(),"유투브관련테스트", Toast.LENGTH_SHORT).show();

            rootview = inflater.inflate(R.layout.youtubeview, container, false);

            YouTubePlayerSupportFragment youTubePlayerFragment = YouTubePlayerSupportFragment.newInstance();
            FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
            transaction.add(R.id.youtube_layout, youTubePlayerFragment).commit();

            youTubePlayerFragment.initialize(API_KEY, new YouTubePlayer.OnInitializedListener() {
                @Override
                public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                    if (!b) {
                        youTubePlayer.setPlayerStyle(YouTubePlayer.PlayerStyle.DEFAULT);
                        youTubePlayer.loadVideo(VIDEO_ID);
                        youTubePlayer.play();
                    }
                }

                @Override
                public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

                }
            });
        }else if(mPage==2){
            rootview =  inflater.inflate(R.layout.middle_layout_dribble_basic, container, false);
        }else if(mPage==3){
            rootview =  inflater.inflate(R.layout.middle_layout_trapping_basic, container, false);

        }

        return rootview;
    }

}
