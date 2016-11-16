package cn.yhq.pair.action;


public interface PairPreferenceAction extends PairAction {

    void onSavePreference(int key, Object value);

    Object getPreference(int key);

}
