package org.ironman.ticketsellingsystem.present;

import org.ironman.ticketsellingsystem.model.ContentInfo;
import org.ironman.ticketsellingsystem.net.Api;
import org.ironman.ticketsellingsystem.ui.activity.ChangePasswordActivity;

import java.util.HashMap;

import cn.droidlover.xdroidmvp.mvp.XPresent;
import cn.droidlover.xdroidmvp.net.ApiSubscriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;

/**
 * Created by Archer on 2019/4/10.
 */

public class PChangPassword extends XPresent<ChangePasswordActivity> {
    public void doChange(String account, String password,String phone) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("account", account);
        map.put("password", password);
        map.put("phone", phone);
        Api.getForgotService().forgetPassword(map)
                .compose(XApi.<ContentInfo>getApiTransformer()) //统一异常处理
                .compose(XApi.<ContentInfo>getScheduler()) //线程调度
                .compose(getV().<ContentInfo>bindToLifecycle()) //内存泄漏处理
                .subscribe(new ApiSubscriber<ContentInfo>() {
                    @Override
                    public void onNext(ContentInfo data) {
//                        getV().data2view(data);
                    }

                    @Override
                    protected void onFail(NetError error) {
                        getV().showError(error);
                    }
                });
    }
}
