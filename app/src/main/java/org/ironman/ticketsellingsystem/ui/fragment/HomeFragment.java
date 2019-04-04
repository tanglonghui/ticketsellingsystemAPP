package org.ironman.ticketsellingsystem.ui.fragment;


import android.os.Bundle;

import org.ironman.ticketsellingsystem.R;

import cn.droidlover.xdroidmvp.mvp.XFragment;


public class HomeFragment extends XFragment{

    @Override
    public void initData(Bundle savedInstanceState) {

    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    public Object newP() {
        return null;
    }

    @Override
    protected void oneLogin(String msg) {

    }
}
