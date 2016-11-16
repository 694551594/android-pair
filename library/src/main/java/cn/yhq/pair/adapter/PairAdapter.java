package cn.yhq.pair.adapter;

import android.content.Context;

import java.util.List;

import cn.yhq.adapter.expand.BaseExpandableListAdapter;
import cn.yhq.adapter.expand.IChildItemViewProviderKeyPolicy;
import cn.yhq.adapter.expand.IGroupItemViewProviderKeyPolicy;
import cn.yhq.pair.adapter.provider.CatalogItemViewProvider;
import cn.yhq.pair.adapter.provider.ImageItemViewProvider;
import cn.yhq.pair.adapter.provider.SwitchItemViewProvider;
import cn.yhq.pair.adapter.provider.TextItemViewProvider;
import cn.yhq.pair.item.PairCatalog;
import cn.yhq.pair.item.PairItem;

/**
 * Created by Administrator on 2016/11/15.
 */

public class PairAdapter extends BaseExpandableListAdapter<PairCatalog, PairItem> {

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

    private void register() {
        this.setGroupItemViewProviderKeyPolicy(new IGroupItemViewProviderKeyPolicy<PairCatalog>() {
            @Override
            public int getItemViewProviderKey(int position, PairCatalog entity) {
                return ItemType.TYPE_CATALOG;
            }
        });
        this.setChildItemViewProviderKeyPolicy(new IChildItemViewProviderKeyPolicy<PairCatalog, PairItem>() {
            @Override
            public int getItemViewProviderKey(int groupPosition, PairCatalog groupEntity, int childPosition, PairItem childEntity) {
                switch (childEntity.getType()) {
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
        this.register(ItemType.TYPE_TEXT, new TextItemViewProvider());
        this.register(ItemType.TYPE_CATALOG, new CatalogItemViewProvider());
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
