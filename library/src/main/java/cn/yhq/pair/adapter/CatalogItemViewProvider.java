package cn.yhq.pair.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListAdapter;

import cn.yhq.adapter.core.ViewHolder;
import cn.yhq.pair.item.PairCatalog;


public class CatalogItemViewProvider extends ExpandableListAdapter<PairCatalog> {

  @Override
  public View getConvertView(Context context, LayoutInflater inflater, ViewGroup parent) {
    return inflater.inflate(R.layout.listitem_pairlist_group_layout, parent, false);
  }

  @Override
  public void setupView(ViewHolder viewHolder, int position, PairCatalog entity, boolean isExpanded) {
    viewHolder.bindResId(R.id.tv_text)
        .setVisibility(TextUtils.isEmpty(entity.getText()) ? View.GONE : View.VISIBLE)
        .bindTextData(entity.getText()).bindResId(R.id.rl_pairlist_group_layout)
        .setVisibility(entity.isVisiable() ? View.VISIBLE : View.GONE);
  }

}
