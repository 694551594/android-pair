package cn.yhq.pair.item;

import android.content.Context;
import android.util.AttributeSet;

/**
 * Created by Administrator on 2016/11/15.
 */

public class CheckPairItem extends TwoStatePairItem<CheckPairItem> {

    public CheckPairItem(Context context, AttributeSet attrs) {
        super(context, Type.CHECKBOX, attrs);
    }

    public CheckPairItem(Context context) {
        this(context, null);
    }
}
