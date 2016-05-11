package com.app.soccerveteranv.vo;

/**
 * Created by sungbo on 2016-04-25.
 */
public class UserVideoVo {

    int lank;

    String userid;

    //비디오 아이디
    String vedioid;

    //비디오 찍은 유저명
    String username;

    //영상 설명
    String description;

    //유저들의 평균 점수
    String avgscore;

    public UserVideoVo(int lank, String userid, String vedioid, String username, String description, String avgscore) {
        this.lank = lank;
        this.userid = userid;
        this.vedioid = vedioid;
        this.username = username;
        this.description = description;
        this.avgscore = avgscore;
    }

    public int getLank() {
        return lank;
    }

    public void setLank(int lank) {
        this.lank = lank;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getVedioid() {
        return vedioid;
    }

    public void setVedioid(String vedioid) {
        this.vedioid = vedioid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAvgscore() {
        return avgscore;
    }

    public void setAvgscore(String avgscore) {
        this.avgscore = avgscore;
    }
}
