package com.app.soccerveteranv.vo;

/**
 * Created by sungbo on 2016-04-05.
 */
public class MisstionVo {

    private String mid;
    private String videoid;
    private String disp;

    public MisstionVo(String mid, String videoid, String disp) {
        this.mid = mid;
        this.videoid = videoid;
        this.disp = disp;
    }

    public String getMid() {
        return mid;
    }

    public void setMid(String mid) {
        this.mid = mid;
    }

    public String getVideoid() {
        return videoid;
    }

    public void setVideoid(String videoid) {
        this.videoid = videoid;
    }

    public String getDisp() {
        return disp;
    }

    public void setDisp(String disp) {
        this.disp = disp;
    }

    public MisstionVo() {
    }



    @Override
    public String toString() {
        return "MisstionVo{" +
                "mid=" + mid +
                ", videoid='" + videoid + '\'' +
                ", disp='" + disp + '\'' +
                '}';
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
