package cn.yhq.pair;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ExpandableListView;

import java.util.ArrayList;
import java.util.List;

import cn.yhq.pair.adapter.recyclerview.PairAdapter;
import cn.yhq.pair.item.OnInvalidateListener;
import cn.yhq.pair.item.PairCatalog;
import cn.yhq.pair.item.PairItem;
import cn.yhq.pair.ui.expandable.PairView;

public class Pair {
    private cn.yhq.pair.adapter.expandable.PairAdapter pairAdapter;
    private Context context;
    private List<PairCatalog> catalogs = new ArrayList<>();

    public Pair(Context context, List<PairCatalog> catalogs) {
        this.context = context;
        this.catalogs = catalogs;
    }

    public void setup(RecyclerView recyclerView) {
        intercept();
        for (PairCatalog catalog : catalogs) {
            for (PairItem<?> item : catalog.getItems()) {
                item.setOnInvalidateListener(new OnInvalidateListener() {
                    @Override
                    public void onInvalidate() {
                        pairAdapter.notifyDataSetChanged();
                    }
                });
            }
        }
        PairAdapter pairAdapter = new PairAdapter(context, catalogs);
        recyclerView.setAdapter(pairAdapter);
    }

    public void setup(PairView pairView) {
        intercept();
        for (PairCatalog catalog : catalogs) {
            for (PairItem<?> item : catalog.getItems()) {
                item.setOnInvalidateListener(new OnInvalidateListener() {
                    @Override
                    public void onInvalidate() {
                        pairAdapter.notifyDataSetChanged();
                    }
                });
            }
        }
        pairAdapter = new cn.yhq.pair.adapter.expandable.PairAdapter(context, catalogs);
        pairView.setAdapter(pairAdapter);
        pairView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                PairItem<?> pairItem = pairAdapter.getChild(groupPosition, childPosition);
                pairItem.onClick();
                return false;
            }
        });
    }

    private void intercept() {
        for (PairCatalog catalog : catalogs) {
            for (PairItem<?> item : catalog.getItems()) {
                item.intercept();
            }
        }
    }

    public void invalidate() {
        intercept();
        pairAdapter.notifyDataSetChanged();
    }

    public static class Builder {
        private List<PairCatalog> catalogs = new ArrayList<>();
        private Context context;

        public Builder(Context context) {
            this.context = context;
        }

        public Builder addCatalog(PairCatalog catalog) {
            this.catalogs.add(catalog);
            return this;
        }

        public PairCatalog newCatalog() {
            PairCatalog catalog = new PairCatalog();
            catalogs.add(catalog);
            return catalog;
        }

        public Pair build() {
            Pair pair = new Pair(context, catalogs);
            return pair;
        }

    }
}
