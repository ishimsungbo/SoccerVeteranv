package com.app.soccerveteranv.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.app.soccerveteranv.R;
import com.app.soccerveteranv.vo.Content;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by sungbo on 2016-04-26.
 */
public class UserContentAdapter extends BaseAdapter{

    private Context mContext = null;
    private int layout = 0;
    private ArrayList<Content> contents = null;
    private LayoutInflater inflater = null;
    private Map<View, TextView> mLoaders;

    public UserContentAdapter(Context c, int l, ArrayList<Content> itemVos){
        this.mContext = c;
        this.inflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.layout = l;
        this.contents = itemVos;
        mLoaders = new HashMap<View, TextView>();
    }


    @Override
    public int getCount() {
        return contents.size();
    }

    @Override
    public Object getItem(int position) {
        return contents.get(position).getId();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View currentRow = convertView;
        ComentHolder holder;

        if(convertView == null){

            currentRow = inflater.inflate(layout,parent,false);
            holder = new ComentHolder();

            //객체 셋업
            holder.id = (TextView) currentRow.findViewById(R.id.user_id_tx);
            holder.username = (TextView) currentRow.findViewById(R.id.user_name_tx);
            holder.content = (TextView) currentRow.findViewById(R.id.user_content_tx);

            //디스플레이에 사용할 객체에 데이터 담기
            holder.id.setText(String.valueOf(contents.get(position).getId()));
            holder.username.setText(contents.get(position).getUsername());
            holder.content.setText(contents.get(position).getContent());
            currentRow.setTag(holder);
        }else{

            holder = (ComentHolder) currentRow.getTag();
            if(contents.get(position)!=null){
                holder.id.setText(String.valueOf(contents.get(position).getId()));
                holder.username.setText(contents.get(position).getUsername());
                holder.content.setText(contents.get(position).getContent());
            }
        }

        return currentRow;
    }

    static class ComentHolder {

        TextView id;
        TextView username;
        TextView content;

    }
}
