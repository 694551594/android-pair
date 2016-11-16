package cn.yhq.pair.action;

import android.content.Context;
import android.content.Intent;

public class PairActivityAction extends PairIntentAction {
  private Class<?> activityClass;

  public PairActivityAction(Context context, Class<?> activityClass) {
    super(new Intent(context, activityClass));
  }

}
