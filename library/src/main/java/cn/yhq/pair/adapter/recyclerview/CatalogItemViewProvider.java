package cn.yhq.pair.adapter.recyclerview;

import android.text.TextUtils;
import android.view.View;

import cn.yhq.adapter.recycler.ViewHolder;
import cn.yhq.pair.R;
import cn.yhq.pair.item.PairCatalog;


public class CatalogItemViewProvider extends PairItemViewProvider<PairCatalog> {

    @Override
    public void setupItemView(ViewHolder viewHolder, int position, PairCatalog entity) {
        viewHolder.bindResId(R.id.title)
                .setVisibility(TextUtils.isEmpty(entity.getTitle()) ? View.GONE : View.VISIBLE)
                .setText(entity.getTitle());
    }

    @Override
    public int getItemViewLayoutId() {
        return R.layout.catalog_layout;
    }

}
