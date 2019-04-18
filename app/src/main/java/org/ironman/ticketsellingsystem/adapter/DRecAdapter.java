package org.ironman.ticketsellingsystem.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.ironman.ticketsellingsystem.R;

import cn.droidlover.xdroidmvp.kit.KnifeKit;
import cn.droidlover.xrecyclerview.RecyclerAdapter;
/**
 * @data created on 2019/4/16
 * @describe TODO  也许我应该把它设置成一个模板。但...
 */

public class DRecAdapter extends RecyclerAdapter<String,DRecAdapter.ViewHolder> {


    public DRecAdapter(Context context) {
        super(context);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_train_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public ViewHolder(View itemView) {
            super(itemView);
            KnifeKit.bind(this, itemView);
        }
    }
}