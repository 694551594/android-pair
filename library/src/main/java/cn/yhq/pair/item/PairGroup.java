package cn.yhq.pair.item;

import android.content.Context;
import android.util.AttributeSet;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yanghuiqiang on 2016/11/18.
 */

public class PairGroup extends BasePair<PairGroup> {
    private List<IPair> pairs = new ArrayList<>();

    public PairGroup(Context context, AttributeSet attrs) {
        super(context, Type.GROUP, attrs);
    }

    public void addPair(IPair pair) {
        this.pairs.add(pair);
    }

    public void addAllPair(List<IPair> pairs) {
        this.pairs.addAll(pairs);
    }

    @Override
    protected void setOnInvalidateListener(final OnInvalidateListener listener) {
        for (IPair pair : pairs) {
            ((BasePair) pair).setOnInvalidateListener(listener);
        }
    }

    public List<IPair> getPairs() {
        return pairs;
    }

    @Override
    public void intercept() {
        for (IPair pair : pairs) {
            pair.intercept();
        }
    }

    @Override
    public void invalidate() {
        for (IPair pair : pairs) {
            pair.invalidate();
        }
    }

    private void intercept(int index) {
        pairs.get(index).intercept();
    }

    private void invalidate(int index) {
        pairs.get(index).invalidate();
    }

    public void refresh(int index) {
        intercept(index);
        invalidate(index);
    }

    public void refresh() {
        intercept();
        invalidate();
    }
}
