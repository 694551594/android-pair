package cn.yhq.pair.adapter;

import java.util.List;

import android.content.Context;
import android.database.DataSetObserver;
import cn.developer.sdk.page2.adapter.OnListDataChangeListener;
import cn.developer.sdk.page2.adapter.IPageDataParser;
import cn.developer.sdk.page2.adapter.IPageListAdapter;
import cn.developer.sdk.pair.core.PairGroup;
import cn.developer.sdk.pair.core.PairGroupList;

public class ExpandablePairGroupPageAdapter extends ExpandablePairGroupAdapter
    implements
      IPageListAdapter<PairGroup> {
  private OnListDataChangeListener onListDataListener;

  public ExpandablePairGroupPageAdapter(Context context) {
    super(context);
    init();
  }

  public ExpandablePairGroupPageAdapter(Context context, PairGroupList listData) {
    super(context, listData);
    init();
  }

  @Override
  public boolean remove(int position) {
    PairGroup pairGroup = this.getPageListData().get(position);
    return pairGroup == this.getPageListData().remove(position);
  }

  @Override
  public int getPageDataCount() {
    return this.getGroupCount();
  }

  @Override
  public void setListDataChangeListener(OnListDataChangeListener onListDataListener) {
    this.onListDataListener = onListDataListener;
    onListDataListener.onDataChange(this.getPageDataCount() != 0, this.getPageDataCount());
  }

  private void init() {
    this.registerDataSetObserver(new PageDataSetObserver());
  }

  class PageDataSetObserver extends DataSetObserver {

    @Override
    public void onChanged() {
      super.onChanged();
      if (onListDataListener != null) {
        onListDataListener.onDataChange(getPageDataCount() != 0, getPageDataCount());
      }
    }

  }

  @Override
  public void clear() {
    this.getPageListData().clear();
  }

  @Override
  public PairGroupList getPageListData() {
    return this.getPairGroupList();
  }

  @Override
  public void setPageDataParser(IPageDataParser<List<PairGroup>> pageDataParser) {}

  @Override
  public void addAll(List<PairGroup> data) {
    this.getPageListData().addAll(data);
  }

}
