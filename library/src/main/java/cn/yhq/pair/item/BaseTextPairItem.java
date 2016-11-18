package cn.yhq.pair.item;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;

import cn.yhq.pair.R;

/**
 * Created by Yanghuiqiang on 2016/11/17.
 */

public class BaseTextPairItem<T extends BaseTextPairItem<T>> extends PairItem<T> {
    private String text;

    public BaseTextPairItem(Context context, Type type, AttributeSet attrs) {
        super(context, type, attrs);

        final TypedArray a = context.obtainStyledAttributes(
                attrs, R.styleable.BaseTextPairItem);

        this.text = a.getString(R.styleable.BaseTextPairItem_text);

        a.recycle();
    }

    public String getText() {
        return text;
    }

    public T setText(String text) {
        this.text = text;
        this.invalidate();
        return (T) this;
    }

    public T setText(Object text) {
        this.text = text.toString();
        return setText(this.text);
    }

}
