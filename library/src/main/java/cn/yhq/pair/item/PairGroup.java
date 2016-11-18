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

    public <T extends IPair> T getPair(int id) {
        for (IPair pair : pairs) {
            if (pair.getId() == id) {
                return (T) pair;
            }
        }
        return null;
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

    private void intercept(int id) {
        for (IPair pair : pairs) {
            if (pair.getId() == id) {
                pair.intercept();
            }
        }
    }

    private void invalidate(int id) {
        for (IPair pair : pairs) {
            if (pair.getId() == id) {
                pair.invalidate();
            }
        }
    }

    public void refresh(int id) {
        intercept(id);
        invalidate(id);
    }

    public void refresh() {
        intercept();
        invalidate();
    }
}
