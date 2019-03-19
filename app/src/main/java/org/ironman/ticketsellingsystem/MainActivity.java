package org.ironman.ticketsellingsystem;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import org.ironman.ticketsellingsystem.model.UserInfo;
import org.ironman.ticketsellingsystem.present.PMain;
import org.ironman.ticketsellingsystem.ui.activity.ForgetActivity;
import org.ironman.ticketsellingsystem.ui.activity.HomeActivity;
import org.ironman.ticketsellingsystem.ui.activity.RegisterActivity;
import org.ironman.ticketsellingsystem.util.CommonUtil;

import butterknife.BindView;
import cn.droidlover.xdroidmvp.cache.SharedPref;
import cn.droidlover.xdroidmvp.mvp.XActivity;
import cn.droidlover.xdroidmvp.router.Router;

/**
 * Created by Kaisa on 2018/1/10.
 */

public class MainActivity extends XActivity<PMain> implements View.OnClickListener {

    @BindView(R.id.tv_login)
    public TextView tvLogin;
    @BindView(R.id.content_back)
    TextView contentBack;
    @BindView(R.id.content_title)
    TextView contentTitle;
    @BindView(R.id.et_account)
    EditText etAccount;
    @BindView(R.id.et_password)
    EditText etPassword;
    @BindView(R.id.tv_rigister)
    TextView tvRigister;
    @BindView(R.id.tv_forget)
    TextView tvForget;
    private SharedPref sp;

    @Override
    public void initData(Bundle savedInstanceState) {
        sp = SharedPref.getInstance(context);
        tvLogin.setOnClickListener(this);
        tvRigister.setOnClickListener(this);
        tvForget.setOnClickListener(this);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    public PMain newP() {
        return new PMain();
    }

    @Override
    protected void oneLogin(String msg) {

    }

    public static void launch(Activity activity) {
        Router.newIntent(activity).to(MainActivity.class).launch();
    }

    public void doLogin(UserInfo model) {
        CommonUtil.showMsg("登录成功");
        UserInfo.DataBean mBean=model.getData();
        sp.putString("account", mBean.getAccount());
        sp.putString("password",mBean.getPassword());
        HomeActivity.launch(context);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_login:
                getP().doLogin(etAccount.getText().toString(), etPassword.getText().toString());
                break;
            case R.id.tv_rigister:
                RegisterActivity.launch(context);
                break;
            case R.id.tv_forget:
                ForgetActivity.launch(context);
                break;
        }
    }
}