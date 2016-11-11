package cn.yhq.pair.core;

import android.os.Bundle;



public class EmptyValueItem extends BaseValueItem<EmptyValueItem.EmptyValue> {

  public EmptyValueItem() {
    super(ValueType.EMPTY);
  }

  public static class EmptyValue extends BaseValue<Void> {

    public EmptyValue() {}

    @Override
    public Void convert(Object o) {
      return null;
    }

    @Override
    public Bundle toBundle() {
      return null;
    }

    @Override
    public BaseValue<Void> fromBundle(Bundle bundle) {
      return null;
    }


  }

  public static EmptyValueItem createValueItem() {
    return new EmptyValueItem();
  }

}
