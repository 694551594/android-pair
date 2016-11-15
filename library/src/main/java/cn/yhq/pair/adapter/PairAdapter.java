package cn.yhq.pair.adapter;

import android.content.Context;

import java.util.List;

import cn.yhq.adapter.expand.BaseExpandableListAdapter;
import cn.yhq.pair.item.PairCatalog;
import cn.yhq.pair.item.PairItem;

/**
 * Created by Administrator on 2016/11/15.
 */

public class PairAdapter extends BaseExpandableListAdapter<PairCatalog, PairItem> {

    public enum ItemType {
        TYPE_CATALOG,
        TYPE_EMPTY,
        TYPE_TEXT,
        TYPE_SWITCH,
        TYPE_CHECKBOX,
        TYPE_IMAGE
    }

    public PairAdapter(Context context) {
        super(context);
    }

    public PairAdapter(Context context, List<PairCatalog> listData) {
        super(context, listData);
    }

    @Override
    public PairItem getChild(PairCatalog group, int childPosition) {
        return group.getItems().get(childPosition);
    }

    @Override
    public int getChildrenCount(PairCatalog group) {
        return group.getItems().size();
    }
}
