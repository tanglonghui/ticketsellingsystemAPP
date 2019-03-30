package org.ironman.ticketsellingsystem.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Archer on 2019/3/28.
 */

public class HomeViewPagerAdapter extends FragmentPagerAdapter {
    private List<Fragment> list=new ArrayList<>();

    public HomeViewPagerAdapter(FragmentManager fm,List<Fragment> list) {
        super(fm);
        this.list.clear();
        this.list=list;
    }

    @Override
    public Fragment getItem(int position) {
        return list.get(position);
    }

    @Override
    public int getCount() {
        return list.size();
    }
}
