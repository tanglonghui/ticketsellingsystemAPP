package org.ironman.ticketsellingsystem.ui.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import org.ironman.ticketsellingsystem.R;

import cn.droidlover.xdroidmvp.mvp.XActivity;
import cn.droidlover.xdroidmvp.router.Router;

/**
 * Created by Archer on 2019/3/17.
 */

public class ForgetActivity  extends XActivity implements View.OnClickListener {
    @Override
    public void onClick(View v) {

    }

    @Override
    public void initData(Bundle savedInstanceState) {

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_forget;
    }

    @Override
    public Object  newP() {
        return null;
    }

    @Override
    protected void oneLogin(String msg) {

    }
    public static void launch(Activity activity) {
        Router.newIntent(activity).to(ForgetActivity.class).launch();
    }

}
