package cn.yhq.pair.item;


import android.content.Context;
import android.util.AttributeSet;

import cn.yhq.pair.interceptor.FieldParserInterceptor;

public class FieldPairItem extends BaseTextPairItem<FieldPairItem> {
    private Object entity;
    private String exp;

    public FieldPairItem(Context context, AttributeSet attrs) {
        super(context, Type.FIELD, attrs);
        this.addInterceptor(new FieldParserInterceptor());
    }

    public FieldPairItem(Context context) {
        this(context, null);
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
