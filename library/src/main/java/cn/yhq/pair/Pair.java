package cn.yhq.pair;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import cn.yhq.pair.adapter.PairAdapter;
import cn.yhq.pair.item.PairCatalog;
import cn.yhq.pair.item.PairItem;

public class Pair {

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

        public PairAdapter build() {
            for (PairCatalog catalog : catalogs) {
                for (PairItem<?> item : catalog.getItems()) {
                    item.intercept();
                }
            }

            PairAdapter adapter = new PairAdapter(context, catalogs);
            return adapter;
        }

    }
}
