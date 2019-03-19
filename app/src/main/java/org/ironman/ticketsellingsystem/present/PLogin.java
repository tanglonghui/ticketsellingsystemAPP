//package org.ironman.ticketsellingsystem.present;
//
//
//import org.ironman.ticketsellingsystem.model.UserInfo;
//import org.ironman.ticketsellingsystem.net.Api;
//import org.ironman.ticketsellingsystem.ui.activity.LoginActivity;
//
//import cn.droidlover.xdroidmvp.mvp.XPresent;
//import cn.droidlover.xdroidmvp.net.ApiSubscriber;
//import cn.droidlover.xdroidmvp.net.NetError;
//import cn.droidlover.xdroidmvp.net.XApi;
//
///**
// * Created by kaisa on 2017/12/31.
// */
//
//public class PLogin extends XPresent<LoginActivity> {
//
//    public void doLogin(String mobile, String password) {
//        Api.getIndexService().login(mobile, password)
//                .compose(XApi.<UserInfo>getApiTransformer()) //统一异常处理
//                .compose(XApi.<UserInfo>getScheduler()) //线程调度
//                .compose(getV().<UserInfo>bindToLifecycle()) //内存泄漏处理
//                .subscribe(new ApiSubscriber<UserInfo>() {
//                    @Override
//                    public void onNext(UserInfo userEntityDataResults) {
//                        getV().doLogin(userEntityDataResults);
//                    }
//                    @Override
//                    protected void onFail(NetError error) {
//                        getV().showError(error);
//                    }
//                });
//    }
//
//}
