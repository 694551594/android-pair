package cn.yhq.pair;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import cn.yhq.pair.adapter.PairAdapter;
import cn.yhq.pair.item.PairCatalog;
import cn.yhq.pair.item.PairItem;
import cn.yhq.pair.ui.PairView;

public class Pair {
    private PairAdapter pairAdapter;
    private Context context;
    private List<PairCatalog> catalogs = new ArrayList<>();

    public Pair(Context context, List<PairCatalog> catalogs) {
        this.context = context;
        this.catalogs = catalogs;
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
        pairAdapter = new PairAdapter(context, catalogs);
        pairView.setAdapter(pairAdapter);
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
