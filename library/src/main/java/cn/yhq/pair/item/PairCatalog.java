package cn.yhq.pair.item;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;

import cn.yhq.adapter.recycler.ViewHolder;
import cn.yhq.pair.R;

/**
 * Created by Administrator on 2016/11/15.
 */

public class PairCatalog extends PairGroup {
    private String title;

    public PairCatalog(Context context) {
        super(context);
    }

    public PairCatalog(Context context, AttributeSet attrs) {
        super(context, attrs);

        final TypedArray a = context.obtainStyledAttributes(
                attrs, R.styleable.PairCatalog);

        this.title = a.getString(R.styleable.PairCatalog_title);

        a.recycle();
    }

    @Override
    public PairGroup setEnable(boolean enable) {
        for (IPair pair : getPairs()) {
            ((Pair<?>) pair).setEnable(enable);
        }
        return super.setEnable(enable);
    }

    public String getTitle() {
        return title;
    }

    public PairCatalog setTitle(String title) {
        this.title = title;
        this.notifyChange();
        return this;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder) {
        super.onBindViewHolder(viewHolder);

        viewHolder.itemView.setEnabled(isEnable());

        viewHolder.bindResId(R.id.title)
                .setVisibility(TextUtils.isEmpty(this.getTitle()) ? View.GONE : View.VISIBLE)
                .setText(this.getTitle());
    }

    @Override
    public int getItemViewLayoutId() {
        return R.layout.pair_catalog_layout;
    }
}
