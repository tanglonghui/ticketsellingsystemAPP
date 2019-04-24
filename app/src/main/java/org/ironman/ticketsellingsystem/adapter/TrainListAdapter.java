package org.ironman.ticketsellingsystem.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.ironman.ticketsellingsystem.R;
import org.ironman.ticketsellingsystem.model.TrainInfo;
import org.ironman.ticketsellingsystem.util.TimeUtil;

import butterknife.BindView;
import cn.droidlover.xdroidmvp.kit.KnifeKit;
import cn.droidlover.xdroidmvp.log.XLog;
import cn.droidlover.xrecyclerview.RecyclerAdapter;

/**
 * @data created on 2019/4/16
 * @describe TODO  查询火车列表适配器
 */

public class TrainListAdapter extends RecyclerAdapter<TrainInfo.ListEntity, TrainListAdapter.ViewHolder> {

    private ItemOnclickListener listener;
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
        final TrainInfo.ListEntity bean=data.get(position);
        holder.tvTrainCard.setText(bean.getTrainCard());
        holder.tvStartPlace.setText(bean.getStartPlace());
        holder.tvEndPlace.setText(bean.getEndPlace());
        String startTime=bean.getStartTime();
        String endTime=bean.getEndTime();
//        XLog.e(startTime);
//        XLog.e(endTime);
//        XLog.e(bean.getTrainTime());
//        startTime= TimeUtil.dateConver(startTime,"yyyy-MM-dd'T'HH:mm:ss.SSSSSSZ","HH:mm");
//        endTime=TimeUtil.dateConver(endTime,"yyyy-MM-dd'T'HH:mm:ss.SSSSSSZ","HH:mm");
        holder.tvStartTime.setText(startTime);
        holder.tvEndTime.setText(endTime);
        holder.tvBusiness.setText("商务："+bean.getBusinessSeat()+"张");
        holder.tvFirst.setText("一等："+bean.getFirstSeat()+"张");
        holder.tvSecond.setText("二等："+bean.getSecondSeat()+"张");
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.OnClickListener(bean);
            }
        });
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
    /**
     * 定义监听
     */
    public interface ItemOnclickListener {
        void OnClickListener(TrainInfo.ListEntity bean);
    }

    public void setListener(ItemOnclickListener listener) {
        this.listener = listener;
    }
}