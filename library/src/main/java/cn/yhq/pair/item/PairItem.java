package cn.yhq.pair.item;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;

import cn.yhq.pair.R;
import cn.yhq.pair.action.PairAction;
import cn.yhq.pair.action.PairClickAction;
import cn.yhq.pair.action.PairPreferenceAction;

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

    public void onSavePreference(Object value) {
        if (action != null) {
            if (action instanceof PairPreferenceAction) {
                ((PairPreferenceAction) action).onSavePreference(value);
            }
        }
    }

    public Object getPreference() {
        if (action != null) {
            if (action instanceof PairPreferenceAction) {
                return ((PairPreferenceAction) action).getPreference();
            }
        }
        return null;
    }

    public String getDescription() {
        return description;
    }

    public T setDescription(String description) {
        this.description = description;
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
        return (T) this;
    }

    public T setIcon(int icon) {
        this.icon = icon;
        return (T) this;
    }

    public String getKey() {
        return key;
    }

    public T setKey(String key) {
        this.key = key;
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

    public T setPreferenceAction(PairPreferenceAction action) {
        this.setAction(action);
        return (T) this;
    }

}
