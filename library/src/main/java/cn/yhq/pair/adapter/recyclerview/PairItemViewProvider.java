package cn.yhq.pair.adapter.recyclerview;

import cn.yhq.adapter.recycler.ItemViewProvider1;
import cn.yhq.adapter.recycler.ViewHolder;
import cn.yhq.pair.item.IPair;


public abstract class PairItemViewProvider<T extends IPair> extends ItemViewProvider1<IPair> {

    public abstract void setupItemView(ViewHolder viewHolder, int position, T entity);

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position, IPair entity) {
        this.setupItemView(viewHolder, position, (T) entity);
    }

}
