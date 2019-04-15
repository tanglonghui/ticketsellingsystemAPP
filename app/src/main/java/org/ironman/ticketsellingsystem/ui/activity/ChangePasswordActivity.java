package org.ironman.ticketsellingsystem.ui.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.ironman.ticketsellingsystem.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.droidlover.xdroidmvp.mvp.XActivity;
import cn.droidlover.xdroidmvp.router.Router;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Archer on 2019/3/17.
 */

public class ChangePasswordActivity extends XActivity implements View.OnClickListener {
    @BindView(R.id.content_back)
    TextView contentBack;
    @BindView(R.id.content_title)
    TextView contentTitle;
    @BindView(R.id.iv_head)
    CircleImageView ivHead;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_account)
    TextView tvAccount;
    @BindView(R.id.rl_head)
    RelativeLayout rlHead;
    @BindView(R.id.et_password)
    EditText etPassword;
    @BindView(R.id.et_reset_password)
    EditText etResetPassword;
    @BindView(R.id.et_check_password)
    EditText etCheckPassword;
    @BindView(R.id.tv_login)
    TextView tvLogin;

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.content_back:
                finish();
                break;
        }
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        contentTitle.setText("修改密码");
        contentBack.setVisibility(View.VISIBLE);
        contentBack.setOnClickListener(this);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_change_password;
    }

    @Override
    public Object newP() {
        return null;
    }

    @Override
    protected void oneLogin(String msg) {

    }

    public static void launch(Activity activity) {
        Router.newIntent(activity).to(ChangePasswordActivity.class).launch();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
