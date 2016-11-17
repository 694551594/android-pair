package cn.yhq.pair.adapter.expandable;

import android.support.v7.widget.ViewStubCompat;
import android.text.Html;
import android.text.TextUtils;
import android.view.View;

import cn.yhq.adapter.core.ViewHolder;
import cn.yhq.adapter.expand.ChildItemViewProvider1;
import cn.yhq.pair.R;
import cn.yhq.pair.item.PairCatalog;
import cn.yhq.pair.item.PairItem;


public abstract class BaseChildItemViewProvider<T extends PairItem> extends ChildItemViewProvider1<PairCatalog, PairItem> {

    @Override
    public int getItemViewLayoutId() {
        return R.layout.pairitem_child_layout;
    }

    public abstract void setupItemView(ViewHolder viewHolder, int groupPosition, PairCatalog groupEntity,
                                       int childPosition, T childEntity);

    @Override
    public void setupView(ViewHolder viewHolder, int groupPosition, PairCatalog groupEntity,
                          int childPosition, PairItem childEntity) {
        // key值
        String key = childEntity.getKey();
        viewHolder.setText(R.id.key, Html.fromHtml(key));
        // 图标
        viewHolder.bindResId(R.id.icon).setVisibility(View.VISIBLE);
        int iconRes = childEntity.getIcon();
        if (iconRes != 0) {
            viewHolder.setImage(iconRes);
        } else if (childEntity.getIconDrawable() != null) {
            viewHolder.setImage(childEntity.getIconDrawable());
        } else {
            viewHolder.setVisibility(View.GONE);
        }
        viewHolder.bindResId(R.id.description).setVisibility(View.VISIBLE);
        String description = childEntity.getDescription();
        if (TextUtils.isEmpty(description)) {
            viewHolder.setText(description).setVisibility(View.GONE);
        } else {
            viewHolder.setText(Html.fromHtml(description));
        }

        // 分割线
        viewHolder.bindResId(R.id.child_divider);
        if (childPosition == this.getAdapter()
                .getChildrenCount(groupPosition) - 1) {
            viewHolder.setVisibility(View.GONE);
        } else {
            viewHolder.setVisibility(View.VISIBLE);
        }
        viewHolder.setVisibility(View.GONE);

        ViewStubCompat viewStub = viewHolder.getView(R.id.view_stub);
        viewStub.setLayoutResource(getItemViewStubLayoutId());
        viewStub.setVisibility(View.VISIBLE);

        this.setupItemView(viewHolder, groupPosition, groupEntity, childPosition, (T) childEntity);
    }

    public abstract int getItemViewStubLayoutId();

}
