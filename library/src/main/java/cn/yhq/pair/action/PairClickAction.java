package cn.yhq.pair.action;


import cn.yhq.pair.item.Pair;

public interface PairClickAction<T extends Pair<T>> extends PairAction {

    boolean onClick(T pair);

}
