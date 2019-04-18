package org.ironman.ticketsellingsystem.ui.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import org.ironman.ticketsellingsystem.R;
import org.ironman.ticketsellingsystem.adapter.HomeViewPagerAdapter;
import org.ironman.ticketsellingsystem.ui.fragment.HomeFragment;
import org.ironman.ticketsellingsystem.ui.fragment.MyFragment;
import org.ironman.ticketsellingsystem.ui.fragment.OrderFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import cn.droidlover.xdroidmvp.mvp.XActivity;
import cn.droidlover.xdroidmvp.router.Router;

/**
 * Created by Archer on 2019/3/17.
 */

public class HomeActivity extends XActivity implements View.OnClickListener {
    @BindView(R.id.content_back)
    TextView contentBack;
    @BindView(R.id.content_title)
    TextView contentTitle;
    @BindView(R.id.vp_fragment)
    ViewPager vpFragment;
    @BindView(R.id.iv_home)
    ImageView ivHome;
    @BindView(R.id.fv_home)
    FrameLayout fvHome;
    @BindView(R.id.iv_order)
    ImageView ivOrder;
    @BindView(R.id.fv_order)
    FrameLayout fvOrder;
    @BindView(R.id.image_my)
    ImageView imageMy;
    @BindView(R.id.fv_my)
    FrameLayout fvMy;
    @BindView(R.id.frameMenu)
    FrameLayout frameMenu;

    HomeViewPagerAdapter adapter;
    List<Fragment> fragmentList = new ArrayList<>();

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fv_home:
                contentTitle.setText("首页");
                vpFragment.setCurrentItem(0);
                fvHome.setSelected(true);
                fvOrder.setSelected(false);
                fvMy.setSelected(false);
                break;
            case R.id.fv_order:
                contentTitle.setText("订单");
                vpFragment.setCurrentItem(1);
                fvHome.setSelected(false);
                fvOrder.setSelected(true);
                fvMy.setSelected(false);
                break;
            case R.id.fv_my:
                contentTitle.setText("我的");
                vpFragment.setCurrentItem(2);
                fvHome.setSelected(false);
                fvOrder.setSelected(false);
                fvMy.setSelected(true);
                break;
        }
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        fvHome.setOnClickListener(this);
        fvOrder.setOnClickListener(this);
        fvMy.setOnClickListener(this);
        contentBack.setVisibility(View.INVISIBLE);
        initAdapter();

    }

    private void initAdapter() {
        fragmentList.clear();
        fragmentList.add(new HomeFragment());
        fragmentList.add(new OrderFragment());
        fragmentList.add(new MyFragment());
        if (adapter == null) {
            adapter = new HomeViewPagerAdapter(getSupportFragmentManager(), fragmentList);
        }
        vpFragment.setAdapter(adapter);
        vpFragment.setOffscreenPageLimit(fragmentList.size());
        vpFragment.setCurrentItem(0);
        fvHome.setSelected(true);
        fvOrder.setSelected(false);
        fvMy.setSelected(false);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_home;
    }

    @Override
    public Object newP() {
        return null;
    }

    @Override
    protected void oneLogin(String msg) {

    }

    public static void launch(Activity activity) {
        Router.newIntent(activity).to(HomeActivity.class).launch();
    }
}
