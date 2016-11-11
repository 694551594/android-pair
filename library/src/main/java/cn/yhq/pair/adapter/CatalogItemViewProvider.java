package cn.yhq.pair.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import cn.android.developers.sdk.R;
import cn.developer.sdk.adapter.core.ViewHolder;
import cn.developer.sdk.adapter.expand.GroupItemViewProvider;
import cn.developer.sdk.pair.core.PairGroup;

public class CatalogItemViewProvider extends GroupItemViewProvider<PairGroup> {

  @Override
  public View getConvertView(Context context, LayoutInflater inflater, ViewGroup parent) {
    return inflater.inflate(R.layout.listitem_pairlist_group_layout, parent, false);
  }

  @Override
  public void setupView(ViewHolder viewHolder, int position, PairGroup entity, boolean isExpanded) {
    viewHolder.bindResId(R.id.tv_text)
        .setVisibility(TextUtils.isEmpty(entity.getText()) ? View.GONE : View.VISIBLE)
        .bindTextData(entity.getText()).bindResId(R.id.rl_pairlist_group_layout)
        .setVisibility(entity.isVisiable() ? View.VISIBLE : View.GONE);
  }

}
