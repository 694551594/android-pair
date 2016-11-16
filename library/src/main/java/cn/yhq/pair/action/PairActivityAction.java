package cn.yhq.pair.action;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

public class PairActivityAction extends PairIntentAction {

  public PairActivityAction(Context context, Class<?> activityClass) {
    super(new Intent(context, activityClass));
  }

  public PairActivityAction(Context context, Class<?> activityClass, Bundle bundle) {
    super(new Intent(context, activityClass).putExtras(bundle));
  }

}
