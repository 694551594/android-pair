package cn.yhq.pair.item;

import cn.yhq.adapter.recycler.ViewHolder;

/**
 * Created by Administrator on 2016/11/17.
 */

public interface IPair {

    int getId();

    int getItemViewLayoutId();

    void onBindViewHolder(ViewHolder viewHolder);

    void setOnPairChangeListener(OnPairChangeListener listener);

}
