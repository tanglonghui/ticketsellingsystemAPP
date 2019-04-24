package org.ironman.ticketsellingsystem.ui.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.jcodecraeer.xrecyclerview.XRecyclerView;

import org.ironman.ticketsellingsystem.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.droidlover.xdroidmvp.mvp.XActivity;
import cn.droidlover.xdroidmvp.router.Router;

public class MyPasengerActivity extends XActivity implements View.OnClickListener {


    @BindView(R.id.content_back)
    TextView contentBack;
    @BindView(R.id.content_title)
    TextView contentTitle;
    @BindView(R.id.content_add)
    TextView contentAdd;
    @BindView(R.id.xv_recycler)
    XRecyclerView xvRecycler;

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
                break;
        }
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        contentAdd.setOnClickListener(this);
        contentBack.setOnClickListener(this);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_my_pasenger;
    }

    @Override
    public Object newP() {
        return null;
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
}
