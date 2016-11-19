package cn.yhq.pair.item;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.ViewStubCompat;
import android.text.Html;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;

import cn.yhq.adapter.recycler.OnRecyclerViewItemClickListener;
import cn.yhq.adapter.recycler.ViewHolder;
import cn.yhq.pair.R;
import cn.yhq.pair.action.PairAction;
import cn.yhq.pair.action.PairClickAction;

/**
 * Created by Administrator on 2016/11/15.
 */

public abstract class PairItem<T extends PairItem<T>> extends Pair<T> {
    private int icon;
    private Drawable iconDrawable;
    private String key;
    private PairAction action;
    private String description;
    private OnRecyclerViewItemClickListener clickListener = new OnRecyclerViewItemClickListener() {

        @Override
        public void onRecyclerViewItemClick(View itemView, int position) {
            performClick();
        }

    };

    public PairItem(Context context, AttributeSet attrs) {
        super(context, attrs);

        final TypedArray a = context.obtainStyledAttributes(
                attrs, R.styleable.Pair);

        this.icon = a.getResourceId(R.styleable.Pair_icon, 0);
        this.key = a.getString(R.styleable.Pair_key);
        this.description = a.getString(R.styleable.Pair_description);

        a.recycle();
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder) {
        super.onBindViewHolder(viewHolder);

        viewHolder.itemView.setEnabled(isEnable());

        viewHolder.setOnRecyclerViewItemClickListener(clickListener);

        // key值
        String key = this.getKey();
        viewHolder.setText(R.id.key, Html.fromHtml(key));
        // 图标
        viewHolder.bindResId(R.id.icon).setVisibility(View.VISIBLE);
        int iconRes = this.getIcon();
        if (iconRes != 0) {
            viewHolder.setImage(iconRes);
        } else if (this.getIconDrawable() != null) {
            viewHolder.setImage(this.getIconDrawable());
        } else {
            viewHolder.setVisibility(View.GONE);
        }
        viewHolder.bindResId(R.id.description).setVisibility(View.VISIBLE);
        String description = this.getDescription();
        if (TextUtils.isEmpty(description)) {
            viewHolder.setText(description).setVisibility(View.GONE);
        } else {
            viewHolder.setText(Html.fromHtml(description));
        }

        ViewStubCompat viewStub = viewHolder.getView(R.id.view_stub);
        viewStub.setLayoutResource(getWidgetLayoutResource());
        viewStub.setVisibility(View.VISIBLE);
    }

    abstract public int getWidgetLayoutResource();

    @Override
    public int getItemViewLayoutId() {
        return R.layout.pair_item_layout;
    }

    public boolean performClick() {
        if (action != null) {
            if (action instanceof PairClickAction) {
                return ((PairClickAction) action).onClick(this);
            }
        }
        return false;
    }

    public String getDescription() {
        return description;
    }

    public T setDescription(String description) {
        this.description = description;
        this.notifyChange();
        return (T) this;
    }

    public int getIcon() {
        return icon;
    }

    public Drawable getIconDrawable() {
        return iconDrawable;
    }

    public T setIconDrawable(Drawable iconDrawable) {
        this.iconDrawable = iconDrawable;
        this.notifyChange();
        return (T) this;
    }

    public T setIcon(int icon) {
        this.icon = icon;
        this.notifyChange();
        return (T) this;
    }

    public String getKey() {
        return key;
    }

    public T setKey(String key) {
        this.key = key;
        this.notifyChange();
        return (T) this;
    }

    public T setAction(PairAction action) {
        this.action = action;
        return (T) this;
    }

    public T setClickAction(PairClickAction action) {
        this.setAction(action);
        return (T) this;
    }

    public PairAction getAction() {
        return action;
    }
}
