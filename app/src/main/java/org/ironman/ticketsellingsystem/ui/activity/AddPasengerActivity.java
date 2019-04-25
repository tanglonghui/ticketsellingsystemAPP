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
import com.mobsandgeeks.saripaar.annotation.NotEmpty;

import org.ironman.ticketsellingsystem.R;
import org.ironman.ticketsellingsystem.model.ContentInfo;
import org.ironman.ticketsellingsystem.util.CommonUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.droidlover.xdroidmvp.mvp.XActivity;
import cn.droidlover.xdroidmvp.router.Router;

public class AddPasengerActivity extends XActivity implements View.OnClickListener , Validator.ValidationListener {


    @BindView(R.id.content_back)
    TextView contentBack;
    @BindView(R.id.content_title)
    TextView contentTitle;
    @BindView(R.id.et_name)
    @NotEmpty(message = "姓名不能为空")
    EditText etName;
    @BindView(R.id.et_phone)
    @NotEmpty(message = "电话不能为空")
    EditText etPhone;
    @BindView(R.id.et_card)
    @NotEmpty(message = "证件号不能为空")
    EditText etCard;
    @BindView(R.id.sp_type)
    Spinner spType;
    @BindView(R.id.tv_add)
    TextView tvAdd;
    private String type;
    private Validator validator;


    @Override
    protected void oneLogin(String msg) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.content_back:
                finish();
                break;
            case R.id.content_add:
                //添加旅客
                break;
            case R.id.tv_add:
                //添加旅客
                validator.validate();
                break;

        }
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        contentTitle.setText("添加旅客");
        tvAdd.setOnClickListener(this);
        contentBack.setOnClickListener(this);
        spType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        type = "成人";
                        break;
                    case 1:
                        type = "学生";
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        validator = new Validator(this);
        validator.setValidationListener(this);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_add_pasenger;
    }

    @Override
    public Object newP() {
        return null;
    }

    public static void launch(Activity activity) {
        Router.newIntent(activity).to(AddPasengerActivity.class).launch();
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
//        getP().doRegister(
//                etAccount.getText().toString(),
//                etPassword.getText().toString(),
//                etName.getText().toString(),
//                sex,
//                etPhone.getText().toString());
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
            CommonUtil.showMsg("添加成功");
        } else {
            CommonUtil.showMsg(data.getMessage());
        }
    }
}
