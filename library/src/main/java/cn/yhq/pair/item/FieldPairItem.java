package cn.yhq.pair.item;


import cn.yhq.pair.intercept.FieldParserIntercept;

public class FieldPairItem extends BaseTextPairItem<FieldPairItem> {
    private Object entity;
    private String exp;

    public FieldPairItem() {
        super(Type.FIELD);
        this.addIntercept(new FieldParserIntercept());
    }

    public Object getEntity() {
        return entity;
    }

    public FieldPairItem setEntity(Object entity) {
        this.entity = entity;
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
