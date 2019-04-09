package org.ironman.ticketsellingsystem.ui.fragment;


import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import org.ironman.ticketsellingsystem.R;

import butterknife.BindView;
import cn.droidlover.xdroidmvp.cache.SharedPref;
import cn.droidlover.xdroidmvp.mvp.XFragment;


public class HomeFragment extends XFragment implements View.OnClickListener, CompoundButton.OnCheckedChangeListener {

    @BindView(R.id.tv_start_place)
    TextView tvStartPlace;
    @BindView(R.id.tv_end_place)
    TextView tvEndPlace;
    @BindView(R.id.imageView)
    ImageView imageView;
    @BindView(R.id.tv_week)
    TextView tvWeek;
    @BindView(R.id.tv_date)
    TextView tvDate;
    @BindView(R.id.tv_query)
    TextView tvQuery;
    @BindView(R.id.tv_history)
    TextView tvHistory;
    @BindView(R.id.tv_clean)
    TextView tvClean;
    @BindView(R.id.cb_is_fast)
    CheckBox cbIsFast;
    @BindView(R.id.cb_is_student)
    CheckBox cbIsStudent;
    private SharedPref sp;

    @Override
    public void initData(Bundle savedInstanceState) {
        sp = SharedPref.getInstance(context);
        tvStartPlace.setText(sp.getString("tvStartPlace",""));
        cbIsFast.setOnCheckedChangeListener(this);
        cbIsStudent.setOnCheckedChangeListener(this);
        tvQuery.setOnClickListener(this);
        tvClean.setOnClickListener(this);

    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    public Object newP() {
        return null;
    }

    @Override
    protected void oneLogin(String msg) {

    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        switch (buttonView.getId()) {
            case R.id.cb_is_fast:
                if (isChecked) {

                }
                break;
            case R.id.cb_is_student:
                if (isChecked) {

                }
                break;
        }
    }
}
