package org.ironman.ticketsellingsystem.present;

import org.ironman.ticketsellingsystem.model.TrainInfo;
import org.ironman.ticketsellingsystem.net.Api;
import org.ironman.ticketsellingsystem.ui.activity.TrainListActivity;

import java.util.HashMap;

import cn.droidlover.xdroidmvp.mvp.XPresent;
import cn.droidlover.xdroidmvp.net.ApiSubscriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;

/**
 * Created by Archer on 2019/4/6.
 */

public class PTrainList extends XPresent<TrainListActivity> {
    public void getTrainList(String startPlace, String endPlace, String date, String isFast) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("startPlace", startPlace);
        map.put("endPlace", endPlace);
        map.put("trainTime", date);
        if (!isFast.isEmpty()){
            map.put("TrainCard", isFast);
        }
        Api.getTrainListService().getTrainList(map)
                .compose(XApi.<TrainInfo>getApiTransformer()) //统一异常处理
                .compose(XApi.<TrainInfo>getScheduler()) //线程调度
                .compose(getV().<TrainInfo>bindToLifecycle()) //内存泄漏处理
                .subscribe(new ApiSubscriber<TrainInfo>() {
                    @Override
                    public void onNext(TrainInfo data) {
                        getV().data2view(data);
                    }

                    @Override
                    protected void onFail(NetError error) {
                        getV().showError(error);
                    }
                });
    }
}
