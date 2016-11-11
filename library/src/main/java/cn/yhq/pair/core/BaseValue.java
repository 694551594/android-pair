package cn.yhq.pair.core;

import android.os.Bundle;



public abstract class BaseValue<V> {

  protected V value;

  public BaseValue() {}

  public void setValue(Object o) {
    value = convert(o);
  }

  public V getValue() {
    return value;
  }

  public abstract V convert(Object o);

  public Bundle toBundle() {
    return null;
  }

  public BaseValue<V> fromBundle(Bundle bundle) {
    return this;
  }
}
