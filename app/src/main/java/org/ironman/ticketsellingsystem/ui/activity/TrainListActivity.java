package org.ironman.ticketsellingsystem.ui.activity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;

import com.jcodecraeer.xrecyclerview.XRecyclerView;

import org.ironman.ticketsellingsystem.R;
import org.ironman.ticketsellingsystem.adapter.TrainListAdapter;
import org.ironman.ticketsellingsystem.app.Constans;
import org.ironman.ticketsellingsystem.model.TrainInfo;
import org.ironman.ticketsellingsystem.present.PTrainList;
import org.ironman.ticketsellingsystem.util.CommonUtil;
import org.ironman.ticketsellingsystem.util.TimeUtil;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import butterknife.BindView;
import cn.droidlover.xdroidmvp.kit.Kits;
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
    @BindView(R.id.tv_last)
    TextView tvLast;
    @BindView(R.id.tv_date)
    TextView tvDate;
    @BindView(R.id.tv_next)
    TextView tvNext;
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
        contentBack.setVisibility(View.VISIBLE);
        contentBack.setOnClickListener(this);
        //拿到查询条件
        isFast = getIntent().getStringExtra("isFast");
        startPlace = getIntent().getStringExtra("startPlace");
        endPlace = getIntent().getStringExtra("endPlace");
        date = getIntent().getStringExtra("date");
        tvDate.setText(getIntent().getStringExtra("tvDate"));
        contentTitle.setText(startPlace + "<>" + endPlace);
        contentTitle.setOnClickListener(this);
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
        } else {
            getP().getTrainList(startPlace, endPlace, date, isFast);
        }
        xvRecycler.setLoadingMoreEnabled(false);
        tvDate.setOnClickListener(this);
        tvLast.setOnClickListener(this);
        tvNext.setOnClickListener(this);
        xvRecycler.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                getP().getTrainList(startPlace, endPlace, date, isFast);
            }

            @Override
            public void onLoadMore() {

            }
        });
        adapter.setListener(new TrainListAdapter.ItemOnclickListener() {
            @Override
            public void OnClickListener(TrainInfo.ListEntity bean) {
                //跳转到订票界面
                Intent intent=new Intent(TrainListActivity.this,BuyActivity.class);
                intent.putExtra("TrainInfoBean",bean);
                startActivity(intent);
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
            case R.id.tv_last:
                try {
                    if (Kits.Date.isToday(Long.parseLong(TimeUtil.dateToStamp(date, "yyyy-MM-dd")))){
                        CommonUtil.showMsg("你想买过去的车票么？");
                    }else {
                        date = TimeUtil.stampToDateAndDown(date, "yyyy-MM-dd");
                        tvDate.setText(TimeUtil.dateConver(date, "yyyy-MM-dd", "MM月dd日"));
                        getP().getTrainList(startPlace, endPlace, date, isFast);
                    }

                } catch (ParseException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.tv_date:
                showDatePickDlg();

                break;
            case R.id.tv_next:
                try {
                    date = TimeUtil.stampToDateAndUp(date, "yyyy-MM-dd");
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                tvDate.setText(TimeUtil.dateConver(date, "yyyy-MM-dd", "MM月dd日"));
                getP().getTrainList(startPlace, endPlace, date, isFast);
                break;
            case R.id.content_title:
                //交互起点终点
                String s = startPlace;
                startPlace = endPlace;
                endPlace = s;
                contentTitle.setText(startPlace + "<>" + endPlace);
                getP().getTrainList(startPlace, endPlace, date, isFast);
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

    //生日选择
    protected void showDatePickDlg() {
        Calendar calendar = Calendar.getInstance();
        DatePickerDialog datePickerDialog = new DatePickerDialog(TrainListActivity.this, new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
//                tvDate.setText(year + "-" + (monthOfYear + 1) + "-" + dayOfMonth);
                tvDate.setText((monthOfYear + 1) + "月" + dayOfMonth + "日");
                date = year + "-" + (monthOfYear + 1) + "-" + dayOfMonth;
                getP().getTrainList(startPlace, endPlace, date, isFast);
            }
        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis());
        datePickerDialog.show();

    }
}
