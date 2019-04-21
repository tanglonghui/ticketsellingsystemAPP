package org.ironman.ticketsellingsystem.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.TextView;

import com.jcodecraeer.xrecyclerview.XRecyclerView;

import org.ironman.ticketsellingsystem.R;
import org.ironman.ticketsellingsystem.adapter.TrainListAdapter;
import org.ironman.ticketsellingsystem.app.Constans;
import org.ironman.ticketsellingsystem.model.TrainInfo;
import org.ironman.ticketsellingsystem.present.PTrainList;
import org.ironman.ticketsellingsystem.util.CommonUtil;

import java.util.ArrayList;

import butterknife.BindView;
import cn.droidlover.xdroidmvp.mvp.XActivity;

/**
 * @data Created by Archer on 2019/4/21.
 * @describe TODO : 火车查询结果界面
 */


public class TrainListActivity extends XActivity<PTrainList> implements View.OnClickListener {

    @BindView(R.id.content_back)
    TextView contentBack;
    @BindView(R.id.content_title)
    TextView contentTitle;
    @BindView(R.id.xv_recycler)
    XRecyclerView xvRecycler;
    TrainListAdapter adapter;
    private ArrayList<TrainInfo.ListEntity> mdata;
    private String startPlace;
    private String endPlace;
    private String date;
    private String isFast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void oneLogin(String msg) {

    }

    @Override
    public void initData(Bundle savedInstanceState) {
        contentTitle.setText("选择车次");
        contentBack.setVisibility(View.VISIBLE);
        contentBack.setOnClickListener(this);
        //拿到查询条件
        isFast = getIntent().getStringExtra("isFast");
        startPlace = getIntent().getStringExtra("startPlace");
        endPlace = getIntent().getStringExtra("endPlace");
        date = getIntent().getStringExtra("date");

        adapter = new TrainListAdapter(this);
        xvRecycler.setLayoutManager(new LinearLayoutManager(this));
        xvRecycler.setAdapter(adapter);
        if (Constans.isDebug) {
            if (mdata == null)
                mdata = new ArrayList<TrainInfo.ListEntity>();
            for (int i = 0; i < 8; i++) {
                mdata.add(null);
            }
            adapter.setData(mdata);
        }else {
            getP().getTrainList(startPlace,endPlace,date,isFast );
        }
        xvRecycler.setLoadingMoreEnabled(false);
        xvRecycler.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                getP().getTrainList(startPlace,endPlace,date,isFast );
            }

            @Override
            public void onLoadMore() {

            }
        });
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_train_list;
    }

    @Override
    public PTrainList newP() {
        return new PTrainList();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.content_back:
                finish();
                break;
        }
    }

    public void data2view(TrainInfo data) {
        xvRecycler.refreshComplete();
        if (data.isSuccess()) {
            adapter.setData(data.getList());
        } else {
            CommonUtil.showMsg(data.getMessage());
        }
    }
}
