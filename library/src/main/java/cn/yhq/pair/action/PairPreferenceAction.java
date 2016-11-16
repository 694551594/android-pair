package cn.yhq.pair.action;


import android.content.Context;

import cn.yhq.pair.utils.PreferencesUtils;

public class PairPreferenceAction implements PairAction {
    private String key;

    public PairPreferenceAction(String key) {
        this.key = key;
    }

    public boolean onSavePreference(Context context, Object value) {
        return PreferencesUtils.savePreferences(context, key, value);
    }

    public Object getPreference(Context context) {
        return PreferencesUtils.getAllValue(context).get(key);
    }

}
