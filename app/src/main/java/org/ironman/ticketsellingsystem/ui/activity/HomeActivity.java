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
                break;
            case R.id.fv_order:
                break;
            case R.id.fv_my:
                break;
        }
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        fvHome.setOnClickListener(this);
        fvOrder.setOnClickListener(this);
        fvMy.setOnClickListener(this);
        initAdapter();

    }

    private void initAdapter() {
        fragmentList.clear();
        fragmentList.add(null);
        fragmentList.add(null);
        if (adapter == null) {
            adapter = new HomeViewPagerAdapter(getSupportFragmentManager(), fragmentList);
        }
        vpFragment.setAdapter(adapter);
        vpFragment.setOffscreenPageLimit(fragmentList.size());
        vpFragment.setCurrentItem(0);

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
