package cn.yhq.pair.adapter.recyclerview;

import android.support.v7.widget.ViewStubCompat;
import android.text.Html;
import android.text.TextUtils;
import android.view.View;

import cn.yhq.adapter.recycler.ViewHolder;
import cn.yhq.pair.R;
import cn.yhq.pair.item.PairItem;


public abstract class BaseItemViewProvider<T extends PairItem<T>> extends PairItemViewProvider<T> {

    @Override
    public int getItemViewLayoutId() {
        return R.layout.pair_item_layout;
    }

    @Override
    public void setupItemView(ViewHolder viewHolder, int position, T entity) {
        // key值
        String key = entity.getKey();
        viewHolder.setText(R.id.key, Html.fromHtml(key));
        // 图标
        viewHolder.bindResId(R.id.icon).setVisibility(View.VISIBLE);
        int iconRes = entity.getIcon();
        if (iconRes != 0) {
            viewHolder.setImage(iconRes);
        } else if (entity.getIconDrawable() != null) {
            viewHolder.setImage(entity.getIconDrawable());
        } else {
            viewHolder.setVisibility(View.GONE);
        }
        viewHolder.bindResId(R.id.description).setVisibility(View.VISIBLE);
        String description = entity.getDescription();
        if (TextUtils.isEmpty(description)) {
            viewHolder.setText(description).setVisibility(View.GONE);
        } else {
            viewHolder.setText(Html.fromHtml(description));
        }

        ViewStubCompat viewStub = viewHolder.getView(R.id.view_stub);
        viewStub.setLayoutResource(getItemViewStubLayoutId());
        viewStub.setVisibility(View.VISIBLE);
    }

    public abstract int getItemViewStubLayoutId();

}
