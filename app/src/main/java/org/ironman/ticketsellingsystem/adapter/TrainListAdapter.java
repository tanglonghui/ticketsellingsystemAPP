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
 * @describe TODO  查询火车列表适配器
 */

public class TrainListAdapter extends RecyclerAdapter<String, TrainListAdapter.ViewHolder> {

    public TrainListAdapter(Context context) {
        super(context);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_train_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
//        holder.tvSecond.setText("1");
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
        @BindView(R.id.tv_business)
        TextView tvBusiness;
        @BindView(R.id.tv_first)
        TextView tvFirst;
        @BindView(R.id.tv_second)
        TextView tvSecond;

        public ViewHolder(View itemView) {
            super(itemView);
            KnifeKit.bind(this, itemView);
        }
    }
}