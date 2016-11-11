package cn.yhq.pair.core;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;

import cn.developer.sdk.pair.adapter.IPairItemProcessor;


public class PairGroup {
  private String text;
  private List<PairItem> pairItems;
  private boolean isVisiable = true;

  private PairGroup(boolean isVisiable) {
    this(null, isVisiable);
  }

  private PairGroup(String text, boolean isVisiable) {
    pairItems = new ArrayList<PairItem>();
    this.isVisiable = isVisiable;
    this.text = text;
  }

  private PairGroup() {
    this(true);
  }

  private PairGroup(String text) {
    this(text, true);
  }

  public static PairGroup createPairGroup(String text) {
    return new PairGroup(text);
  }

  public static PairGroup createPairGroup() {
    return new PairGroup();
  }

  public static PairGroup createPairGroup(boolean isVisiable) {
    return new PairGroup(isVisiable);
  }

  public static PairGroup createPairGroup(String text, boolean isVisiable) {
    return new PairGroup(text, isVisiable);
  }

  public String getText() {
    return text;
  }

  public PairGroup setText(String text) {
    this.text = text;
    return this;
  }

  public List<PairItem> getPairItems() {
    return pairItems;
  }

  public void setPairItems(List<PairItem> pairItems) {
    this.pairItems = pairItems;
  }

  public boolean isVisiable() {
    return isVisiable;
  }

  public void setVisiable(boolean isVisiable) {
    this.isVisiable = isVisiable;
  }

  public PairGroup addPairItem(PairItem pairItem) {
    this.pairItems.add(pairItem);
    return this;
  }

  public PairItem getPairItem(int position) {
    return this.pairItems.get(position);
  }

  public <V extends BaseValue<?>> V getPairValue(int position) {
    return this.getPairValueItem(position).getValue();
  }

  public <T extends BaseValueItem<? extends BaseValue<?>>> T getPairValueItem(int position) {
    return this.getPairItem(position).getValueItem();
  }

  public void addPairItemProcessor(IPairItemProcessor pairItemProcessor) {
    for (PairItem pairItem : this.getPairItems()) {
      pairItem.addPairItemProcessor(pairItemProcessor);
    }
  }

  public Bundle toBundle() {
    Bundle bundle = new Bundle();
    Bundle pairItemBundle = new Bundle();
    for (int i = 0; i < pairItems.size(); i++) {
      pairItemBundle.putBundle(String.valueOf(i), pairItems.get(i).toBundle());
    }
    bundle.putBundle("pairItem", pairItemBundle);
    return bundle;
  }

  public PairGroup fromBundle(Bundle bundle) {
    Bundle pairItemBundle = bundle.getBundle("pairItem");
    for (int i = 0; i < this.pairItems.size(); i++) {
      this.pairItems.get(i).fromBundle(pairItemBundle.getBundle(String.valueOf(i)));
    }
    return this;
  }
}
