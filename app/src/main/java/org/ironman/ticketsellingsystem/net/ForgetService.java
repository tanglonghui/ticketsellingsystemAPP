package org.ironman.ticketsellingsystem.net;

import org.ironman.ticketsellingsystem.model.ContentInfo;

import java.util.Map;

import io.reactivex.Flowable;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by Archer on 2019/4/10.
 */

public interface ForgetService {
    /**
     * 忘记密码
     */
    @FormUrlEncoded
    @POST("/forgetPassword")
    Flowable<ContentInfo> forgetPassword(@FieldMap Map<String, Object> map);
}
