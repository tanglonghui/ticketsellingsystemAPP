package org.ironman.ticketsellingsystem.ui.fragment;


import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jcodecraeer.xrecyclerview.XRecyclerView;

import org.ironman.ticketsellingsystem.R;
import org.ironman.ticketsellingsystem.adapter.OrderAdapter;
import org.ironman.ticketsellingsystem.model.ContentInfo;
import org.ironman.ticketsellingsystem.util.CommonUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.droidlover.xdroidmvp.mvp.XFragment;

/**
 * @data Created by Archer on 2019/4/18.
 * @describe TODO : 订单展示列表
 */


public class OrderFragment extends XFragment implements View.OnClickListener {

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
    private String state = "1";

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

    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_order;
    }

    @Override
    public Object newP() {
        return null;
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
            case R.id.tv_historical_trip:
                state = "3";
                tvUnpaid.setTextColor(getResources().getColor(R.color.white));
                tvNoTrip.setTextColor(getResources().getColor(R.color.white));
                tvHistoricalTrip.setTextColor(getResources().getColor(R.color.x_red));
                break;
            case R.id.tv_no_trip:
                state = "2";
                tvUnpaid.setTextColor(getResources().getColor(R.color.white));
                tvNoTrip.setTextColor(getResources().getColor(R.color.x_red));
                tvHistoricalTrip.setTextColor(getResources().getColor(R.color.white));
                break;
            case R.id.tv_unpaid:
                state = "1";
                tvUnpaid.setTextColor(getResources().getColor(R.color.x_red));
                tvNoTrip.setTextColor(getResources().getColor(R.color.white));
                tvHistoricalTrip.setTextColor(getResources().getColor(R.color.white));
                break;
        }
    }

    public void data2view(ContentInfo data) {
        xvRecycler.refreshComplete();
        if (data.isSuccess()) {
            adapter.setData((String[]) data.getList());
        } else {
            CommonUtil.showMsg(data.getMessage());
        }
    }
}
