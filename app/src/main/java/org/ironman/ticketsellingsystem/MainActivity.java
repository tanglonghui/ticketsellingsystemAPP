package org.ironman.ticketsellingsystem;

import android.os.Bundle;
import android.widget.TextView;

import org.ironman.ticketsellingsystem.model.DataResults;
import org.ironman.ticketsellingsystem.model.UserEntity;
import org.ironman.ticketsellingsystem.present.PMain;
import org.ironman.ticketsellingsystem.ui.activity.HomeActivity;
import org.ironman.ticketsellingsystem.ui.activity.LoginActivity;

import butterknife.BindView;
import cn.droidlover.xdroidmvp.cache.SharedPref;
import cn.droidlover.xdroidmvp.mvp.XActivity;

public class MainActivity extends XActivity<PMain> {

    @BindView(R.id.test)
    TextView textView;
    private SharedPref sp;

    @Override
    protected void oneLogin(String msg) {

    }

    @Override
    public void initData(Bundle savedInstanceState) {
        sp = SharedPref.getInstance(context);
        if (sp.get("account")!=null&&sp.get("password")!=null){
            getP().doLogin(sp.getString("account","").toString(), sp.getString("password","").toString());
        }else {
            LoginActivity.launch(context);
        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public PMain newP() {
        return new PMain();
    }

    public void doLogin(DataResults<UserEntity> model) {
        HomeActivity.launch(context);
    }
    public void doLogin() {
        LoginActivity.launch(context);
    }
}
