package cn.yhq.pair.ui;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import cn.yhq.adapter.recycler.IItemViewProviderKeyPolicy;
import cn.yhq.adapter.recycler.RecyclerListAdapter;
import cn.yhq.pair.item.IPair;
import cn.yhq.pair.item.OnPairChangeListener;
import cn.yhq.pair.item.PairGroup;

/**
 * Created by Yanghuiqiang on 2016/11/17.
 */
public class PairAdapter extends RecyclerListAdapter<IPair> {
    private List<PairLayout> mLayouts = new ArrayList<>();

    static class PairLayout {
        int itemLayout;
        String name;

        PairLayout(String name, int itemLayout) {
            this.itemLayout = itemLayout;
            this.name = name;
        }

        @Override
        public boolean equals(Object o) {
            if (!(o instanceof PairLayout)) {
                return false;
            }
            final PairLayout other = (PairLayout) o;
            return itemLayout == other.itemLayout
                    && name == other.name;
        }

        @Override
        public int hashCode() {
            int result = 17;
            result = 31 * result + name.hashCode();
            result = 31 * result + itemLayout;
            return result;
        }
    }

    public PairAdapter(Context context, PairGroup listData) {
        super(context, listData.getAllVisiblePairs());

        for (int i = 0; i < this.getItemCount(); i++) {
            IPair pair = this.getItem(i);
            pair.setOnPairChangeListener(new OnPairChangeListener() {
                @Override
                public void onPairChange(IPair pair) {
                    int index = getListData().indexOf(pair);
                    notifyItemChanged(index);
                }

                @Override
                public void onPairHierarchyChange(IPair pair) {
                    notifyDataSetChanged();
                }
            });

            PairLayout layout = new PairLayout(pair.getClass().getName(), pair.getItemViewLayoutId());
            if (!mLayouts.contains(layout)) {
                mLayouts.add(layout);
            }
        }

        for (int i = 0; i < mLayouts.size(); i++) {
            PairLayout layout = mLayouts.get(i);
            this.register(i, new ItemViewProvider(layout.itemLayout));
        }

        this.setItemViewProviderKeyPolicy(new IItemViewProviderKeyPolicy<IPair>() {
            @Override
            public int getItemViewProviderKey(int position, IPair entity) {
                PairLayout layout = new PairLayout(entity.getClass().getName(), entity.getItemViewLayoutId());
                return mLayouts.indexOf(layout);
            }
        });
    }

}
