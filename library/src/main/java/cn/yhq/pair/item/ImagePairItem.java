package cn.yhq.pair.item;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;

import cn.yhq.pair.R;

/**
 * Created by Administrator on 2016/11/15.
 */

public class ImagePairItem extends PairItem<ImagePairItem> {
    private int resId;
    private String url;
    private int width;
    private int height;

    public ImagePairItem(Context context, AttributeSet attrs) {
        super(context, Type.IMAGE, attrs);

        final TypedArray a = context.obtainStyledAttributes(
                attrs, R.styleable.ImagePairItem);

        this.resId = a.getResourceId(R.styleable.ImagePairItem_resId, 0);
        this.url = a.getString(R.styleable.ImagePairItem_url);
        this.width = a.getInt(R.styleable.ImagePairItem_width, 32);
        this.height = a.getInt(R.styleable.ImagePairItem_height, 32);

        a.recycle();
    }

    public ImagePairItem(Context context) {
        this(context, null);
    }

    public int getResId() {
        return resId;
    }

    public ImagePairItem setResId(int resId) {
        this.resId = resId;
        return this;
    }

    public String getUrl() {
        return url;
    }

    public ImagePairItem setUrl(String url) {
        this.url = url;
        return this;
    }

    public int getWidth() {
        return width;
    }

    public ImagePairItem setWidth(int width) {
        this.width = width;
        return this;
    }

    public int getHeight() {
        return height;
    }

    public ImagePairItem setHeight(int height) {
        this.height = height;
        return this;
    }
}
