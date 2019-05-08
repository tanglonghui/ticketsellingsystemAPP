package org.ironman.ticketsellingsystem.present;

import org.ironman.ticketsellingsystem.model.ContentInfo;
import org.ironman.ticketsellingsystem.model.PasengerInfo;
import org.ironman.ticketsellingsystem.net.Api;
import org.ironman.ticketsellingsystem.ui.activity.ChosePasengerActivity;
import org.ironman.ticketsellingsystem.ui.activity.MyPasengerActivity;

import java.util.HashMap;

import cn.droidlover.xdroidmvp.mvp.XPresent;
import cn.droidlover.xdroidmvp.net.ApiSubscriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;

/**
 * Created by Archer on 2019/4/6.
 */

public class PChosePasenger extends XPresent<ChosePasengerActivity> {
    public void getPasenger(Integer userId) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("userId", userId);
        Api.getPasengerService().getPasenger(map)
                .compose(XApi.<PasengerInfo>getApiTransformer()) //统一异常处理
                .compose(XApi.<PasengerInfo>getScheduler()) //线程调度
                .compose(getV().<PasengerInfo>bindToLifecycle()) //内存泄漏处理

                .subscribe(new ApiSubscriber<PasengerInfo>() {
                    @Override
                    public void onNext(PasengerInfo data) {
                        getV().data2view(data);
                    }

                    @Override
                    protected void onFail(NetError error) {
                        getV().showError(error);
                    }
                });
    }
}
