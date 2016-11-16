package cn.yhq.pair.action;

import android.content.Context;
import android.content.Intent;

import cn.yhq.pair.item.PairItem;

public class PairIntentAction implements PairClickAction {
    private Context context;
    private Intent intent;

    public PairIntentAction(Context context, Intent intent) {
        this.context = context;
        this.intent = intent;
    }

    @Override
    public boolean onClick(PairItem pairItem) {
        context.startActivity(intent);
        return false;
    }

}
