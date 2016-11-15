package cn.yhq.pair.item;

import cn.yhq.pair.PairItemType;

public class TextPairItem extends PairItem {
    private String text;

    public TextPairItem() {
        super(PairItemType.TEXT);
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
