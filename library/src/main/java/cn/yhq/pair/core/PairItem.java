package cn.yhq.pair.core;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import cn.developer.sdk.pair.adapter.IPairItemProcessor;
import cn.developer.sdk.pair.core.BaseValueItem.ValueType;
import cn.developer.sdk.pair.core.FieldValueItem.FieldValue;
import cn.developer.sdk.pair.core.ImageValueItem.ImageValue;
import cn.developer.sdk.pair.core.SwitchValueItem.SwitchValue;
import cn.developer.sdk.pair.core.TextValueItem.TextValue;


/**
 * PairAdapter的item实体类
 * 
 * @author Yanghuiqiang 2015-6-26
 * 
 */
public class PairItem {
  // 值
  private BaseValueItem<? extends BaseValue<?>> valueItem;
  // 图片
  private int iconRes;
  private Drawable iconDrawable;
  // key值
  private String key;
  // 是否显示箭头
  private boolean indicator;
  private String description;
  private Object entity;

  public PairItem() {
    this.addPairItemProcessor(new FieldPairItemProcessor());
  }

  public PairItem process(IPairItemProcessor processor) {
    return processor.process(this);
  }

  private List<IPairItemProcessor> pairItemProcessors = new ArrayList<IPairItemProcessor>();

  public int getIconRes() {
    return iconRes;
  }

  public void setIconRes(int iconRes) {
    this.iconRes = iconRes;
  }

  public String getKey() {
    return key;
  }

  public void setKey(String key) {
    this.key = key;
  }

  public boolean isIndicator() {
    return indicator;
  }

