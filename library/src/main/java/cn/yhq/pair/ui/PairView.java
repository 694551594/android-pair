package cn.yhq.pair.ui;

import android.content.Context;
import android.database.DataSetObserver;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ListAdapter;

import cn.yhq.pair.adapter.PairAdapter;

/**
 * Created by Yanghuiqiang on 2016/11/16.
 */

public class PairView extends ExpandableListView {
    private PairAdapter mPairAdapter;

    public PairView(Context context) {
        super(context);
        initView();
    }

    public PairView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public PairView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initView();
    }

    public void initView() {
        // this.setDivider(null);
        this.setGroupIndicator(null);
        this.setBackgroundResource(android.R.color.white);
        // 该listview是一直展开的并且group不可点
        this.setOnGroupClickListener(new OnGroupClickListener() {

            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                return true;
            }
        });
    }

    @Override
    public PairAdapter getExpandableListAdapter() {
        return mPairAdapter;
    }

    @Override
    public void setAdapter(ListAdapter adapter) {
        super.setAdapter(adapter);
    }

    @Override
    public void setAdapter(final ExpandableListAdapter adapter) {
        if (adapter == null) {
            return;
        }
        if (super.getExpandableListAdapter() != null) {
            mPairAdapter = null;
        }
        DataSetObserver observer = new DataSetObserver() {

            @Override
            public void onChanged() {
                super.onChanged();
                for (int i = 0; i < adapter.getGroupCount(); i++) {
                    PairView.this.expandGroup(i);
                }
            }

        };
        adapter.registerDataSetObserver(observer);
        super.setAdapter(adapter);
        if (mPairAdapter == null) {
            mPairAdapter = (PairAdapter) adapter;
        }
        observer.onChanged();
    }
}
