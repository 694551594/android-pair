package cn.yhq.pair.adapter;

import cn.developer.sdk.pair.core.PairGroup;
import cn.developer.sdk.pair.core.PairItem;

public interface IPairGroupProcessor {
  public PairItem processor(int groupIndex, PairGroup pairGroup, int childIndex, PairItem pairItem);
}
