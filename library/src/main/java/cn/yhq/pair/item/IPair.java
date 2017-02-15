package cn.yhq.pair.item;

import cn.yhq.adapter.recycler.ViewHolder;
import cn.yhq.pair.action.PairAction;

/**
 * Created by Administrator on 2016/11/17.
 */

public interface IPair {

    int getId();

    int getItemViewLayoutId();

    void onBindViewHolder(ViewHolder viewHolder);

    void setOnPairChangeListener(OnPairChangeListener listener);

    void onCreated();

    boolean isVisible();

    boolean isEnable();

    void setAction(PairAction action);

}
