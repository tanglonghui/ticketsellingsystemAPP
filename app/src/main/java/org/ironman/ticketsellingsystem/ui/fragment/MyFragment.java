package org.ironman.ticketsellingsystem.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.ironman.ticketsellingsystem.MainActivity;
import org.ironman.ticketsellingsystem.R;
import org.ironman.ticketsellingsystem.ui.activity.ChangePasswordActivity;
import org.ironman.ticketsellingsystem.ui.activity.ChangePersonalDataActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.droidlover.xdroidmvp.mvp.XFragment;
import de.hdodenhof.circleimageview.CircleImageView;


public class MyFragment extends XFragment implements View.OnClickListener {

    @BindView(R.id.iv_head)
    CircleImageView ivHead;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_account)
    TextView tvAccount;
    @BindView(R.id.rl_head)
    RelativeLayout rlHead;
    @BindView(R.id.tv_add_pasenger)
    TextView tvAddPasenger;
    @BindView(R.id.tv_change_password)
    TextView tvChangePassword;
    @BindView(R.id.tv_logout)
    TextView tvLogout;
    Unbinder unbinder;

    @Override
    public void initData(Bundle savedInstanceState) {
        tvLogout.setOnClickListener(this);
        tvChangePassword.setOnClickListener(this);
        tvAddPasenger.setOnClickListener(this);
        rlHead.setOnClickListener(this);
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_my;
    }

    @Override
    public Object newP() {
        return null;
    }

    @Override
    protected void oneLogin(String msg) {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rl_head:
                ChangePersonalDataActivity.launch(getActivity());
                break;
            case R.id.tv_add_pasenger:

                break;
            case R.id.tv_change_password:
                ChangePasswordActivity.launch(getActivity());
                break;
            case R.id.tv_logout:
                MainActivity.launch(getActivity());
                getActivity().finish();
                break;
        }
    }
}
