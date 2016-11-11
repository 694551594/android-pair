package cn.yhq.pair.core;

import android.os.Bundle;



public class FieldValueItem extends BaseValueItem<FieldValueItem.FieldValue> {

  public FieldValueItem() {
    super(ValueType.FIELD);
  }

  public static class FieldInfo {
    private Object entity;
    private String exp;
    private String formatClass;
    private String text;

    public String getText() {
      return text;
    }

    public void setText(String text) {
      this.text = text;
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

    public Object getEntity() {
      return entity;
    }

    public void setEntity(Object entity) {
      this.entity = entity;
    }



  }

  public static class FieldValue extends BaseValue<FieldInfo> {

    public FieldValue() {
      this.value = new FieldInfo();
    }

    public String getExp() {
      return value.exp;
    }

    public void setExp(String exp) {
      value.exp = exp;
    }

    public String getFormatClass() {
      return value.formatClass;
    }

    public void setFormatClass(String formatClass) {
      value.formatClass = formatClass;
    }

    public String getText() {
      return value.text;
    }

    public void setText(String text) {
      value.text = text;
    }

    @Override
    public FieldInfo convert(Object o) {
      return null;
    }

    @Override
    public Bundle toBundle() {
      Bundle bundle = new Bundle();
      bundle.putString("text", value.getText());
      return bundle;
    }

    @Override
    public FieldValue fromBundle(Bundle bundle) {
      setText(bundle.getString("text"));
      return this;
    }

  }

  public static FieldValueItem createValueItem(String exp) {
    return createValueItem(exp, "string");
  }

  public static FieldValueItem createValueItem(String exp, String formatClass) {
    FieldValueItem fieldValueItem = new FieldValueItem();
    FieldValue fieldValue = new FieldValue();
    fieldValue.setExp(exp);
    fieldValue.setFormatClass(formatClass);
    fieldValueItem.setValue(fieldValue);
    return fieldValueItem;
  }
}