  public void setIndicator(boolean indicator) {
    this.indicator = indicator;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  /**
   * @return the iconDrawable
   */
  public Drawable getIconDrawable() {
    return iconDrawable;
  }

  /**
   * @param iconDrawable the iconDrawable to set
   */
  public void setIconDrawable(Drawable iconDrawable) {
    this.iconDrawable = iconDrawable;
  }

  public Object getEntity() {
    return entity;
  }

  public void setEntity(Object entity) {
    this.entity = entity;
  }

  @SuppressWarnings("unchecked")
  public <T extends BaseValueItem<? extends BaseValue<?>>> T getValueItem() {
    return (T) valueItem;
  }

  public void setValueItem(BaseValueItem<? extends BaseValue<?>> valueItem) {
    this.valueItem = valueItem;
  }

  public void addPairItemProcessor(IPairItemProcessor pairItemProcessor) {
    this.pairItemProcessors.add(pairItemProcessor);
  }

  public void onClick(Context context) {
    valueItem.onClick(context);
  }

  public void onSavePreference(Context context) {
    valueItem.onSavePreference(context);
  }

  public void onValueChanged(Context context) {
    for (IPairItemProcessor pairItemProcessor : pairItemProcessors) {
      pairItemProcessor.process(this);
    }
    valueItem.onValueChanged(context);
  }

  public static PairItem makeEmptyPair() {
    PairItem pairItem = new PairItem();
    pairItem.valueItem = EmptyValueItem.createValueItem();
    return pairItem;
  }

  public static PairItem makeTextPair(String key, String value, String description) {
    return makeTextPair(0, key, value, description, false);
  }

  public static PairItem makeTextPair(String key) {
    return makeTextPair(0, key);
  }

  public static PairItem makeTextPair(String key, String value) {
    return makeTextPair(0, key, value, null, false);
  }

  public static PairItem makeTextPair(int iconRes, String key) {
    return makeTextPair(iconRes, key, null);
  }

  public static PairItem makeTextPair(int iconRes, String key, boolean indicator) {
    return makeTextPair(iconRes, key, null, null, indicator);
  }

  public static PairItem makeTextPair(int iconRes, String key, String value) {
    return makeTextPair(iconRes, key, value, null, false);
  }

  public static PairItem makeTextPair(int iconRes, String key, String value, String description,
      boolean indicator) {
    PairItem pairItem = new PairItem();
    pairItem.key = key;
    pairItem.valueItem = TextValueItem.createValueItem(value);
    pairItem.iconRes = iconRes;
    pairItem.indicator = indicator;
    pairItem.description = description;
    return pairItem;
  }

  public static PairItem makeTextPair(Drawable iconDrawable, String key) {
    return makeTextPair(iconDrawable, key, null);
  }

  public static PairItem makeTextPair(Drawable iconDrawable, String key, boolean indicator) {
    return makeTextPair(iconDrawable, key, null, null, indicator);
  }

  public static PairItem makeTextPair(Drawable iconDrawable, String key, String value) {
    return makeTextPair(iconDrawable, key, value, null, false);
  }

  public static PairItem makeTextPair(Drawable iconDrawable, String key, String value,
      String description, boolean indicator) {
    PairItem pairItem = new PairItem();
    pairItem.key = key;
    pairItem.valueItem = TextValueItem.createValueItem(value);
    pairItem.iconDrawable = iconDrawable;
    pairItem.indicator = indicator;
    pairItem.description = description;
    return pairItem;
  }

  public static PairItem makeImagePair(String key, int resId, int width, int height) {
    return makeImagePair(key, resId, null, null, width, height);
  }

  public static PairItem makeImagePair(String key, String url, int width, int height) {
    return makeImagePair(key, 0, url, null, width, height);
  }

  public static PairItem makeImagePair(String key, int resId, String url, int width, int height) {
    return makeImagePair(key, resId, url, null, width, height);
  }

  public static PairItem makeImagePair(String key, int resId, String url, String description,
      int width, int height) {
    PairItem pairItem = new PairItem();
    pairItem.key = key;
    pairItem.description = description;
    pairItem.valueItem = ImageValueItem.createValueItem(resId, url, width, height);
    return pairItem;
  }

  public static PairItem makeImagePair(String key, int resId) {
    return makeImagePair(key, resId, null, null);
  }

  public static PairItem makeImagePair(String key, String url) {
    return makeImagePair(key, 0, url, null);
  }

  public static PairItem makeImagePair(String key, int resId, String url) {
    return makeImagePair(key, resId, url, null);
  }

  public static PairItem makeImagePair(String key, int resId, String url, String description) {
    return makeImagePair(key, resId, url, description, 0, 0);
  }

  public static PairItem makeSwitchPair(String key, boolean checked) {
    return makeSwitchPair(key, checked, null);
  }

  public static PairItem makeSwitchPair(String key, boolean checked, String description) {
    PairItem pairItem = new PairItem();
    pairItem.key = key;
    pairItem.description = description;
    pairItem.valueItem = SwitchValueItem.createValueItem(checked);
    return pairItem;
  }

  public PairItem setText(String text) {
    ValueType valueType = this.getValueItem().getValueType();
    if (valueType == ValueType.TEXT) {
      ((TextValue) this.getValueItem().getValue()).setText(text);
    } else if (valueType == ValueType.FIELD) {
      ((FieldValue) this.getValueItem().getValue()).setText(text);
    }
    return this;
  }

  public PairItem setChecked(boolean checked) {
    ((SwitchValue) this.getValueItem().getValue()).setChecked(checked);
    return this;
  }

  public PairItem setImage(int resId) {
    ((ImageValue) this.getValueItem().getValue()).setResId(resId);
    return this;
  }

  public PairItem setImage(String url) {
    ((ImageValue) this.getValueItem().getValue()).setUrl(url);
    return this;
  }

  public void bindEntity(Object entity) {
    this.entity = entity;
  }

  public Bundle toBundle() {
    Bundle bundle = new Bundle();
    if (valueItem != null) {
      bundle.putBundle("valueItem", valueItem.toBundle());
    }
    return bundle;
  }

  public PairItem fromBundle(Bundle bundle) {
    if (bundle != null) {
      this.valueItem.fromBundle(bundle.getBundle("valueItem"));
    }
    return this;
  }

}
