package cn.yhq.pair.item;


import cn.yhq.pair.intercept.FieldParserIntercept;

public class FieldPairItem extends BaseTextPairItem<FieldPairItem> {
    private Object entity;
    private String exp;
    private FieldParserIntercept intercept;

    public FieldPairItem() {
        super(PairItemType.FIELD);
        intercept = new FieldParserIntercept();
        this.addIntercept(intercept);
    }

    public Object getEntity() {
        return entity;
    }

    public FieldPairItem setEntity(Object entity) {
        this.entity = entity;
        this.intercept.setEntity(entity);
        return this;
    }

    public String getExp() {
        return exp;
    }

    public FieldPairItem setExp(String exp) {
        this.exp = exp;
        return this;
    }

}
