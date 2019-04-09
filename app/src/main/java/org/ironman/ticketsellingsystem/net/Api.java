package org.ironman.ticketsellingsystem.net;

import org.ironman.ticketsellingsystem.app.MyUrls;

import cn.droidlover.xdroidmvp.net.XApi;

/**
 * Created by kaisa on 2017/12/31.
 */

public class Api {

    public static final String API_URL = MyUrls.WEB_ROOT;

    private static IndexService indexService;
    private static RegisterService registerService;
    private static ForgetService forgetService;
    public static IndexService getIndexService() {
        if (indexService == null) {
            synchronized (Api.class) {
                if (indexService == null) {
                    indexService = XApi.getInstance().getRetrofit(API_URL, true).create(IndexService.class);
                }
            }
        }
        return indexService;
    }


    public static RegisterService getRegisterService() {
        if (registerService == null) {
            synchronized (Api.class) {
                if (registerService == null) {
                    registerService = XApi.getInstance().getRetrofit(API_URL, true).create(RegisterService.class);
                }
            }
        }
        return registerService;
    }
    public static ForgetService getForgotService () {
        if (forgetService == null) {
            synchronized (Api.class) {
                if (forgetService == null) {
                    forgetService = XApi.getInstance().getRetrofit(API_URL, true).create(ForgetService.class);
                }
            }
        }
        return forgetService;
    }

}
