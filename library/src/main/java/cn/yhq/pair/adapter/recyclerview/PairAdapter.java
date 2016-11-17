package cn.yhq.pair.adapter.recyclerview;

import android.content.Context;

import java.util.List;

import cn.yhq.adapter.recycler.IItemViewProviderKeyPolicy;
import cn.yhq.adapter.recycler.RecyclerListAdapter;
import cn.yhq.pair.item.PairCatalog;
import cn.yhq.pair.item.PairItem;


/**
 * Created by Yanghuiqiang on 2016/11/17.
 */

public class PairAdapter extends RecyclerListAdapter<PairCatalog> implements IExpandableAdapter<PairCatalog, PairItem> {

    @Override
    public PairCatalog getGroup(int groupPosition) {
        return this.getItem(groupPosition);
    }

    @Override
    public PairItem getChild(int groupPosition, int childPosition) {
        return this.getItem(groupPosition).getItems().get(childPosition);
    }

    @Override
    public int getGroupCount() {
        return this.getItemCount();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return this.getItem(groupPosition).getItems().size();
    }

    public static class ItemType {
        final static int TYPE_CATALOG = 0;
        final static int TYPE_TEXT = 1;
        final static int TYPE_SWITCH = 2;
        final static int TYPE_CHECKBOX = 3;
        final static int TYPE_IMAGE = 4;
    }

    public PairAdapter(Context context) {
        super(context);
        register();
    }

    public PairAdapter(Context context, List<PairCatalog> listData) {
        super(context, listData);
        register();
    }

    public boolean isGroupPosition(int position) {
        if (position == 0) {
            return true;
        }
        int j = 0;
        for (int i = 0; i < this.getGroupCount(); i++) {
            j = this.getChildrenCount(i) + j;
            if (position == j + 1) {
                return true;
            }
        }
        return false;
    }

    public int getGroupPosition(int position) {
        if (position == 0) {
            return position;
        }
        int j = 0;
        for (int i = 0; i < this.getGroupCount(); i++) {
            j = this.getChildrenCount(i) + j;
            if (position < j) {
                return i;
            }
        }
        return 0;
    }

    public int getChildPosition(int position) {
        int p = 0;
        for (int i = 0; i < this.getGroupCount(); i++) {
            for (int j = 0; j < this.getChildrenCount(i); j++) {
                p += j;
                if (position < p) {
                    return j;
                }
            }
        }
        return 0;
    }

    private void register() {
        this.setItemViewProviderKeyPolicy(new IItemViewProviderKeyPolicy<PairCatalog>() {
            @Override
            public int getItemViewProviderKey(int position, PairCatalog entity) {
                if (isGroupPosition(position)) {
                    return ItemType.TYPE_CATALOG;
                }
                int groupPosition = getGroupPosition(position);
                int childPosition = getChildPosition(position);
                switch (getChild(groupPosition, childPosition).getType()) {
                    case CHECKBOX:
                        return ItemType.TYPE_CHECKBOX;
                    case IMAGE:
                        return ItemType.TYPE_IMAGE;
                    case SWITCH:
                        return ItemType.TYPE_SWITCH;
                    case TEXT:
                    case FIELD:
                        return ItemType.TYPE_TEXT;
                    default:
                        return ItemType.TYPE_TEXT;
                }
            }
        });
        this.register(ItemType.TYPE_IMAGE, new ImageItemViewProvider());
        this.register(ItemType.TYPE_SWITCH, new SwitchItemViewProvider());
        this.register(ItemType.TYPE_CHECKBOX, new CheckboxItemViewProvider());
        this.register(ItemType.TYPE_TEXT, new TextItemViewProvider());
        this.register(ItemType.TYPE_CATALOG, new CatalogItemViewProvider());
    }

}
