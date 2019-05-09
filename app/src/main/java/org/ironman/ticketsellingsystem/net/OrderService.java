package org.ironman.ticketsellingsystem.net;

import org.ironman.ticketsellingsystem.model.ContentInfo;
import org.ironman.ticketsellingsystem.model.PasengerInfo;

import java.util.Map;

import io.reactivex.Flowable;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by Archer on 2019/4/10.
 */

public interface OrderService {
    /**
     * 提交订单
     */
    @FormUrlEncoded
    @POST("/addOrder")
    Flowable<ContentInfo> addOrder(@FieldMap Map<String, Object> map);
    /**
     * 改变订单状态
     */
    @FormUrlEncoded
    @POST("/changOrderState")
    Flowable<ContentInfo> changOrderState(@FieldMap Map<String, Object> map);
    /**
     * 拿到旅客列表
     */
    @FormUrlEncoded
    @POST("/myPasengerList")
    Flowable<PasengerInfo> getPasenger(@FieldMap Map<String, Object> map);
}
