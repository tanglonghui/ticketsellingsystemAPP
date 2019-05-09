package org.ironman.ticketsellingsystem.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.ironman.ticketsellingsystem.R;
import org.ironman.ticketsellingsystem.app.Constans;
import org.ironman.ticketsellingsystem.model.PasengerInfo;
import org.ironman.ticketsellingsystem.model.TrainInfo;
import org.ironman.ticketsellingsystem.util.CommonUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.droidlover.xdroidmvp.log.XLog;
import cn.droidlover.xdroidmvp.mvp.XActivity;

public class BuyActivity extends XActivity implements View.OnClickListener {


    @BindView(R.id.content_back)
    TextView contentBack;
    @BindView(R.id.content_title)
    TextView contentTitle;
    @BindView(R.id.tv_train_card)
    TextView tvTrainCard;
    @BindView(R.id.tv_start_place)
    TextView tvStartPlace;
    @BindView(R.id.tv_start_time)
    TextView tvStartTime;
    @BindView(R.id.tv_end_place)
    TextView tvEndPlace;
    @BindView(R.id.tv_end_time)
    TextView tvEndTime;
    @BindView(R.id.tv_business)
    TextView tvBusiness;
    @BindView(R.id.tv_business_price)
    TextView tvBusinessPrice;
    @BindView(R.id.tv_first)
    TextView tvFirst;
    @BindView(R.id.tv_first_price)
    TextView tvFirstPrice;
    @BindView(R.id.tv_second)
    TextView tvSecond;
    @BindView(R.id.tv_second_price)
    TextView tvSecondPrice;
    @BindView(R.id.tv_chose)
    TextView tvChose;
    @BindView(R.id.tv_submit)
    TextView tvSubmit;
    @BindView(R.id.lv_business)
    LinearLayout lvBusiness;
    @BindView(R.id.lv_first)
    LinearLayout lvFirst;
    @BindView(R.id.lv_second)
    LinearLayout lvSecond;


    TrainInfo.ListEntity bean;
    PasengerInfo.ListBean pasengerBean;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_type)
    TextView tvType;
    @BindView(R.id.tv_card)
    TextView tvCard;
    @BindView(R.id.lv_pasenger)
    LinearLayout lvPasenger;
    private String state;

    @Override
    protected void oneLogin(String msg) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.content_back:
                finish();
                break;
            case R.id.tv_submit:
                if (pasengerBean == null) {
                    CommonUtil.showMsg("请选择旅客");
                } else {

                }
                break;
            case R.id.tv_chose:
                Intent intent = new Intent(BuyActivity.this, ChosePasengerActivity.class);
                startActivityForResult(intent, Constans.REQ_CHOSE_PASENGER);
                break;
        }
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        contentBack.setOnClickListener(this);
        tvSubmit.setOnClickListener(this);
        tvChose.setOnClickListener(this);
        bean = (TrainInfo.ListEntity) getIntent().getSerializableExtra("TrainInfoBean");
        XLog.e("" + bean.getId());
        tvTrainCard.setText(bean.getTrainCard());
        tvStartPlace.setText(bean.getStartPlace());
        tvEndPlace.setText(bean.getEndPlace());
        tvStartTime.setText(bean.getStartTime());
        tvEndTime.setText(bean.getEndTime());
        tvBusiness.setText(bean.getBusinessSeat() + "张");
        tvBusinessPrice.setText("" + bean.getBusinessPrice() + "￥");
        tvFirst.setText(bean.getFirstSeat() + "张");
        tvFirstPrice.setText("" + bean.getFirstSeatPrice() + "￥");
        tvSecond.setText(bean.getSecondSeat() + "张");
        tvSecondPrice.setText("" + bean.getSecondSeatPrice() + "￥");
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_buy;
    }

    @Override
    public Object newP() {
        return null;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.lv_business, R.id.lv_first, R.id.lv_second})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.lv_business:
                state = "1";
                lvBusiness.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                lvFirst.setBackgroundColor(getResources().getColor(R.color.app_bg));
                lvSecond.setBackgroundColor(getResources().getColor(R.color.app_bg));
                break;
            case R.id.lv_first:
                state = "2";
                lvBusiness.setBackgroundColor(getResources().getColor(R.color.app_bg));
                lvFirst.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                lvSecond.setBackgroundColor(getResources().getColor(R.color.app_bg));
                break;
            case R.id.lv_second:
                state = "3";
                lvBusiness.setBackgroundColor(getResources().getColor(R.color.app_bg));
                lvFirst.setBackgroundColor(getResources().getColor(R.color.app_bg));
                lvSecond.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Constans.REQ_CHOSE_PASENGER && resultCode == RESULT_OK) {
            pasengerBean = (PasengerInfo.ListBean) data.getSerializableExtra("pasengerBean");
            tvName.setText(pasengerBean.getName());
            tvType.setText(pasengerBean.getType());
            tvCard.setText(pasengerBean.getPhone());
            lvPasenger.setVisibility(View.VISIBLE);
            tvChose.setText("更换乘客");
        } else {
            XLog.e("异常");
        }
    }
}
