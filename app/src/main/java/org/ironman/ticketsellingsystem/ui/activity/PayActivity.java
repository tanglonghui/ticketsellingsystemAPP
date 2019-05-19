package org.ironman.ticketsellingsystem.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.ironman.ticketsellingsystem.R;
import org.ironman.ticketsellingsystem.event.OrderFragmentReflashEvent;
import org.ironman.ticketsellingsystem.model.ContentInfo;
import org.ironman.ticketsellingsystem.present.PPay;
import org.ironman.ticketsellingsystem.util.CommonUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.droidlover.xdroidmvp.event.BusProvider;
import cn.droidlover.xdroidmvp.mvp.XActivity;

public class PayActivity extends XActivity<PPay> {


    @BindView(R.id.tv_wait)
    TextView tvWait;
    @BindView(R.id.tv_pay)
    TextView tvPay;
    @BindView(R.id.tv_cancel)
    TextView tvCancel;
    @BindView(R.id.content_back)
    TextView contentBack;
    @BindView(R.id.content_title)
    TextView contentTitle;
Integer id;
    @Override
    protected void oneLogin(String msg) {

    }

    @Override
    public void initData(Bundle savedInstanceState) {
        contentTitle.setText("支付");
        id=getIntent().getIntExtra("orderId",0);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_pay;
    }

    @Override
    public PPay newP() {
        return new  PPay();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.tv_wait, R.id.tv_pay, R.id.tv_cancel})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_wait:
                finish();
                break;
            case R.id.tv_pay:
                getP().changOrder(id,"1");
                break;
            case R.id.tv_cancel:
                getP().changOrder(id,"2");
                break;
        }
    }

    @OnClick(R.id.content_back)
    public void onViewClicked() {
        finish();
    }

    public void data2view(ContentInfo data) {
        if (data.isSuccess()) {
            OrderFragmentReflashEvent event=new OrderFragmentReflashEvent();
            BusProvider.getBus().post(event);
           finish();
        } else {
            CommonUtil.showMsg(data.getMessage());
        }
    }
}
