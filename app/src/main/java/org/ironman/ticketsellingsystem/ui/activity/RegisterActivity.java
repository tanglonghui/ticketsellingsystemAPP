package org.ironman.ticketsellingsystem.ui.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;
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
import org.ironman.ticketsellingsystem.present.PRegister;
import org.ironman.ticketsellingsystem.util.CommonUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.droidlover.xdroidmvp.cache.SharedPref;
import cn.droidlover.xdroidmvp.mvp.XActivity;
import cn.droidlover.xdroidmvp.router.Router;

public class RegisterActivity extends XActivity<PRegister> implements View.OnClickListener, Validator.ValidationListener {

    @BindView(R.id.content_back)
    TextView contentBack;
    @BindView(R.id.content_title)
    TextView contentTitle;
    @NotEmpty(message = "账号不能为空")
    @BindView(R.id.et_account)
    EditText etAccount;
    @NotEmpty(message = "姓名不能为空")
    @BindView(R.id.et_name)
    EditText etName;
    @Length(min = 11, max = 11, message = "手机号码为11位")
    @BindView(R.id.et_phone)
    EditText etPhone;
    @Password(min = 6, message = "密码最低6位")
    @BindView(R.id.et_password)
    EditText etPassword;
    @ConfirmPassword(message = "两次输入密码不一致")
    @BindView(R.id.et_check_password)
    EditText etCheckPassword;
    @BindView(R.id.sp_sex)
    Spinner spSex;
    @BindView(R.id.tv_register)
    TextView tvRegister;
    private Validator validator;

    private String sex = "男";

    @Override
    protected void oneLogin(String msg) {

    }

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
        contentTitle.setText("注册");
        if (Constans.isDebug){
            etAccount.setText("1");
            etPassword.setText("123456");
            etCheckPassword.setText("123456");
            etPhone.setText("12345678910");
            etName.setText("赵");
        }
        validator = new Validator(this);
        validator.setValidationListener(this);
        tvRegister.setOnClickListener(this);
        contentBack.setOnClickListener(this);
        spSex.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        sex = "男";
                        break;
                    case 1:
                        sex = "女";
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_register;
    }

    @Override
    public PRegister newP() {
        return new PRegister();
    }

    public static void launch(Activity activity) {
        Router.newIntent(activity).to(RegisterActivity.class).launch();
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
        getP().doRegister(
                etAccount.getText().toString(),
                etPassword.getText().toString(),
                etName.getText().toString(),
                sex,
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
            CommonUtil.showMsg("注册成功");
            SharedPref.getInstance(context).putString(Constans.ACCOUNT, etAccount.getText().toString());
            SharedPref.getInstance(context).putString(Constans.PASSWORD, etPassword.getText().toString());
            MainActivity.launch(this);
        } else {
            CommonUtil.showMsg(data.getMessage());
        }
    }

}
