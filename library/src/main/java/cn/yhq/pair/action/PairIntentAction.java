package cn.yhq.pair.action;

import android.content.Context;
import android.content.Intent;

import cn.yhq.pair.item.PairItem;

public class PairIntentAction implements PairClickAction {
    private Intent intent;

    public PairIntentAction(Intent intent) {
        this.intent = intent;
    }

    @Override
    public void onClick(Context context, PairItem pairItem) {
        context.startActivity(intent);
    }

}
