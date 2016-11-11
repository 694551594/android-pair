package cn.yhq.pair.adapter;

import cn.developer.sdk.pair.core.PairItem;

public class PairItemTypeProvider {
  public final static int TYPE_TEXT = 0;
  public final static int TYPE_SWITCHBUTTON = 1;
  public final static int TYPE_CHECKBOX = 4;
  public final static int TYPE_EMPTY = 2;
  public final static int TYPE_IMAGE = 3;

  public static int getItemViewType(int position, PairItem item) {
    switch (item.getValueItem().getValueType()) {
      case CHECKBOX:
        return TYPE_CHECKBOX;
      case EMPTY:
        return TYPE_EMPTY;
      case SWITCH:
        return TYPE_SWITCHBUTTON;
      case TEXT:
        return TYPE_TEXT;
      case IMAGE:
        return TYPE_IMAGE;
      default:
        break;
    }
    return TYPE_EMPTY;
  }

  public static boolean isItemViewType(int position, PairItem item, int type) {
    return type == getItemViewType(position, item);
  }
}
