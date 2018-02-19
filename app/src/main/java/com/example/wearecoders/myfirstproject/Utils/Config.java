package com.example.wearecoders.myfirstproject.Utils;

import com.example.wearecoders.myfirstproject.JSON_Result;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by We Are Coders on 2/7/2018.
 */

public interface Config {

    @FormUrlEncoded
    @POST(Constants.URL_CHECK)
    Call<ResponseBody> check(@Field("login_token") String token, @Field("user_id") String id);

    @FormUrlEncoded
    @POST(Constants.URL_FACEBOOK)
    Call<JSON_Result> login_facebook(@Field("access_token") String accestoken, @Field("fcm_token") String fcm_token, @Field("device_id") String device_id, @Field("device_type") String device_type, @Field("login_type") String login_type);
}
