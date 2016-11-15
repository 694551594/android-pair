package cn.yhq.pair;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import cn.yhq.pair.adapter.PairAdapter;
import cn.yhq.pair.item.PairCatalog;

/**
 * Created by Administrator on 2016/11/15.
 */

public class Pair {

    public static class Builder {
        private List<PairCatalog> catalogs = new ArrayList<>();
        private Context context;

        public Builder(Context context) {
            this.context = context;
        }

        public PairCatalog newCatalog() {
            PairCatalog catalog = new PairCatalog();
            catalogs.add(catalog);
            return catalog;
        }

        public PairAdapter build() {
            PairAdapter adapter = new PairAdapter(context, catalogs);
            return adapter;
        }

    }
}
