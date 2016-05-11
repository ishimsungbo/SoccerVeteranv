package com.app.soccerveteranv.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.app.soccerveteranv.R;
import com.app.soccerveteranv.vo.UserVideoVo;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubeThumbnailLoader;
import com.google.android.youtube.player.YouTubeThumbnailView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by sungbo on 2016-04-25.
 */
public class YouTubeUserVideoAdapter extends BaseAdapter implements YouTubeThumbnailView.OnInitializedListener{

    YouTubeThumbnailView ThumbnailView;

    private Context mContext = null;
    private int layout = 0;
    private ArrayList<UserVideoVo> userVideoVos = null;
    private LayoutInflater inflater = null;
    private Map<View, YouTubeThumbnailLoader> mLoaders;

    public YouTubeUserVideoAdapter(Context c, int l, ArrayList<UserVideoVo> itemVos) {
        this.mContext = c;
        this.inflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.layout = l;
        this.userVideoVos = itemVos;
        mLoaders = new HashMap<View, YouTubeThumbnailLoader>();
    }

    @Override
    public int getCount() {
        return userVideoVos.size();
    }

    @Override
    public Object getItem(int position) {
        return userVideoVos.get(position).getVedioid();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View currentRow = convertView;
        VideoHolder videoHolder;

        if(convertView == null){

            currentRow = inflater.inflate(layout,parent,false);
            videoHolder = new VideoHolder();

            videoHolder.userv_thumbnail = (YouTubeThumbnailView) currentRow.findViewById(R.id.userv_thumbnail);
            videoHolder.userv_name  = (TextView) currentRow.findViewById(R.id.userv_name);
            videoHolder.userv_disp  = (TextView) currentRow.findViewById(R.id.userv_disp);
            videoHolder.userv_lank = (TextView) currentRow.findViewById(R.id.userv_lank);

            videoHolder.userv_name.setText(userVideoVos.get(position).getUsername());
            videoHolder.userv_disp.setText(userVideoVos.get(position).getDescription());
            videoHolder.userv_thumbnail.setTag(userVideoVos.get(position).getVedioid());

            videoHolder.userv_lank.setText(String.valueOf(userVideoVos.get(position).getLank()));

            try{
                videoHolder.userv_thumbnail.initialize(String.valueOf(R.string.youtube_api_key),this);
            }catch (Exception e){
                e.printStackTrace();
            }
            currentRow.setTag(videoHolder);
        }else{
            videoHolder = (VideoHolder) currentRow.getTag();
            final YouTubeThumbnailLoader loader = mLoaders.get(videoHolder.userv_thumbnail);
            if(userVideoVos.get(position) != null){

                videoHolder.userv_name.setText(userVideoVos.get(position).getUsername());
                videoHolder.userv_disp.setText(userVideoVos.get(position).getDescription());
                videoHolder.userv_lank.setText(String.valueOf(userVideoVos.get(position).getLank()));

                if (loader == null) {
                    //Loader is currently initialising
                    videoHolder.userv_thumbnail.setTag(userVideoVos.get(position).getVedioid());
                } else {
                    //The loader is already initialised
                    loader.setVideo(userVideoVos.get(position).getVedioid());
                }

            }
        }

        return currentRow;
    }

    @Override
    public void onInitializationSuccess(YouTubeThumbnailView youTubeThumbnailView, YouTubeThumbnailLoader youTubeThumbnailLoader) {

        this.ThumbnailView = youTubeThumbnailView;
        //ThumbnailView.animate().alpha(50f);
        //ThumbnailView.setVisibility(View.INVISIBLE);
        youTubeThumbnailLoader.setVideo((String) youTubeThumbnailView.getTag());


    }

    @Override
    public void onInitializationFailure(YouTubeThumbnailView youTubeThumbnailView, YouTubeInitializationResult youTubeInitializationResult) {

    }

    //실제 유투브 비디오 아이디는 빼놓는다. 어댑터에 바로 참좋서 넣는다.
    static class VideoHolder {

        TextView userv_lank;

        //영상 유투브썸네일
        YouTubeThumbnailView userv_thumbnail;

        //영상찍은 유저명
        TextView userv_name;

        //영상 설명
        TextView userv_disp;

        //평균 유저들 점수
        TextView avgscore;

    }

}
