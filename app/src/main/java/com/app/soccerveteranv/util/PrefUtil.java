package com.app.soccerveteranv.util;

import android.app.Activity;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.app.soccerveteranv.vo.UserProfileVo;

public class PrefUtil {

    private Activity activity;

    // constructor
    public PrefUtil(Activity activity) {
        this.activity = activity;
    }

    public void clearToken() {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(activity);
        SharedPreferences.Editor editor = sp.edit();
        editor.clear();
        editor.apply();
    }

    public void saveUser(UserProfileVo userProfileVo){
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(activity);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("snsname", userProfileVo.getSnsname());
        editor.putString("userid", userProfileVo.getUserid());
        editor.putString("username", userProfileVo.getUsername());
        editor.putString("profileImgUrl", userProfileVo.getProfileImgUrl());
        editor.putString("token", userProfileVo.getToken());
        editor.apply();
    }

    public UserProfileVo getUserProfile() {
        UserProfileVo userProfileVo = new UserProfileVo();
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(activity);

        userProfileVo.setSnsname(sp.getString("snsname", null));
        userProfileVo.setUserid(sp.getString("userid", null));
        userProfileVo.setUsername(sp.getString("username", null));
        userProfileVo.setProfileImgUrl(sp.getString("profileImgUrl", null));
        userProfileVo.setToken(sp.getString("token", null));

        return userProfileVo;

    }



}