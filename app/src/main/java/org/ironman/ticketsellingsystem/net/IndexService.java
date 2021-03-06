package org.ironman.ticketsellingsystem.net;


import org.ironman.ticketsellingsystem.model.ContentInfo;
import org.ironman.ticketsellingsystem.model.UserInfo;

import java.util.Map;

import io.reactivex.Flowable;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * 基本接口
 * Created by kaisa on 2017/12/31.
 */

public interface IndexService {

    /**
     * 登录
     */
    @FormUrlEncoded
    @POST("/login")
    Flowable<UserInfo> login(@Field("account") String mobile,
                             @Field("password") String password);
    /**
     * 登录
     */
    @FormUrlEncoded
    @POST("/updateUser")
    Flowable<ContentInfo> changePersonalData(@FieldMap Map<String, Object> map);

}
