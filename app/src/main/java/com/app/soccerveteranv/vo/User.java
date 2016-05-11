package com.app.soccerveteranv.vo;

import java.io.Serializable;

/**
 * Created by sungbo on 2016-04-03.
 */
public class User implements Serializable {

    private String name;
    private String profileImgUrl;

    public User(){}

    public User(String name, String profileImgUrl) {
        this.name = name;
        this.profileImgUrl = profileImgUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProfileImgUrl() {
        return profileImgUrl;
    }

    public void setProfileImgUrl(String profileImgUrl) {
        this.profileImgUrl = profileImgUrl;
    }
}
