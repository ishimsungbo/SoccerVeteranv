package com.app.soccerveteranv.vo;

/**
 * Created by sungbo on 2016-04-11.
 */
public class UserProfileVo {

    private String snsname;
    private String userid;
    private String username;
    private String profileImgUrl;
    private String token;

    public UserProfileVo(){};

    public UserProfileVo(String snsname, String userid, String username, String profileImgUrl, String token) {
        this.snsname = snsname;
        this.userid = userid;
        this.username = username;
        this.profileImgUrl = profileImgUrl;
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getSnsname() {
        return snsname;
    }

    public void setSnsname(String snsname) {
        this.snsname = snsname;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getProfileImgUrl() {
        return profileImgUrl;
    }

    public void setProfileImgUrl(String profileImgUrl) {
        this.profileImgUrl = profileImgUrl;
    }

    @Override
    public String toString() {
        return "UserProfileVo{" +
                "snsname='" + snsname + '\'' +
                ", userid='" + userid + '\'' +
                ", username='" + username + '\'' +
                ", profileImgUrl='" + profileImgUrl + '\'' +
                '}';
    }
}
