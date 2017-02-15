package cn.yhq.pair.action;

import android.content.Intent;

import cn.yhq.pair.item.Pair;

public class PairIntentAction<T extends Pair<T>> implements PairClickAction<T> {
    private Intent intent;

    public PairIntentAction(Intent intent) {
        this.intent = intent;
    }

    @Override
    public boolean onClick(T pairItem) {
        pairItem.getContext().startActivity(intent);
        return false;
    }

}
