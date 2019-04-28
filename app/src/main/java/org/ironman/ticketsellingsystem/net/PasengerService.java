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

public interface PasengerService {
    /**
     * 删除旅客
     */
    @FormUrlEncoded
    @POST("/removePasenger")
    Flowable<ContentInfo> deletePasenger(@FieldMap Map<String, Object> map);
    /**
     * 添加旅客
     */
    @FormUrlEncoded
    @POST("/addPasenger")
    Flowable<ContentInfo> addPasenger(@FieldMap Map<String, Object> map);
    /**
     * 拿到旅客列表
     */
    @FormUrlEncoded
    @POST("/myPasengerList")
    Flowable<PasengerInfo> getPasenger(@FieldMap Map<String, Object> map);
}
