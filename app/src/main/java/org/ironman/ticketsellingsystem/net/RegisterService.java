package org.ironman.ticketsellingsystem.net;

import org.ironman.ticketsellingsystem.model.ContentInfo;

import java.util.Map;

import io.reactivex.Flowable;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by Archer on 2019/4/6.
 */

public interface RegisterService {
    /**
     * 注册
     */
    @FormUrlEncoded
    @POST("/register")
    Flowable<ContentInfo> register(@FieldMap Map<String, Object> map);
}
