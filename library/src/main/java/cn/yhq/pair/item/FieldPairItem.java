package cn.yhq.pair.item;

import cn.yhq.pair.PairItemType;


public class FieldPairItem extends TextPairItem {
    private Object entity;
    private String exp;
    private String formatClass;

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
}
