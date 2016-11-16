package cn.yhq.pair.item;

import android.graphics.drawable.Drawable;

import java.util.ArrayList;
import java.util.List;

import cn.yhq.pair.action.PairAction;
import cn.yhq.pair.action.PairClickAction;
import cn.yhq.pair.action.PairPreferenceAction;

/**
 * Created by Administrator on 2016/11/15.
 */

public class PairItem<T extends PairItem<T>> {
    private int icon;
    private Drawable iconDrawable;
    private String key;
    private PairAction action;
    private PairItemType type;
    private String description;
    private List<PairIntercept> intercepts = new ArrayList<>();
    private OnInvalidateListener onInvalidateListener;

    public PairItem(PairItemType type) {
        this.type = type;
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

    protected void invalidate() {
        if (onInvalidateListener != null) {
            onInvalidateListener.onInvalidate();
        }
    }

    public void setOnInvalidateListener(OnInvalidateListener onInvalidateListener) {
        this.onInvalidateListener = onInvalidateListener;
    }

    public T intercept() {
        try {
            this.getDataWithInterceptorChain((T) this);
        } catch (Exception e) {
        }
        return (T) this;
    }

    private T getDataWithInterceptorChain(T item) throws Exception {
        PairIntercept.Chain<T> chain = new DefaultIntercept(0, item);
        return chain.intercept(item);
    }

    class DefaultIntercept implements PairIntercept.Chain<T> {
        private final int index;
        private T item;

        DefaultIntercept(int index, T item) {
            this.index = index;
            this.item = item;
        }

        @Override
        public T getItem() {
            return item;
        }

        @Override
        public T intercept(T item) throws Exception {
            if (index < intercepts.size()) {
                PairIntercept.Chain chain = new DefaultIntercept(index + 1, item);
                PairIntercept intercept = intercepts.get(index);
                T interceptData = (T) intercept.intercept(chain);

                if (interceptData == null) {
                    throw new NullPointerException("intercept " + intercept + " returned null");
                }

                return interceptData;
            }
            return item;
        }
    }

    public PairItem<T> addIntercept(PairIntercept intercept) {
        this.intercepts.add(intercept);
        return this;
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

    public PairAction getAction() {
        return action;
    }

    public T setAction(PairAction action) {
        this.action = action;
        return (T) this;
    }

    public PairItemType getType() {
        return type;
    }

}
