package org.ironman.ticketsellingsystem.ui.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jcodecraeer.xrecyclerview.XRecyclerView;

import org.ironman.ticketsellingsystem.R;
import org.ironman.ticketsellingsystem.adapter.OrderAdapter;
import org.ironman.ticketsellingsystem.app.Constans;
import org.ironman.ticketsellingsystem.event.OrderFragmentReflashEvent;
import org.ironman.ticketsellingsystem.model.OrderInfo;
import org.ironman.ticketsellingsystem.present.POrder;
import org.ironman.ticketsellingsystem.ui.activity.PayActivity;
import org.ironman.ticketsellingsystem.util.CommonUtil;

import java.util.function.Consumer;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.droidlover.xdroidmvp.cache.SharedPref;
import cn.droidlover.xdroidmvp.event.BusProvider;
import cn.droidlover.xdroidmvp.log.XLog;
import cn.droidlover.xdroidmvp.mvp.XFragment;
import cn.droidlover.xdroidmvp.net.NetError;

/**
 * @data Created by Archer on 2019/4/18.
 * @describe TODO : 订单展示列表
 */


public class OrderFragment extends XFragment<POrder> implements View.OnClickListener {

    @BindView(R.id.tv_unpaid)
    TextView tvUnpaid;
    @BindView(R.id.tv_no_trip)
    TextView tvNoTrip;
    @BindView(R.id.tv_historical_trip)
    TextView tvHistoricalTrip;
    @BindView(R.id.xv_recycler)
    XRecyclerView xvRecycler;
    Unbinder unbinder;

    OrderAdapter adapter;
    @BindView(R.id.tv_order_time_tip)
    TextView tvOrderTimeTip;
    private String state;
    Integer id;

    @Override
    protected void oneLogin(String msg) {

    }

    @Override
    public void initData(Bundle savedInstanceState) {
        tvUnpaid.setOnClickListener(this);
        tvNoTrip.setOnClickListener(this);
        tvHistoricalTrip.setOnClickListener(this);
        tvUnpaid.setTextColor(getResources().getColor(R.color.x_red));
        tvNoTrip.setTextColor(getResources().getColor(R.color.white));
        tvHistoricalTrip.setTextColor(getResources().getColor(R.color.white));
        adapter = new OrderAdapter(getActivity());
        xvRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        xvRecycler.setAdapter(adapter);
        state = "0";
        id = SharedPref.getInstance(getContext()).getInt(Constans.ID, 0);
//        getP().getOrder(id, state);
        tvOrderTimeTip.setVisibility(View.VISIBLE);
        xvRecycler.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                getP().getOrder(id, state);
            }

            @Override
            public void onLoadMore() {

            }
        });
        xvRecycler.setLoadingMoreEnabled(false);
        adapter.setListener(new OrderAdapter.ItemOnclickListener() {
            @Override
            public void OnClickListener(OrderInfo.ListEntity bean) {
                Intent intent=new Intent(getContext(),PayActivity.class);
                intent.putExtra("orderId",bean.getId());
                XLog.e(""+bean.getId());
                startActivity(intent);
            }
        });
        BusProvider.getBus()
                .toFlowable(OrderFragmentReflashEvent.class)
                .subscribe(new io.reactivex.functions.Consumer<OrderFragmentReflashEvent>() {
                    @Override
                    public void accept(OrderFragmentReflashEvent orderFragmentReflashEvent) throws Exception {
                        getP().getOrder(id, state);
                    }
                });

    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_order;
    }

    @Override
    public POrder newP() {
        return new POrder();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_unpaid:
                state = "0";
                tvUnpaid.setTextColor(getResources().getColor(R.color.x_red));
                tvNoTrip.setTextColor(getResources().getColor(R.color.white));
                tvHistoricalTrip.setTextColor(getResources().getColor(R.color.white));
                getP().getOrder(id, state);
                tvOrderTimeTip.setVisibility(View.VISIBLE);
                break;
            case R.id.tv_no_trip:
                state = "1";
                tvUnpaid.setTextColor(getResources().getColor(R.color.white));
                tvNoTrip.setTextColor(getResources().getColor(R.color.x_red));
                tvHistoricalTrip.setTextColor(getResources().getColor(R.color.white));
                getP().getOrder(id, state);
                tvOrderTimeTip.setVisibility(View.GONE);
                break;
            case R.id.tv_historical_trip:
                state = "2";
                tvUnpaid.setTextColor(getResources().getColor(R.color.white));
                tvNoTrip.setTextColor(getResources().getColor(R.color.white));
                tvHistoricalTrip.setTextColor(getResources().getColor(R.color.x_red));
                getP().getOrder(id, state);
                tvOrderTimeTip.setVisibility(View.GONE);
                break;
        }
    }

    public void data2view(OrderInfo data) {
        xvRecycler.refreshComplete();
        if (data.isSuccess()) {
            adapter.setData(data.getList());
        } else {
            CommonUtil.showMsg(data.getMessage());
        }
    }
    public void cancelLoading(){
        xvRecycler.refreshComplete();
        xvRecycler.loadMoreComplete();
    }
    @Override
    public void showError(NetError error) {
        super.showError(error);
        cancelLoading();
    }

    @Override
    public void onResume() {
        super.onResume();
        getP().getOrder(id, state);
    }
}
