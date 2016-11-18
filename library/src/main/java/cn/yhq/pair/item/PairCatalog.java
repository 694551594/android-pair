package cn.yhq.pair.item;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;

import cn.yhq.pair.R;

/**
 * Created by Administrator on 2016/11/15.
 */

public class PairCatalog extends BasePair<PairCatalog> {
    private String title;

    public PairCatalog(Context context) {
        this(context, null);
    }

    public PairCatalog(Context context, AttributeSet attrs) {
        super(context, Type.CATALOG, attrs);

        final TypedArray a = context.obtainStyledAttributes(
                attrs, R.styleable.PairCatalog);

        this.title = a.getString(R.styleable.PairCatalog_title);

        a.recycle();
    }

    public String getTitle() {
        return title;
    }

    public PairCatalog setTitle(String title) {
        this.title = title;
        this.invalidate();
        return this;
    }

}
