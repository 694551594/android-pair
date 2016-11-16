package cn.yhq.pair.action;


import android.content.Context;

import cn.yhq.pair.item.PairItem;


public interface PairClickAction extends PairAction {

    boolean onClick(Context context, PairItem pairItem);

}
