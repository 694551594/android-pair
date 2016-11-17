package cn.yhq.pair.item;

/**
 * Created by Yanghuiqiang on 2016/11/17.
 */

public class BaseTextPairItem<T extends BaseTextPairItem<T>> extends PairItem<T> {
    private String text;

    public BaseTextPairItem(PairItemType type) {
        super(type);
    }

    public String getText() {
        return text;
    }

    public T setText(String text) {
        this.text = text;
        return (T) this;
    }

    public T setText(Object text) {
        this.text = text.toString();
        return (T) this;
    }

}
