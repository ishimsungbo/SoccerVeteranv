package com.app.soccerveteranv.network;

import com.app.soccerveteranv.common.Tmessage;
import com.app.soccerveteranv.vo.MisstionVo;

import java.util.ArrayList;

import retrofit.Call;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.POST;

/**
 * Created by sungbo on 2016-04-05.
 */
public interface MisstionListService {

    @GET("/api/mlist")
    Call<ArrayList<MisstionVo>> getMlist();

    @GET("/api/mlistnomal")
    Call<ArrayList<MisstionVo>> getmlistnomal();

    @FormUrlEncoded
    @POST("/an/test")
    Call<Tmessage> sendName(@Field("first_name") String first, @Field("last_name") String last);
}
