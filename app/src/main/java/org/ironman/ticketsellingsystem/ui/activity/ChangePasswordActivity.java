package org.ironman.ticketsellingsystem.ui.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.ConfirmPassword;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;
import com.mobsandgeeks.saripaar.annotation.Password;

import org.ironman.ticketsellingsystem.R;
import org.ironman.ticketsellingsystem.app.Constans;
import org.ironman.ticketsellingsystem.model.ContentInfo;
import org.ironman.ticketsellingsystem.present.PChangPassword;
import org.ironman.ticketsellingsystem.util.CommonUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.droidlover.xdroidmvp.cache.SharedPref;
import cn.droidlover.xdroidmvp.mvp.XActivity;
import cn.droidlover.xdroidmvp.router.Router;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Archer on 2019/3/17.
 */

public class ChangePasswordActivity extends XActivity<PChangPassword> implements View.OnClickListener, Validator.ValidationListener {
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
    @NotEmpty(message = "原密码不能为空")
    @BindView(R.id.et_password)
    EditText etPassword;
    @Password(min = 6, message = "密码最低6位")
    @BindView(R.id.et_reset_password)
    @ConfirmPassword(message = "两次输入密码不一致")
    EditText etResetPassword;
    @BindView(R.id.et_check_password)
    EditText etCheckPassword;
    @BindView(R.id.tv_login)
    TextView tvLogin;//提交按钮
    private Validator validator;

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.content_back:
                finish();
                break;
            case R.id.tv_login:
                validator.validate();
                break;
        }
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        contentTitle.setText("修改密码");
        contentBack.setVisibility(View.VISIBLE);
        contentBack.setOnClickListener(this);
        validator = new Validator(this);
        validator.setValidationListener(this);
        tvLogin.setOnClickListener(this);

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_change_password;
    }

    @Override
    public PChangPassword newP() {
        return new PChangPassword();
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

    @Override
    public void onValidationSucceeded() {
        //表单验证成功，请求网络
        getP().doChange(
                SharedPref.getInstance(context).getString(Constans.ACCOUNT, ""),
                etResetPassword.getText().toString(),
                SharedPref.getInstance(context).getString(Constans.PHONE, ""));
    }

    @Override
    public void onValidationFailed(List<ValidationError> errors) {
        for (ValidationError error : errors) {
            View view = error.getView();
            String message = error.getCollatedErrorMessage(this);
            // Display error messages ;)
            if (view instanceof EditText) {
                ((EditText) view).setError(message);
            } else {
                CommonUtil.showMsg(message);
            }
        }
    }

    public void data2view(ContentInfo data) {
        if (data.isSuccess()) {
            CommonUtil.showMsg("修改成功");
            SharedPref.getInstance(context).putString(Constans.PASSWORD, etPassword.getText().toString());
        } else {
            CommonUtil.showMsg(data.getMessage());
        }
    }
}
