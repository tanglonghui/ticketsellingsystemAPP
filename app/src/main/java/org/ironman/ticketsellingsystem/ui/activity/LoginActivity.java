package org.ironman.ticketsellingsystem.ui.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import org.ironman.ticketsellingsystem.R;
import org.ironman.ticketsellingsystem.model.DataResults;
import org.ironman.ticketsellingsystem.model.UserEntity;
import org.ironman.ticketsellingsystem.present.PLogin;
import org.ironman.ticketsellingsystem.util.CommonUtil;

import butterknife.BindView;
import cn.droidlover.xdroidmvp.cache.SharedPref;
import cn.droidlover.xdroidmvp.mvp.XActivity;
import cn.droidlover.xdroidmvp.router.Router;

/**
 * Created by Kaisa on 2018/1/10.
 */

public class LoginActivity extends XActivity<PLogin> implements View.OnClickListener {

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
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    public PLogin newP() {
        return new PLogin();
    }

    @Override
    protected void oneLogin(String msg) {

    }

    public static void launch(Activity activity) {
        Router.newIntent(activity).to(LoginActivity.class).launch();
    }

    public void doLogin(DataResults<UserEntity> model) {
        CommonUtil.showMsg("登录成功");
        sp.putString("account", model.getT().getAccount());
        sp.putString("password", model.getT().getPassword());
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

                break;
        }
    }
}