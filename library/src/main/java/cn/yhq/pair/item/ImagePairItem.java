package cn.yhq.pair.item;

/**
 * Created by Administrator on 2016/11/15.
 */

public class ImagePairItem extends PairItem<ImagePairItem> {
    private int resId;
    private String url;
    private int width;
    private int height;

    public ImagePairItem() {
        super(PairItemType.IMAGE);
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
