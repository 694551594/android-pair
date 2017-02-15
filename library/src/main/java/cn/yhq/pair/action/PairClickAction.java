package cn.yhq.pair.action;


public interface PairClickAction<T> extends PairAction {

    boolean onClick(T pair);

}
