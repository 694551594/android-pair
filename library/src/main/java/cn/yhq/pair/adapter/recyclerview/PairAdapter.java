package cn.yhq.pair.adapter.recyclerview;

import android.content.Context;

import java.util.List;

import cn.yhq.adapter.recycler.IItemViewProviderKeyPolicy;
import cn.yhq.adapter.recycler.RecyclerListAdapter;
import cn.yhq.pair.item.IPair;


/**
 * Created by Yanghuiqiang on 2016/11/17.
 */

public class PairAdapter extends RecyclerListAdapter<IPair> {

    public static class ItemType {
        final static int TYPE_CATALOG = 0;
        final static int TYPE_TEXT = 1;
        final static int TYPE_SWITCH = 2;
        final static int TYPE_CHECKBOX = 3;
        final static int TYPE_IMAGE = 4;
        final static int TYPE_FIELD = 5;
    }

    public PairAdapter(Context context, List<IPair> listData) {
        super(context, listData);
        register();
    }

    private void register() {
        this.setItemViewProviderKeyPolicy(new IItemViewProviderKeyPolicy<IPair>() {
            @Override
            public int getItemViewProviderKey(int position, IPair entity) {
                switch (entity.getType()) {
                    case CATALOG:
                        return ItemType.TYPE_CATALOG;
                    case CHECKBOX:
                        return ItemType.TYPE_CHECKBOX;
                    case IMAGE:
                        return ItemType.TYPE_IMAGE;
                    case SWITCH:
                        return ItemType.TYPE_SWITCH;
                    case TEXT:
                        return ItemType.TYPE_TEXT;
                    case FIELD:
                        return ItemType.TYPE_FIELD;
                    default:
                        return ItemType.TYPE_TEXT;
                }
            }
        });
        this.register(ItemType.TYPE_IMAGE, new ImageItemViewProvider());
        this.register(ItemType.TYPE_SWITCH, new SwitchItemViewProvider());
        this.register(ItemType.TYPE_CHECKBOX, new CheckboxItemViewProvider());
        this.register(ItemType.TYPE_TEXT, new TextItemViewProvider());
        this.register(ItemType.TYPE_FIELD, new FieldItemViewProvider());
        this.register(ItemType.TYPE_CATALOG, new CatalogItemViewProvider());
    }

}
