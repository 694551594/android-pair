package cn.yhq.pair.item;

import android.content.Context;
import android.util.AttributeSet;

import cn.yhq.pair.action.PairPreferenceAction;

/**
 * Created by Yanghuiqiang on 2016/11/18.
 */

public class PreferencePairItem<T extends PreferencePairItem<T>> extends PairItem<T> {

    public PreferencePairItem(Context context, Type type, AttributeSet attrs) {
        super(context, type, attrs);
    }

    public void onSavePreference(Object value) {
        if (this.getAction() != null) {
            if (this.getAction() instanceof PairPreferenceAction) {
                ((PairPreferenceAction) this.getAction()).onSavePreference(getContext(), value);
            }
        }
    }

    public Object getPreference() {
        if (this.getAction() != null) {
            if (this.getAction() instanceof PairPreferenceAction) {
                return ((PairPreferenceAction) this.getAction()).getPreference(getContext());
            }
        }
        return null;
    }

    public T setPreferenceAction(PairPreferenceAction action) {
        this.setAction(action);
        return (T) this;
    }
}
