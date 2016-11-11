package cn.yhq.pair.adapter;

import android.content.Context;
import android.os.Bundle;

import java.util.List;

import cn.yhq.adapter.expand.BaseExpandableAdapter;
import cn.yhq.adapter.expand.IChildItemViewProvider;
import cn.yhq.pair.core.BaseValue;
import cn.yhq.pair.core.BaseValueItem;
import cn.yhq.pair.core.PairGroup;
import cn.yhq.pair.core.PairGroupList;
import cn.yhq.pair.core.PairItem;

public class ExpandablePairGroupAdapter
    extends BaseExpandableAdapter<PairGroupList, PairGroup, PairItem> {

  public enum ItemType {
    TYPE_CATALOG, TYPE_EMPTY, TYPE_TEXT, TYPE_SWITCH, TYPE_CHECKBOX, TYPE_IMAGE
  }

  private IExpandableCheckedChangeListener mExpandableCheckedChangeListener;

  public ExpandablePairGroupAdapter(Context context, PairGroupList listData) {
    super(context, listData);
    init();
  }

  public ExpandablePairGroupAdapter(Context context) {
    super(context);
    init();
  }

  @Override
  protected void initItemViewProvider() {
    this.setItemViewTypeKeyPolicy(new IGroupItemViewTypeKeyPolicy<ItemType, PairGroup>() {

      @Override
      public ItemType getItemViewTypeKey(int position, PairGroup entity) {
        return ItemType.TYPE_CATALOG;
      }

    });
    this.setItemViewTypeKeyPolicy(new IChildItemViewTypeKeyPolicy<ItemType, PairGroup, PairItem>() {

      @Override
      public ItemType getItemViewTypeKey(int groupPosition, PairGroup groupEntity,
          int childPosition, PairItem childEntity) {
        switch (childEntity.getValueItem().getValueType()) {
          case CHECKBOX:
            return ItemType.TYPE_CHECKBOX;
          case EMPTY:
            return ItemType.TYPE_EMPTY;
          case IMAGE:
            return ItemType.TYPE_IMAGE;
          case SWITCH:
            return ItemType.TYPE_SWITCH;
          case TEXT:
          case FIELD:
            return ItemType.TYPE_TEXT;
          default:
            break;
        }
        return ItemType.TYPE_EMPTY;
      }

    });

    this.register(ItemType.TYPE_EMPTY, new EmptyItemViewProvider());
    this.register(ItemType.TYPE_IMAGE, new ImageItemViewProvider());
    this.register(ItemType.TYPE_SWITCH, new SwitchItemViewProvider());
    this.register(ItemType.TYPE_TEXT, new TextItemViewProvider());
    this.register(ItemType.TYPE_CATALOG, new CatalogItemViewProvider());
  }

  public void addPairGroupProcessor(IPairGroupProcessor pairItemProcessor) {
    mListData.addPairGroupProcessor(pairItemProcessor);
  }

  public void bindEntity(int groupPosition, int childPosition, Object entity) {
    mListData.bindEntity(groupPosition, childPosition, entity);
  }

  public void bindEntity(Object entity) {
    mListData.bindEntity(entity);
  }

  private void init() {
    SwitchItemViewProvider switchItemViewProvider =
        this.getChildItemViewProvider(ItemType.TYPE_SWITCH);
    switchItemViewProvider.setOnCheckedChangeListener(new IExpandableCheckedChangeListener() {

      @Override
      public void onCheckedChanged(int groupPosition, int childPosition, boolean isChecked) {
        if (mExpandableCheckedChangeListener != null) {
          mExpandableCheckedChangeListener
              .onCheckedChanged(groupPosition, childPosition, isChecked);
        }
        getListData().onSavePreference(groupPosition, childPosition);
        notifyDataSetChanged();
      }

    });
  }

  public void setOnCheckedChangeListener(IExpandableCheckedChangeListener onCheckedChangeListener) {
    this.mExpandableCheckedChangeListener = onCheckedChangeListener;
  }

  public void setIconVisiable(boolean isVisiable) {
    List<IChildItemViewProvider<PairGroup, PairItem>> list = this.getAllChildItemViewProvider();
    for (int i = 0; i < list.size(); i++) {
      IChildItemViewProvider<PairGroup, PairItem> baseProvider = list.get(i);
      if (baseProvider instanceof BaseChildItemViewProvider) {
        BaseChildItemViewProvider baseFromProvider = (BaseChildItemViewProvider) baseProvider;
        baseFromProvider.setIconVisiable(isVisiable);
      }
    }
  }

  public <T extends BaseValueItem<? extends BaseValue<?>>> T getPairValueItem(int groupPosition,
                                                                              int childPosition) {
    return this.getChild(groupPosition, childPosition).getValueItem();
  }

  @Override
  public int getGroupCount() {
    return this.mListData.size();
  }

  @Override
  public int getRealChildrenCount(int groupPosition) {
    return getGroup(groupPosition).getPairItems().size();
  }

  @Override
  protected PairGroupList newInstance() {
    return PairGroupList.newPairGroupList(this.mContext);
  }

  public PairGroupList getPairGroupList() {
    return this.getListData();
  }

  @Override
  public PairGroup getGroup(int groupPosition) {
    return this.mListData.get(groupPosition);
  }

  @Override
  public PairItem getChild(int groupPosition, int childPosition) {
    return getGroup(groupPosition).getPairItem(childPosition);
  }

  public void invalidate() {
    this.mListData.invalidate();
  }

  public void onClick(int groupPosition, int childPosition) {
    this.mListData.onClick(groupPosition, childPosition);
  }

  public void onValueChanged(int groupPosition, int childPosition) {
    this.mListData.onValueChanged(groupPosition, childPosition);
  }

  @Override
  public void notifyDataSetChanged() {
    invalidate();
    super.notifyDataSetChanged();
  }

  public Bundle onSaveInstanceState() {
    Bundle bundle = new Bundle();
    try {
      boolean isEmpty = this.mListData.isEmpty();
      if (!isEmpty) {
        bundle.putBundle("pairGroupListData", mListData.toBundle());
      }
      return bundle;
    } catch (Exception e) {
      return bundle;
    }
  }

  public void onRestoreInstanceState(Bundle state) {
    try {
      Bundle bundle = state.getBundle("pairGroupListData");
      if (bundle != null) {
        mListData.fromBundle(bundle);
        this.notifyDataSetChanged();
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }


}
