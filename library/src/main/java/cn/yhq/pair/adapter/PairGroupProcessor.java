package cn.yhq.pair.adapter;

import android.content.Context;

public abstract class PairGroupProcessor implements IPairGroupProcessor {
  protected Context mContext;

  public void setContext(Context context) {
    this.mContext = context;
  }
}
