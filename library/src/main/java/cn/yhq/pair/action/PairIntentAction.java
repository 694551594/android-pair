package cn.yhq.pair.action;

import android.content.Intent;

import cn.yhq.pair.item.PairItem;

public class PairIntentAction implements PairClickAction {
    private Intent intent;

    public PairIntentAction(Intent intent) {
        this.intent = intent;
    }

    @Override
    public boolean onClick(PairItem pairItem) {
        pairItem.getContext().startActivity(intent);
        return false;
    }

}
