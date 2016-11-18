package cn.yhq.pair.item;

import android.content.Context;
import android.util.AttributeSet;

/**
 * Created by Administrator on 2016/11/15.
 */

public class SwitchPairItem extends TwoStatePairItem<SwitchPairItem> {

    public SwitchPairItem(Context context, AttributeSet attrs) {
        super(context, Type.SWITCH, attrs);
    }

    public SwitchPairItem(Context context) {
        this(context, null);
    }

}
