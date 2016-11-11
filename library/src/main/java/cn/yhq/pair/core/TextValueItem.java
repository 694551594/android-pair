package cn.yhq.pair.core;

import android.os.Bundle;


public class TextValueItem extends BaseValueItem<TextValueItem.TextValue> {

  public TextValueItem() {
    super(ValueType.TEXT);
  }

  public static TextValueItem createValueItem(String text) {
    TextValueItem textValueItem = new TextValueItem();
    TextValue textValue = new TextValue();
    textValue.setText(text);
    textValueItem.setValue(textValue);
    return textValueItem;
  }

  public static class TextValue extends BaseValue<String> {

    public TextValue() {}

    public String getText() {
      return this.getValue();
    }

    public void setText(String text) {
      this.setValue(text);
    }

    @Override
    public String convert(Object o) {
      return (String) o;
    }

    @Override
    public Bundle toBundle() {
      Bundle bundle = new Bundle();
      bundle.putString("text", value);
      return bundle;
    }

    @Override
    public TextValue fromBundle(Bundle bundle) {
      setText(bundle.getString("text"));
      return this;
    }
  }

}
