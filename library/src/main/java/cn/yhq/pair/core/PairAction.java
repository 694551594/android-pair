package cn.yhq.pair.core;

import java.io.Serializable;

import android.content.Context;


public class PairAction implements Serializable {
  private static final long serialVersionUID = 2484696137881781159L;
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
