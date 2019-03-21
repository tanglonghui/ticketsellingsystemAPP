package org.ironman.ticketsellingsystem.net;

import org.ironman.ticketsellingsystem.model.BaseResults;

import java.util.Map;

import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * @author TangLongHui
 * @data created on 2019/3/12.
 * @describe TODO
 */

public interface LoginService {

    @FormUrlEncoded
    @POST("token")
    Observable<BaseResults> login(@FieldMap Map<String, Object> map);

    @FormUrlEncoded
    @POST("token")
    Observable<BaseResults> register(@FieldMap Map<String, Object> map);

}
