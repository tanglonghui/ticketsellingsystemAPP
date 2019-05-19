package org.ironman.ticketsellingsystem.ui.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.ironman.ticketsellingsystem.R;
import org.ironman.ticketsellingsystem.app.Constans;
import org.ironman.ticketsellingsystem.event.MyFragmentReflashEvent;
import org.ironman.ticketsellingsystem.model.ContentInfo;
import org.ironman.ticketsellingsystem.model.UserInfo;
import org.ironman.ticketsellingsystem.present.PChangePersonalData;
import org.ironman.ticketsellingsystem.util.CommonUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.droidlover.xdroidmvp.cache.SharedPref;
import cn.droidlover.xdroidmvp.event.BusProvider;
import cn.droidlover.xdroidmvp.mvp.XActivity;
import cn.droidlover.xdroidmvp.router.Router;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Archer on 2019/3/17.
 */

public class ChangePersonalDataActivity extends XActivity<PChangePersonalData> implements View.OnClickListener {
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
    @BindView(R.id.et_name)
    EditText etName;
    @BindView(R.id.et_age)
    EditText etAge;
    @BindView(R.id.et_phone)
    EditText etPhone;
    @BindView(R.id.et_id_card)
    EditText etIdCard;
    @BindView(R.id.tv_sex)
    TextView tvSex;
    @BindView(R.id.tv_save)
    TextView tvSave;
    private SharedPref sp;
    private String sex;

    @Override
    public void onClick(View v) {

    }

    @Override
    public void initData(Bundle savedInstanceState) {
        contentBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        tvSex.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showListDialog();
            }
        });
        contentTitle.setText("个人资料");
        sp = SharedPref.getInstance(context);
        getP().doLogin(sp.getString(Constans.ACCOUNT, ""), sp.getString(Constans.PASSWORD, ""));
    }

    @Override
    public int getLayoutId() {

        return R.layout.activity_change_personal_data;
    }

    @Override
    public PChangePersonalData newP() {
        return new PChangePersonalData();
    }

    @Override
    protected void oneLogin(String msg) {

    }

    public static void launch(Activity activity) {
        Router.newIntent(activity).to(ChangePersonalDataActivity.class).launch();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick(R.id.tv_save)
    public void onViewClicked() {
        getP().changePersonalData(
                SharedPref.getInstance(this).getInt(Constans.ID, 0),
                etAge.getText().toString(),
                etIdCard.getText().toString(),
                etName.getText().toString(),
                sex,
                etPhone.getText().toString());
    }

    public void doLogin(UserInfo model) {
        if (model.isSuccess()) {
            UserInfo.DataBean mBean = model.getData();
            sp.putInt(Constans.ID, mBean.getId());
            sp.putString(Constans.ACCOUNT, mBean.getAccount());
            sp.putString(Constans.PASSWORD, mBean.getPassword());
            sp.putString(Constans.NAME, mBean.getName());
            sp.putString(Constans.AGE, mBean.getAge());
            sp.putString(Constans.SEX, mBean.getSex());
            sp.putString(Constans.ID_CARD, mBean.getIdCard());
            sp.putString(Constans.ID_CARD_TYPE, mBean.getIdCardType());
            sp.putString(Constans.PHONE, mBean.getPhone());
            sp.putString(Constans.STATE, mBean.getState());
            tvName.setText(mBean.getName());
            tvAccount.setText("账号：" + mBean.getAccount());
            etAge.setText(mBean.getAge());
            etIdCard.setText(mBean.getIdCard());
            etName.setText(mBean.getName());
            etPhone.setText(mBean.getPhone());
            tvSex.setText(mBean.getSex());
            sex=mBean.getSex();
        } else {
            CommonUtil.showMsg("error");
        }

    }

    private void showListDialog() {
        final String[] items = {"男", "女"};
        AlertDialog.Builder listDialog =
                new AlertDialog.Builder(ChangePersonalDataActivity.this);
        listDialog.setTitle("选择");
        listDialog.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // which 下标从0开始
                // ...To-do
                switch (which) {
                    case 0:
                        sex = "男";
                        tvSex.setText(sex);
                        break;
                    case 1:
                        sex = "女";
                        tvSex.setText(sex);
                        break;
                }
            }
        });
        listDialog.show();
    }
    public void data2view(ContentInfo data) {
        if (data.isSuccess()) {
            CommonUtil.showMsg("修改成功");
            getP().doLogin(sp.getString(Constans.ACCOUNT, ""), sp.getString(Constans.PASSWORD, ""));
            MyFragmentReflashEvent event=new MyFragmentReflashEvent();
            BusProvider.getBus().post(event);
        } else {
            CommonUtil.showMsg(data.getMessage());
        }
    }
}
