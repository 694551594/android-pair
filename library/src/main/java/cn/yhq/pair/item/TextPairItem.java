package cn.yhq.pair.item;

import android.content.Context;
import android.util.AttributeSet;

public class TextPairItem extends BaseTextPairItem<TextPairItem> {

    public TextPairItem(Context context, AttributeSet attrs) {
        super(context, Type.TEXT, attrs);
    }

    public TextPairItem(Context context) {
        this(context, null);
    }

}
