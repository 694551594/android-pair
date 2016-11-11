package cn.yhq.pair.adapter;

import android.content.Context;
import android.text.Html;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import cn.android.developers.sdk.R;
import cn.developer.sdk.adapter.core.ViewHolder;
import cn.developer.sdk.adapter.expand.ChildItemViewProvider;
import cn.developer.sdk.pair.core.PairGroup;
import cn.developer.sdk.pair.core.PairItem;

public class BaseChildItemViewProvider extends ChildItemViewProvider<PairGroup, PairItem> {
  private boolean isIconVisiable;

  @Override
  public View getConvertView(Context context, LayoutInflater inflater, ViewGroup parent) {
    return inflater.inflate(R.layout.listitem_pairlist_child_layout, parent, false);
  }

  /**
   * 是否显示图标
   * 
   * @param isVisiable
   */
  public void setIconVisiable(boolean isVisiable) {
    this.isIconVisiable = isVisiable;
  }

  @Override
  public void setupView(ViewHolder viewHolder, int groupPosition, PairGroup groupEntity,
      int childPosition, PairItem childEntity) {
    // ImageView iconImageView = viewHolder.getView(R.id.iv_pair_icon);
    // ImageView indicatorImageView = viewHolder.getView(R.id.show_message_more);
    // TextView keyTextView = viewHolder.getView(R.id.tv_pair_item_key);

    // key值
    String key = childEntity.getKey();
    // keyTextView.setText(Html.fromHtml(key));
    viewHolder.bindTextData(R.id.tv_pair_item_key, Html.fromHtml(key));

    // 图标
    viewHolder.bindResId(R.id.iv_pair_icon).setVisibility(View.VISIBLE);
    int iconRes = childEntity.getIconRes();
    if (iconRes != 0) {
      viewHolder.bindImageData(iconRes);
      // iconImageView.setImageResource(iconRes);
    } else {
      viewHolder.bindImageData(childEntity.getIconDrawable());
      if (childEntity.getIconDrawable() == null) {
        viewHolder.setVisibility(isIconVisiable ? View.VISIBLE : View.GONE);
      }

      // iconImageView.setImageBitmap(null);
      // if (!isIconVisiable) {
      // iconImageView.setVisibility(View.GONE);
      // }
    }

    // 右箭头
    viewHolder.bindResId(R.id.show_message_more);
    boolean indicator = childEntity.isIndicator();
    viewHolder.setVisibility(indicator ? View.VISIBLE : View.GONE);
    // if (indicator) {
    // indicatorImageView.setVisibility(View.VISIBLE);
    // } else {
    // indicatorImageView.setVisibility(View.GONE);
    // }

    // 描述
    // TextView descriptionTextView = viewHolder.getView(R.id.tv_pair_item_description);
    viewHolder.bindResId(R.id.tv_pair_item_description);
    String description = childEntity.getDescription();
    if (TextUtils.isEmpty(description)) {
      // descriptionTextView.setText("");
      // descriptionTextView.setVisibility(View.GONE);
      viewHolder.bindTextData("").setVisibility(View.GONE);
    } else {
      viewHolder.bindTextData(Html.fromHtml(description)).setVisibility(View.VISIBLE);
      // descriptionTextView.setText(Html.fromHtml(description));
      // descriptionTextView.setVisibility(View.VISIBLE);
    }

    // 分割线
    viewHolder.bindResId(R.id.child_divider);
    // View dividerView = viewHolder.getView(R.id.child_divider);
    if (groupEntity.isVisiable()
        && childPosition == ((BaseExpandableListAdapter) this.getAdapter())
            .getChildrenCount(groupPosition) - 1) {
      // dividerView.setVisibility(View.GONE);
      viewHolder.setVisibility(View.GONE);
    } else {
      // dividerView.setVisibility(View.VISIBLE);
      viewHolder.setVisibility(View.VISIBLE);
    }
  }

}
