package org.ironman.ticketsellingsystem.event;

import cn.droidlover.xdroidmvp.event.IBus;

/**
 * Created by Administrator on 2019/4/15.
 */

public class ChoseStartPlaceEvent implements IBus.IEvent {
    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    private String msg;
    @Override
    public int getTag() {
        return 1;
    }
}
