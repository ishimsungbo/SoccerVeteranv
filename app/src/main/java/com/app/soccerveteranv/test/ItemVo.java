package com.app.soccerveteranv.test;

/**
 * Created by sungbo on 2016-04-11.
 */
public class ItemVo {
    String vid;
    String videoid;

    public String getVid() {
        return vid;
    }

    public void setVid(String vid) {
        this.vid = vid;
    }

    public String getVideoid() {
        return videoid;
    }

    public void setVideoid(String videoid) {
        this.videoid = videoid;
    }

    public ItemVo(String vid, String videoid) {
        this.vid = vid;
        this.videoid = videoid;
    }
}
