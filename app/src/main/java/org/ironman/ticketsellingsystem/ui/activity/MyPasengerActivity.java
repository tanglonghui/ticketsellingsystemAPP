package org.ironman.ticketsellingsystem.ui.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.TextView;

import com.jcodecraeer.xrecyclerview.XRecyclerView;

import org.ironman.ticketsellingsystem.R;
import org.ironman.ticketsellingsystem.adapter.MyPasengerAdapter;
import org.ironman.ticketsellingsystem.app.Constans;
import org.ironman.ticketsellingsystem.model.ContentInfo;
import org.ironman.ticketsellingsystem.model.PasengerInfo;
import org.ironman.ticketsellingsystem.present.PGetPasenger;
import org.ironman.ticketsellingsystem.util.CommonUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.droidlover.xdroidmvp.cache.SharedPref;
import cn.droidlover.xdroidmvp.mvp.XActivity;
import cn.droidlover.xdroidmvp.router.Router;



public class MyPasengerActivity extends XActivity<PGetPasenger> implements View.OnClickListener {


    @BindView(R.id.content_back)
    TextView contentBack;
    @BindView(R.id.content_title)
    TextView contentTitle;
    @BindView(R.id.content_add)
    TextView contentAdd;
    @BindView(R.id.xv_recycler)
    XRecyclerView xvRecycler;

    MyPasengerAdapter adapter;
    Integer id;
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
                AddPasengerActivity.launch(this);
                break;
        }
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        id = SharedPref.getInstance(this).getInt(Constans.ID, 0);
        contentAdd.setOnClickListener(this);
        contentBack.setOnClickListener(this);
        adapter = new MyPasengerAdapter(this);
        //jdk 1.8 才支持lambda
//        adapter.setListener((Integer p)-> getP().removePasenger(id,p));
        adapter.setListener(new MyPasengerAdapter.ItemOnclickListener() {
            @Override
            public void OnClickListener(Integer Id,Integer pasengerId) {
                getP().removePasenger(Id, pasengerId);
            }
        });
        xvRecycler.setLayoutManager(new LinearLayoutManager(this));
        xvRecycler.setLoadingMoreEnabled(false);
        xvRecycler.setPullRefreshEnabled(false);
        xvRecycler.setAdapter(adapter);

        getP().getPasenger(id);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_my_pasenger;
    }

    @Override
    public PGetPasenger newP() {
        return new PGetPasenger();
    }

    public static void launch(Activity activity) {
        Router.newIntent(activity).to(MyPasengerActivity.class).launch();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    public void data2view(PasengerInfo data) {
        if (data.isSuccess()) {
            adapter.setData(data.getList());
        } else {
            CommonUtil.showMsg(data.getMessage());
        }
    }

    public void data2view(ContentInfo data) {
        if (data.isSuccess()) {
            CommonUtil.showMsg("删除成功");
            getP().getPasenger(id);
        } else {
            CommonUtil.showMsg(data.getMessage());
        }
    }
}
