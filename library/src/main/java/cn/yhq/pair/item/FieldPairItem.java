package cn.yhq.pair.item;

import cn.yhq.pair.PairItemType;


public class FieldPairItem extends PairItem<FieldPairItem> {
    private Object entity;
    private String exp;
    private String formatClass;
    private String text;

    public FieldPairItem() {
        super(PairItemType.FIELD);
    }

    public Object getEntity() {
        return entity;
    }

    public void setEntity(Object entity) {
        this.entity = entity;
    }

    public String getExp() {
        return exp;
    }

    public void setExp(String exp) {
        this.exp = exp;
    }

    public String getFormatClass() {
        return formatClass;
    }

    public void setFormatClass(String formatClass) {
        this.formatClass = formatClass;
    }

    public String getText() {
        return text;
    }

    public FieldPairItem setText(String text) {
        this.text = text;
        return this;
    }
}
