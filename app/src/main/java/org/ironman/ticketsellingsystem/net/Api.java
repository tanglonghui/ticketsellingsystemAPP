package org.ironman.ticketsellingsystem.net;

import org.ironman.ticketsellingsystem.app.Constans;

/**
 * Created by kaisa on 2017/12/31.
 */

public class Api {

    public static final String API_URL = Constans.WEB_ROOT + "/api/";

/*

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

*/


}
