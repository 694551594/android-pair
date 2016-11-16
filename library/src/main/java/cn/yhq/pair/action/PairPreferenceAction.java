package cn.yhq.pair.action;


import android.content.Context;

import cn.yhq.pair.utils.PreferencesUtils;

public class PairPreferenceAction implements PairAction {
    private String key;
    private Context context;

    public PairPreferenceAction(Context context, String key) {
        this.key = key;
        this.context = context;
    }

    public boolean onSavePreference(Object value) {
        return PreferencesUtils.savePreferences(context, key, value);
    }

    public Object getPreference() {
        return PreferencesUtils.getAllValue(context).get(key);
    }

}
