package cn.yhq.pair.action;


import cn.yhq.pair.item.PairItem;


public interface PairClickAction extends PairAction {

    boolean onClick(PairItem pairItem);

}
