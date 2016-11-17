package cn.yhq.pair.adapter.recyclerview;

import android.support.v7.widget.ViewStubCompat;
import android.text.Html;
import android.text.TextUtils;
import android.view.View;

import cn.yhq.adapter.recycler.ItemViewProvider1;
import cn.yhq.adapter.recycler.ViewHolder;
import cn.yhq.pair.R;
import cn.yhq.pair.item.IPair;
import cn.yhq.pair.item.PairItem;


public abstract class BasePairItemViewProvider<T extends PairItem<T>> extends ItemViewProvider1<IPair> {

    @Override
    public int getItemViewLayoutId() {
        return R.layout.catalog_layout;
    }

    public abstract void setupItemView(ViewHolder viewHolder, int position, T childEntity);

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position, IPair entity) {
        PairItem<T> item = (PairItem<T>) entity;
        // key值
        String key = item.getKey();
        viewHolder.setText(R.id.key, Html.fromHtml(key));
        // 图标
        viewHolder.bindResId(R.id.icon).setVisibility(View.VISIBLE);
        int iconRes = item.getIcon();
        if (iconRes != 0) {
            viewHolder.setImage(iconRes);
        } else if (item.getIconDrawable() != null) {
            viewHolder.setImage(item.getIconDrawable());
        } else {
            viewHolder.setVisibility(View.GONE);
        }
        viewHolder.bindResId(R.id.description).setVisibility(View.VISIBLE);
        String description = item.getDescription();
        if (TextUtils.isEmpty(description)) {
            viewHolder.setText(description).setVisibility(View.GONE);
        } else {
            viewHolder.setText(Html.fromHtml(description));
        }

        ViewStubCompat viewStub = viewHolder.getView(R.id.view_stub);
        viewStub.setLayoutResource(getItemViewStubLayoutId());
        viewStub.setVisibility(View.VISIBLE);

        this.setupItemView(viewHolder, position, (T) entity);
    }

    public abstract int getItemViewStubLayoutId();

}
