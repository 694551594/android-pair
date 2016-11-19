package cn.yhq.pair.adapter.recyclerview;

import cn.yhq.adapter.recycler.ItemViewProvider1;
import cn.yhq.adapter.recycler.ViewHolder;
import cn.yhq.pair.item.IPair;

/**
 * Created by Administrator on 2016/11/20.
 */

class ItemViewProvider extends ItemViewProvider1<IPair> {
    private int layout;

    public ItemViewProvider(int layout) {
        this.layout = layout;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position, IPair entity) {
        super.onBindViewHolder(viewHolder, position, entity);
        entity.onBindViewHolder(viewHolder);
    }

    @Override
    public int getItemViewLayoutId() {
        return layout;
    }

}
