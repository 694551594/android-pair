package cn.yhq.pair.core;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.os.Bundle;
import cn.developer.sdk.pair.adapter.ExpandablePairGroupAdapter;
import cn.developer.sdk.pair.adapter.IPairGroupProcessor;
import cn.developer.sdk.pair.adapter.IPairItemProcessor;

public class PairGroupList extends ArrayList<PairGroup> {
  private static final long serialVersionUID = -8786816015304845976L;

  private Context mContext;
  private PairGroup mCurrentPairGroup;
  private List<IPairGroupProcessor> valueItemProcessors = new ArrayList<IPairGroupProcessor>();

  public PairGroupList(Context context) {
    super();
    this.mContext = context;
  }

  public static PairGroupList newPairGroupList(Context context) {
    return new PairGroupList(context);
  }

  public PairGroupList addPairGroup(PairGroup pairGroup) {
    this.add(pairGroup);
    return this;
  }

  public PairGroup getCurrentPairGroup() {
    return mCurrentPairGroup;
  }

  public void setGroupVisiable(boolean visiable) {
    mCurrentPairGroup.setVisiable(visiable);
  }

  public PairGroupList newPairGroup(boolean visiable) {
    mCurrentPairGroup = PairGroup.createPairGroup();
    mCurrentPairGroup.setVisiable(visiable);
    this.add(mCurrentPairGroup);
    return this;
  }

  public PairGroupList newPairGroup() {
    mCurrentPairGroup = PairGroup.createPairGroup();
    this.add(mCurrentPairGroup);
    return this;
  }

  public PairGroupList newPairGroup(String text) {
    mCurrentPairGroup = PairGroup.createPairGroup(text);
    this.add(mCurrentPairGroup);
    return this;
  }

  public PairGroupList addPairItem(PairItem pairItem) {
    addPairItem(mCurrentPairGroup, pairItem);
    return this;
  }

  public PairGroupList addPairItem(PairGroup pairGroup, PairItem pairItem) {
    if (!this.contains(pairGroup)) {
      this.add(pairGroup);
    }
    for (IPairGroupProcessor pairGroupProcessor : valueItemProcessors) {
      pairItem =
          pairGroupProcessor.processor(this.size() - 1, pairGroup, pairGroup.getPairItems().size(),
              pairItem);
    }
    pairGroup.addPairItem(pairItem);
    return this;
  }

  public void invalidate() {
    for (int i = 0; i < this.size(); i++) {
      for (int j = 0; j < this.get(i).getPairItems().size(); j++) {
        PairItem pairItem = this.get(i).getPairItems().get(j);
        onValueChanged(i, j);
        for (IPairGroupProcessor pairGroupProcessor : valueItemProcessors) {
          pairGroupProcessor.processor(i, this.get(i), j, pairItem);
        }
      }
    }
  }

  public void bindEntity(int groupPosition, int childPosition, Object entity) {
    PairItem pairItem = this.get(groupPosition).getPairItems().get(childPosition);
    pairItem.bindEntity(entity);
  }

  public void bindEntity(Object entity) {
    for (int i = 0; i < this.size(); i++) {
      for (int j = 0; j < this.get(i).getPairItems().size(); j++) {
        bindEntity(i, j, entity);
      }
    }
  }

  public void onClick(int groupPosition, int childPosition) {
    PairItem pairItem = this.get(groupPosition).getPairItems().get(childPosition);
    pairItem.onClick(mContext);
  }

  public void onSavePreference(int groupPosition, int childPosition) {
    PairItem pairItem = this.get(groupPosition).getPairItems().get(childPosition);
    pairItem.onSavePreference(mContext);
  }

  public void onValueChanged(int groupPosition, int childPosition) {
    PairItem pairItem = this.get(groupPosition).getPairItems().get(childPosition);
    pairItem.onValueChanged(mContext);
  }

  public PairGroupList addPairGroupProcessor(IPairGroupProcessor pairItemProcessor) {
    valueItemProcessors.add(pairItemProcessor);
    return this;
  }

  public PairGroupList addPairItemProcessor(IPairItemProcessor pairItemProcessor) {
    for (PairGroup pairGroup : this) {
      pairGroup.addPairItemProcessor(pairItemProcessor);
    }
    return this;
  }

  public ExpandablePairGroupAdapter buildPairAdapter() {
    ExpandablePairGroupAdapter adapter = new ExpandablePairGroupAdapter(mContext, this);
    return adapter;
  }

  public Bundle toBundle() {
    Bundle bundle = new Bundle();
    Bundle pariGroupBundle = new Bundle();
    for (int i = 0; i < this.size(); i++) {
      pariGroupBundle.putBundle(String.valueOf(i), this.get(i).toBundle());
    }
    bundle.putBundle("pairGroup", pariGroupBundle);
    return bundle;
  }

  public PairGroupList fromBundle(Bundle bundle) {
    Bundle pariGroupBundle = bundle.getBundle("pairGroup");
    for (int i = 0; i < this.size(); i++) {
      this.get(i).fromBundle(pariGroupBundle.getBundle(String.valueOf(i)));
    }
    return this;
  }

}
