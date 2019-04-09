package org.ironman.ticketsellingsystem.ui.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.ConfirmPassword;
import com.mobsandgeeks.saripaar.annotation.Length;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;
import com.mobsandgeeks.saripaar.annotation.Password;

import org.ironman.ticketsellingsystem.MainActivity;
import org.ironman.ticketsellingsystem.R;
import org.ironman.ticketsellingsystem.app.Constans;
import org.ironman.ticketsellingsystem.model.ContentInfo;
import org.ironman.ticketsellingsystem.present.PForget;
import org.ironman.ticketsellingsystem.util.CommonUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.droidlover.xdroidmvp.cache.SharedPref;
import cn.droidlover.xdroidmvp.mvp.XActivity;
import cn.droidlover.xdroidmvp.router.Router;

/**
 * Created by Archer on 2019/3/17.
 */

public class ForgetActivity extends XActivity<PForget> implements View.OnClickListener, Validator.ValidationListener {
    @BindView(R.id.content_back)
    TextView contentBack;
    @BindView(R.id.content_title)
    TextView contentTitle;
    @NotEmpty(message = "账号不能为空")
    @BindView(R.id.et_account)
    EditText etAccount;
    @Length(min = 11, max = 11, message = "手机号码为11位")
    @BindView(R.id.et_phone)
    EditText etPhone;
    @Password(min = 6, message = "密码最低6位")
    @BindView(R.id.et_password)
    EditText etPassword;
    @ConfirmPassword(message = "两次输入密码不一致")
    @BindView(R.id.et_check_password)
    EditText etCheckPassword;
    @BindView(R.id.tv_register)
    TextView tvRegister;
    private Validator validator;
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_register:
                validator.validate();
                break;
            case R.id.content_back:
                finish();
                break;
        }
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        contentTitle.setText("忘记密码");
        validator = new Validator(this);
        validator.setValidationListener(this);
        tvRegister.setOnClickListener(this);
        contentBack.setOnClickListener(this);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_forget;
    }

    @Override
    public PForget newP() {
        return new PForget();
    }

    @Override
    protected void oneLogin(String msg) {

    }

    public static void launch(Activity activity) {
        Router.newIntent(activity).to(ForgetActivity.class).launch();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
    // Code…

    @Override
    public void onValidationSucceeded() {
        //表单验证成功，请求网络
        getP().doForgot(
                etAccount.getText().toString(),
                etPassword.getText().toString(),
                etPhone.getText().toString());
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
            SharedPref.getInstance(context).putString(Constans.ACCOUNT, etAccount.getText().toString());
            SharedPref.getInstance(context).putString(Constans.PASSWORD, etPassword.getText().toString());
            MainActivity.launch(this);
        } else {
            CommonUtil.showMsg(data.getMessage());
        }
    }
}
