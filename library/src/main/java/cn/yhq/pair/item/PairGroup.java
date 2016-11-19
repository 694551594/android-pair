package cn.yhq.pair.item;

import android.content.Context;
import android.util.AttributeSet;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yanghuiqiang on 2016/11/18.
 */

public class PairGroup extends Pair<PairGroup> {
    private List<IPair> pairs = new ArrayList<>();

    public PairGroup(Context context) {
        this(context, null);
    }

    public PairGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void addPair(IPair pair) {
        this.pairs.add(pair);
    }

    public void addAllPair(List<IPair> pairs) {
        this.pairs.addAll(pairs);
    }

    public <T extends IPair> T getPairByIndex(int index) {
        return (T) getAllPairs().get(index);
    }

    protected List<IPair> getPairs() {
        return pairs;
    }

    private void _getPairs(List<IPair> list, PairGroup pairGroup) {
        for (IPair pair : pairGroup.pairs) {
            if (pair instanceof PairGroup) {
                if (pair instanceof PairCatalog) {
                    list.add(pair);
                }
                _getPairs(list, (PairGroup) pair);
            } else {
                list.add(pair);
            }
        }
    }

    public List<IPair> getAllPairs() {
        List<IPair> list = new ArrayList<>();
        _getPairs(list, this);
        return list;
    }

    public <T extends IPair> T getPairById(int id) {
        for (IPair pair : getAllPairs()) {
            if (pair.getId() == id) {
                return (T) pair;
            }
        }
        return null;
    }

    @Override
    public int getItemViewLayoutId() {
        return 0;
    }

}
