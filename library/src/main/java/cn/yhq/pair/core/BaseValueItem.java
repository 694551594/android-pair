package cn.yhq.pair.core;

import android.content.Context;
import android.os.Bundle;

public class BaseValueItem<T extends BaseValue<?>> {
  // 类型
  private ValueType valueType;
  private T value;
  private PairAction pairAction;

  public static enum ValueType {
    TEXT, SWITCH, EMPTY, FIELD, IMAGE, CHECKBOX
  }

  public BaseValueItem(ValueType valueType) {
    this.valueType = valueType;
  }

  public ValueType getValueType() {
    return valueType;
  }

  @SuppressWarnings("unchecked")
  public <V extends BaseValue<?>> V getValue() {
    return (V) value;
  }

  public void setValue(T value) {
    this.value = value;
  }

  public void onClick(Context context) {
    if (pairAction != null) {
      pairAction.onClick(context);
    }
  }

  public void onSavePreference(Context context) {
    if (pairAction != null) {
      pairAction.onSavePreference(context);
    }
  }

  public void onValueChanged(Context context) {
    if (pairAction != null) {
      Object o = pairAction.getPreference(context);
      value.setValue(o);
    }
  }

  public PairAction getPairAction() {
    return pairAction;
  }

  public void setPairAction(PairAction pairAction) {
    this.pairAction = pairAction;
  }

  public Bundle toBundle() {
    Bundle bundle = new Bundle();
    if (value != null) {
      bundle.putBundle("value", value.toBundle());
    }
    return bundle;
  }

  public BaseValueItem<T> fromBundle(Bundle bundle) {
    if (bundle != null) {
      this.value.fromBundle(bundle.getBundle("value"));
    }
    return this;
  }

}
