package cn.yhq.pair.core;

import android.content.Context;


public class PairAction {
  protected PairItem pairItem;

  public PairAction(PairItem pairItem) {
    this.pairItem = pairItem;
  }

  public void onClick(Context context) {

  }

  public Object getPreference(Context context) {
    return null;
  }

  public void onSavePreference(Context context) {

  }
}
