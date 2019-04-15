package org.ironman.ticketsellingsystem.event;

import org.ironman.ticketsellingsystem.app.Constans;

import cn.droidlover.xdroidmvp.event.IBus;

/**
 * Created by Administrator on 2019/4/15.
 */

public class ChosePlaceEvent implements IBus.IEvent {
    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    private String msg;


    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    private String state;
    @Override
    public int getTag() {
        return Constans.CHOSE_PLACE_EVENT;
    }
}
