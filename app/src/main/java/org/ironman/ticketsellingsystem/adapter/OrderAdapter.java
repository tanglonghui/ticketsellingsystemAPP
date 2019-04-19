package org.ironman.ticketsellingsystem.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.ironman.ticketsellingsystem.R;

import butterknife.BindView;
import cn.droidlover.xdroidmvp.kit.KnifeKit;
import cn.droidlover.xrecyclerview.RecyclerAdapter;

/**
 * @data created on 2019/4/16
 * @describe TODO  也许我应该把它设置成一个模板。但...
 */

public class OrderAdapter extends RecyclerAdapter<String, OrderAdapter.ViewHolder> {


    public OrderAdapter(Context context) {
        super(context);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_order, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_train_card)
        TextView tvTrainCard;
        @BindView(R.id.tv_start_place)
        TextView tvStartPlace;
        @BindView(R.id.tv_start_time)
        TextView tvStartTime;
        @BindView(R.id.tv_end_place)
        TextView tvEndPlace;
        @BindView(R.id.tv_end_time)
        TextView tvEndTime;
        @BindView(R.id.tv_order_id)
        TextView tvOrderId;
        @BindView(R.id.tv_pasenger)
        TextView tvPasenger;
        @BindView(R.id.tv_jump)
        TextView tvJump;

        public ViewHolder(View itemView) {
            super(itemView);
            KnifeKit.bind(this, itemView);
        }
    }
}