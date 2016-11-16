package cn.yhq.pair.item;

public class TextPairItem extends PairItem<TextPairItem> {
    private String text;

    public TextPairItem() {
        super(PairItemType.TEXT);
    }

    public String getText() {
        return text;
    }

    public TextPairItem setText(String text) {
        this.text = text;
        return this;
    }
}
