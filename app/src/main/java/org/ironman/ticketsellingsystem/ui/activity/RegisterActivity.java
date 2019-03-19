package org.ironman.ticketsellingsystem.ui.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import org.ironman.ticketsellingsystem.R;

import cn.droidlover.xdroidmvp.mvp.XActivity;
import cn.droidlover.xdroidmvp.router.Router;

public class RegisterActivity extends XActivity implements View.OnClickListener {

    @Override
    protected void oneLogin(String msg) {

    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void initData(Bundle savedInstanceState) {

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_register;
    }

    @Override
    public Object newP() {
        return null;
    }
    public static void launch(Activity activity) {
        Router.newIntent(activity).to(RegisterActivity.class).launch();
    }

}
