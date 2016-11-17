package cn.yhq.pair.adapter.recyclerview;

import android.text.TextUtils;
import android.view.View;

import cn.yhq.adapter.recycler.ItemViewProvider1;
import cn.yhq.adapter.recycler.ViewHolder;
import cn.yhq.pair.R;
import cn.yhq.pair.item.IPair;
import cn.yhq.pair.item.PairCatalog;


public class CatalogItemViewProvider extends ItemViewProvider1<IPair> {

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position, IPair entity) {
        PairCatalog catalog = (PairCatalog) entity;
        viewHolder.bindResId(R.id.title)
                .setVisibility(TextUtils.isEmpty(catalog.getTitle()) ? View.GONE : View.VISIBLE)
                .setText(catalog.getTitle());
    }

    @Override
    public int getItemViewLayoutId() {
        return R.layout.item_layout;
    }

}
