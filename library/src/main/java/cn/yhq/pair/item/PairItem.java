package cn.yhq.pair.item;

import android.graphics.drawable.Drawable;

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

    public PairItem(Type type) {
        super(type);
    }

    public boolean onClick() {
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
