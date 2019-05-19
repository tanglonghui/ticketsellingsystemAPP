package org.ironman.ticketsellingsystem.present;

import org.ironman.ticketsellingsystem.model.OrderInfo;
import org.ironman.ticketsellingsystem.net.Api;
import org.ironman.ticketsellingsystem.ui.fragment.OrderFragment;

import java.util.HashMap;

import cn.droidlover.xdroidmvp.mvp.XPresent;
import cn.droidlover.xdroidmvp.net.ApiSubscriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;

/**
 * Created by Archer on 2019/4/6.
 */

public class POrder extends XPresent<OrderFragment> {
    public void getOrder(Integer userId,String state) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("userId", userId);
        map.put("state", state);
        Api.getOrderService().getOrder(map)
                .compose(XApi.<OrderInfo>getApiTransformer()) //统一异常处理
                .compose(XApi.<OrderInfo>getScheduler()) //线程调度
                .compose(getV().<OrderInfo>bindToLifecycle()) //内存泄漏处理

                .subscribe(new ApiSubscriber<OrderInfo>() {
                    @Override
                    public void onNext(OrderInfo data) {
                        getV().data2view(data);
                    }

                    @Override
                    protected void onFail(NetError error) {
                        getV().showError(error);
                    }
                });
    }
}
