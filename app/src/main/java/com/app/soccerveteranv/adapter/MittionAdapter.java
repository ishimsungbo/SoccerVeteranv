package com.app.soccerveteranv.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.app.soccerveteranv.R;
import com.app.soccerveteranv.vo.MisstionVo;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubeThumbnailLoader;
import com.google.android.youtube.player.YouTubeThumbnailView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by sungbo on 2016-04-05.
 */
public class MittionAdapter extends BaseAdapter implements YouTubeThumbnailView.OnInitializedListener {



    private Context mContext = null;
    private int layout = 0;
    private ArrayList<MisstionVo> misstionVos = null;
    private LayoutInflater inflater = null;
    private Map<View, YouTubeThumbnailLoader> mLoaders;

    public MittionAdapter(Context c, int l, ArrayList<MisstionVo> d) {
        this.mContext = c;
        this.layout = l;
        this.misstionVos = d;
        this.inflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mLoaders = new HashMap<View, YouTubeThumbnailLoader>();
    }

    @Override
    public int getCount() {
        return misstionVos.size();
    }

    @Override
    public Object getItem(int position) {
        return misstionVos.get(position).getMid();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View currentRow = convertView;
        VideoHolder holder;

        if(convertView==null){

            currentRow = inflater.inflate(layout, parent, false);
            holder = new VideoHolder();
            holder.mid = (TextView) currentRow.findViewById(R.id.noaml_txt_vid);
            holder.description = (TextView) currentRow.findViewById(R.id.noaml_txt_disp);
            holder.thumb = (YouTubeThumbnailView) currentRow.findViewById(R.id.imageView_thumbnail);

            holder.mid.setText(misstionVos.get(position).getMid());
            holder.description.setText(misstionVos.get(position).getDisp());
            holder.thumb.setTag(misstionVos.get(position).getVideoid());

            holder.thumb.initialize(String.valueOf(R.string.youtube_api_key),this);
            currentRow.setTag(holder);
        }else{
            holder = (VideoHolder) currentRow.getTag();
            final YouTubeThumbnailLoader loader = mLoaders.get(holder.thumb);

            if(misstionVos.get(position) != null){
                holder.mid.setText(misstionVos.get(position).getMid());
                holder.description.setText(misstionVos.get(position).getDisp());
                if (loader == null) {
                    //Loader is currently initialising
                    holder.thumb.setTag(misstionVos.get(position).getVideoid());
                } else {
                    //The loader is already initialised
                    loader.setVideo(misstionVos.get(position).getVideoid());
                }

            }
        }
        /*
        holder.thumb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                VeteranToast.makeToast(mContext, "미리보기를 클릭하셨습니다", Toast.LENGTH_SHORT).show();
            }
        });
        */
        return currentRow;

    }

    @Override
    public void onInitializationSuccess(YouTubeThumbnailView youTubeThumbnailView, YouTubeThumbnailLoader youTubeThumbnailLoader) {
        youTubeThumbnailLoader.setVideo((String) youTubeThumbnailView.getTag());
    }

    @Override
    public void onInitializationFailure(YouTubeThumbnailView youTubeThumbnailView, YouTubeInitializationResult youTubeInitializationResult) {

    }


    static class VideoHolder {
        YouTubeThumbnailView thumb;
        TextView mid;
        TextView description;
    }
}
