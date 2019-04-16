package org.ironman.ticketsellingsystem.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.ironman.ticketsellingsystem.R;

import butterknife.BindView;
import cn.droidlover.xdroidmvp.mvp.XActivity;
import cn.droidlover.xrecyclerview.XRecyclerView;

public class TrainListActivity extends XActivity implements View.OnClickListener {

    @BindView(R.id.content_back)
    TextView contentBack;
    @BindView(R.id.content_title)
    TextView contentTitle;
    @BindView(R.id.xv_recycler)
    XRecyclerView xvRecycler;

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
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_train_list;
    }

    @Override
    public Object newP() {
        return null;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.content_back:
                finish();
                break;
        }
    }
}
