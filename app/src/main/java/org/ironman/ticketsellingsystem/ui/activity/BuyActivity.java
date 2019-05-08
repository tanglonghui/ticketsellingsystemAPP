package org.ironman.ticketsellingsystem.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jcodecraeer.xrecyclerview.XRecyclerView;

import org.ironman.ticketsellingsystem.R;
import org.ironman.ticketsellingsystem.adapter.MyPasengerAdapter;
import org.ironman.ticketsellingsystem.model.TrainInfo;

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
    @BindView(R.id.xv_recycler)
    XRecyclerView xvRecycler;

    TrainInfo.ListEntity bean;
    private String state;
    private MyPasengerAdapter adapter;

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
                break;
            case R.id.tv_chose:
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

        adapter = new MyPasengerAdapter(this);
        adapter.setListener(new MyPasengerAdapter.ItemOnclickListener() {
            @Override
            public void OnClickListener(int position, Integer id, Integer pasengerId) {

            }
        });
        xvRecycler.setLayoutManager(new LinearLayoutManager(this));
        xvRecycler.setLoadingMoreEnabled(false);
        xvRecycler.setPullRefreshEnabled(false);
        xvRecycler.setAdapter(adapter);
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
}
