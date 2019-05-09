package org.ironman.ticketsellingsystem.ui.activity;

import android.app.Activity;
import android.content.Intent;
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
import org.ironman.ticketsellingsystem.present.PChosePasenger;
import org.ironman.ticketsellingsystem.present.PGetPasenger;
import org.ironman.ticketsellingsystem.util.CommonUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.droidlover.xdroidmvp.cache.SharedPref;
import cn.droidlover.xdroidmvp.mvp.XActivity;
import cn.droidlover.xdroidmvp.router.Router;

/**
 * @data created on 2019/5/8
 * @describe TODO 选择旅客界面
 */
public class ChosePasengerActivity extends XActivity<PChosePasenger> implements View.OnClickListener {


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

    PasengerInfo.ListBean bean;
    List mdata;

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
        contentAdd.setVisibility(View.INVISIBLE);
        contentTitle.setText("选择旅客");
        contentAdd.setOnClickListener(this);
        contentBack.setOnClickListener(this);
        adapter = new MyPasengerAdapter(this,"chose");
        adapter.setListener(new MyPasengerAdapter.ItemOnclickListener() {
            @Override
            public void OnClickListener(int position, Integer Id, Integer pasengerId) {
                //将旅客信息传回 购买界面
                bean= (PasengerInfo.ListBean) mdata.get(position);
                Intent intent = new Intent();
                intent.putExtra("pasengerBean", bean);
                setResult(RESULT_OK, intent);
                finish();
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
    public PChosePasenger newP() {
        return new PChosePasenger();
    }

    public static void launch(Activity activity) {
        Router.newIntent(activity).to(ChosePasengerActivity.class).launch();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    public void data2view(PasengerInfo data) {
        if (data.isSuccess()) {
            mdata=data.getList();
            adapter.setData(mdata);
            adapter.notifyDataSetChanged();
        } else {
            CommonUtil.showMsg(data.getMessage());
        }
    }
}
