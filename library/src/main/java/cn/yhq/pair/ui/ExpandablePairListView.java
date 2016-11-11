package cn.yhq.pair.ui;

import android.content.Context;
import android.content.res.TypedArray;
import android.database.DataSetObserver;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;

import cn.yhq.pair.R;
import cn.yhq.pair.adapter.ExpandablePairGroupAdapter;
import cn.yhq.pair.adapter.PairGroupXMLParser;
import cn.yhq.pair.core.PairGroupList;

/**
 * 为了保持界面统一而写的布局
 * 
 * 主要实现设置界面以及类似key-value的list界面
 * 
 * 界面可以使用xml定义
 * 
 * @author Yanghuiqiang 2015-10-22
 * 
 */
public class ExpandablePairListView extends ExpandableListView {
  // xml布局对应的id
  private int mXmlResId;
  private ExpandablePairGroupAdapter mExpandablePairGroupAdapter;
  private PairGroupList mPairGroupList;
  // 是否自动适配数据
  private boolean mAutoAdapterData;
  private OnChildClickListener mOnChildClickListener;

  public ExpandablePairListView(Context context) {
    super(context);
    initView();
  }

  public ExpandablePairListView(Context context, AttributeSet attrs) {
    super(context, attrs);
    initAttrs(attrs);
    initView();
  }

  public ExpandablePairListView(Context context, AttributeSet attrs, int defStyle) {
    super(context, attrs, defStyle);
    initAttrs(attrs);
    initView();
  }

  private void initAttrs(AttributeSet attrs) {
    TypedArray a =
        getContext().obtainStyledAttributes(attrs, R.styleable.ExpandablePairListView, 0, 0);
    mXmlResId = a.getResourceId(R.styleable.ExpandablePairListView_xmlLayout, 0);
    if (mXmlResId == 0) {
      a.recycle();
      return;
    }
    mAutoAdapterData = a.getBoolean(R.styleable.ExpandablePairListView_autoAdapterData, true);
    mPairGroupList = PairGroupXMLParser.parser(this.getContext(), mXmlResId);
    if (mPairGroupList != null) {
      mExpandablePairGroupAdapter = mPairGroupList.buildPairAdapter();
    }
    a.recycle();
  }

  public void initView() {
    this.setDivider(null);
    this.setGroupIndicator(null);
    // 该listview是一直展开的并且group不可点
    this.setOnGroupClickListener(new OnGroupClickListener() {

      @Override
      public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
        return true;
      }
    });

    super.setOnChildClickListener(new OnChildClickListener() {

      @Override
      public boolean onChildClick(ExpandableListView parent, View v, int groupPosition,
          int childPosition, long id) {
        if (mOnChildClickListener != null) {
          mOnChildClickListener.onChildClick(parent, v, groupPosition, childPosition, id);
        }
        mExpandablePairGroupAdapter.getListData().onClick(groupPosition, childPosition);
        return true;
      }

    });

    if (mExpandablePairGroupAdapter != null && mAutoAdapterData) {
      this.setAdapter(mExpandablePairGroupAdapter);
    }

  }

  @Override
  public void setOnChildClickListener(OnChildClickListener onChildClickListener) {
    this.mOnChildClickListener = onChildClickListener;
  }

  @Override
  public Parcelable onSaveInstanceState() {
    Parcelable superState = super.onSaveInstanceState();
    SavedState ss = new SavedState(superState);
    ss.bundle = mExpandablePairGroupAdapter.onSaveInstanceState();
    return ss;
  }

  @Override
  public void onRestoreInstanceState(Parcelable state) {
    SavedState ss = (SavedState) state;
    super.onRestoreInstanceState(ss.getSuperState());
    this.mExpandablePairGroupAdapter.onRestoreInstanceState(ss.bundle);
  }

  static class SavedState extends BaseSavedState {

    SavedState(Parcelable parcelable) {
      super(parcelable);
    }

    private SavedState(Parcel in) {
      super(in);
      bundle = in.readBundle();
    }

    Bundle bundle = new Bundle();

    @Override
    public void writeToParcel(Parcel dest, int flags) {
      super.writeToParcel(dest, flags);
      dest.writeBundle(bundle);
    }

    public static final Parcelable.Creator<SavedState> CREATOR =
        new Parcelable.Creator<SavedState>() {
          public SavedState createFromParcel(Parcel in) {
            return new SavedState(in);
          }

          public SavedState[] newArray(int size) {
            return new SavedState[size];
          }
        };
  }

  @Override
  public ExpandablePairGroupAdapter getExpandableListAdapter() {
    return mExpandablePairGroupAdapter;
  }

  @Override
  public void setAdapter(final ExpandableListAdapter adapter) {
    if (adapter == null) {
      return;
    }
    if (super.getExpandableListAdapter() != null) {
      mExpandablePairGroupAdapter = null;
    }
    super.setAdapter(adapter);
    if (mExpandablePairGroupAdapter == null) {
      mExpandablePairGroupAdapter = (ExpandablePairGroupAdapter) adapter;
    }
    mExpandablePairGroupAdapter.registerDataSetObserver(new DataSetObserver() {

      @Override
      public void onChanged() {
        super.onChanged();
        for (int i = 0; i < adapter.getGroupCount(); i++) {
          ExpandablePairListView.this.expandGroup(i);
        }
      }

    });
    mExpandablePairGroupAdapter.invalidate();
    for (int i = 0; i < adapter.getGroupCount(); i++) {
      ExpandablePairListView.this.expandGroup(i);
    }
  }


}
