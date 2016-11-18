package cn.yhq.pair.item;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;

import cn.yhq.pair.R;
import cn.yhq.pair.action.PairAction;
import cn.yhq.pair.action.PairClickAction;

/**
 * Created by Administrator on 2016/11/15.
 */

public class PairItem<T extends PairItem<T>> extends BasePair<T> {
    private int icon;
    private Drawable iconDrawable;
    private String key;
    private PairAction action;
    private String description;

    public PairItem(Context context, Type type, AttributeSet attrs) {
        super(context, type, attrs);

        final TypedArray a = context.obtainStyledAttributes(
                attrs, R.styleable.Pair);

        this.icon = a.getResourceId(R.styleable.Pair_icon, 0);
        this.key = a.getString(R.styleable.Pair_key);
        this.description = a.getString(R.styleable.Pair_description);

        a.recycle();
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
        this.invalidate();
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
        this.invalidate();
        return (T) this;
    }

    public T setIcon(int icon) {
        this.icon = icon;
        this.invalidate();
        return (T) this;
    }

    public String getKey() {
        return key;
    }

    public T setKey(String key) {
        this.key = key;
        this.invalidate();
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
