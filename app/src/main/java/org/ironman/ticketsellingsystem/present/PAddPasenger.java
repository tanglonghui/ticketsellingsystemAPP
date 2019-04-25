package org.ironman.ticketsellingsystem.present;

import org.ironman.ticketsellingsystem.model.ContentInfo;
import org.ironman.ticketsellingsystem.net.Api;
import org.ironman.ticketsellingsystem.ui.activity.AddPasengerActivity;

import java.util.HashMap;

import cn.droidlover.xdroidmvp.mvp.XPresent;
import cn.droidlover.xdroidmvp.net.ApiSubscriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;

/**
 * Created by Archer on 2019/4/6.
 */

public class PAddPasenger extends XPresent<AddPasengerActivity> {
    public void addPasenger(String account, String password, String name, String sex, String phone) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("account", account);
        map.put("password", password);
        map.put("name", name);
        map.put("sex", sex);
        map.put("phone", phone);
        Api.getPasengerService().addPasenger(map)
                .compose(XApi.<ContentInfo>getApiTransformer()) //统一异常处理
                .compose(XApi.<ContentInfo>getScheduler()) //线程调度
                .compose(getV().<ContentInfo>bindToLifecycle()) //内存泄漏处理
                .subscribe(new ApiSubscriber<ContentInfo>() {
                    @Override
                    public void onNext(ContentInfo data) {
                        getV().data2view(data);
                    }

                    @Override
                    protected void onFail(NetError error) {
                        getV().showError(error);
                    }
                });
    }
}
