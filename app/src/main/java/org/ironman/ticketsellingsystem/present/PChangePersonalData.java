package org.ironman.ticketsellingsystem.present;


import org.ironman.ticketsellingsystem.model.ContentInfo;
import org.ironman.ticketsellingsystem.model.UserInfo;
import org.ironman.ticketsellingsystem.net.Api;
import org.ironman.ticketsellingsystem.ui.activity.ChangePersonalDataActivity;

import java.util.HashMap;

import cn.droidlover.xdroidmvp.mvp.XPresent;
import cn.droidlover.xdroidmvp.net.ApiSubscriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;

/**
 * Created by kaisa on 2017/12/31.
 */

public class PChangePersonalData extends XPresent<ChangePersonalDataActivity> {

    public void doLogin(String mobile, String password) {
        Api.getIndexService().login(mobile, password)
                .compose(XApi.<UserInfo>getApiTransformer()) //统一异常处理
                .compose(XApi.<UserInfo>getScheduler()) //线程调度
                .compose(getV().<UserInfo>bindToLifecycle()) //内存泄漏处理
                .subscribe(new ApiSubscriber<UserInfo>() {
                    @Override
                    public void onNext(UserInfo userEntityDataResults) {
                        getV().doLogin(userEntityDataResults);
                    }
                    @Override
                    protected void onFail(NetError error) {
                        getV().showError(error);
                    }
                });
    }
    public void changePersonalData(Integer id,String age, String idCard, String name, String sex, String phone) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("id", id);
        map.put("age", age);
        map.put("idCard", idCard);
        map.put("name", name);
        map.put("sex", sex);
        map.put("phone", phone);
        Api.getIndexService().changePersonalData(map)
                .compose(XApi.<ContentInfo>getApiTransformer()) //统一异常处理
                .compose(XApi.<ContentInfo>getScheduler()) //线程调度
                .compose(getV().<ContentInfo>bindToLifecycle()) //内存泄漏处理
                .subscribe(new ApiSubscriber<ContentInfo>() {
                    @Override
                    public void onNext(ContentInfo userEntityDataResults) {
                        getV().data2view(userEntityDataResults);
                    }
                    @Override
                    protected void onFail(NetError error) {
                        getV().showError(error);
                    }
                });
    }

}
