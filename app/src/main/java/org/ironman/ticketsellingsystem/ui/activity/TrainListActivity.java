package org.ironman.ticketsellingsystem.ui.activity;

import android.os.Bundle;

import org.ironman.ticketsellingsystem.R;

import cn.droidlover.xdroidmvp.mvp.XActivity;

public class TrainListActivity extends XActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void oneLogin(String msg) {

    }

    @Override
    public void initData(Bundle savedInstanceState) {

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_train_list;
    }

    @Override
    public Object newP() {
        return null;
    }
}
