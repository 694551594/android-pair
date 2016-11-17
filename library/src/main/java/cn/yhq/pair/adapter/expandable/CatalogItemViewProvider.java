package cn.yhq.pair.adapter.expandable;

import android.text.TextUtils;
import android.view.View;

import cn.yhq.adapter.core.ViewHolder;
import cn.yhq.adapter.expand.GroupItemViewProvider1;
import cn.yhq.pair.R;
import cn.yhq.pair.item.PairCatalog;


public class CatalogItemViewProvider extends GroupItemViewProvider1<PairCatalog> {

  @Override
  public void setupView(ViewHolder viewHolder, int position, PairCatalog entity, boolean isExpanded) {
    viewHolder.bindResId(R.id.title)
        .setVisibility(TextUtils.isEmpty(entity.getTitle()) ? View.GONE : View.VISIBLE)
        .setText(entity.getTitle());
  }

  @Override
  public int getItemViewLayoutId() {
    return R.layout.pairitem_group_layout;
  }

}
