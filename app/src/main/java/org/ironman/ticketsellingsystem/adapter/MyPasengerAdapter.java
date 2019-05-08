package org.ironman.ticketsellingsystem.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.ironman.ticketsellingsystem.R;
import org.ironman.ticketsellingsystem.model.PasengerInfo;

import butterknife.BindView;
import cn.droidlover.xdroidmvp.kit.KnifeKit;
import cn.droidlover.xrecyclerview.RecyclerAdapter;

/**
 * @data created on 2019/4/16
 * @describe TODO  我的旅客，获得当前账号旅客列表
 */

public class MyPasengerAdapter extends RecyclerAdapter<PasengerInfo.ListBean, MyPasengerAdapter.ViewHolder> {


    private ItemOnclickListener listener;

    public MyPasengerAdapter(Context context) {
        super(context);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_my_pasenger, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final PasengerInfo.ListBean bean=data.get(position);
        holder.tvName.setText(bean.getName());
        holder.tvType.setText(bean.getType());
        holder.tvCard.setText(bean.getPhone());
        holder.tvDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.OnClickListener(position,bean.getId(),bean.getPasengerId());
            }
        });

    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.tv_type)
        TextView tvType;
        @BindView(R.id.tv_card)
        TextView tvCard;
        @BindView(R.id.tv_delete)
        TextView tvDelete;
        public ViewHolder(View itemView) {
            super(itemView);
            KnifeKit.bind(this, itemView);
        }
    }
    /**
     * 定义监听
     */
    public interface ItemOnclickListener {
        void OnClickListener(int position,Integer id,Integer pasengerId);
    }

    public void setListener(ItemOnclickListener listener) {
        this.listener = listener;
    }
}