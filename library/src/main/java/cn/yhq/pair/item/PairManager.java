package cn.yhq.pair.item;

import android.content.Context;
import android.view.View;

import java.util.List;

import cn.yhq.adapter.recycler.OnRecyclerViewItemClickListener;
import cn.yhq.pair.adapter.recyclerview.PairAdapter;
import cn.yhq.pair.ui.recyclerview.PairView;


/**
 * Created by Administrator on 2016/11/17.
 */

public class PairManager {
    private Context context;
    private PairFactory factory;

    public PairManager(Context context, PairFactory factory) {
        this.context = context;
        this.factory = factory;
    }

    public void attach(PairView pairView) {
        PairAdapter adapter = this.createPairAdapter(factory);
        pairView.setAdapter(adapter);
    }

    public void refresh() {
        factory.refresh();
    }

    public PairAdapter createPairAdapter(PairFactory factory) {
        final List<IPair> pairs = factory.create();
        final PairAdapter adapter = new PairAdapter(context, pairs);
        adapter.setOnRecyclerViewItemClickListener(new OnRecyclerViewItemClickListener() {
            @Override
            public void onRecyclerViewItemClick(View itemView, int position) {
                IPair pair = pairs.get(position);
                if (pair.getType() != BasePair.Type.CATALOG) {
                    PairItem<?> item = (PairItem<?>) pair;
                    item.onClick();
                }
            }
        });
        factory.setOnInvalidateListener(new OnInvalidateListener<IPair>() {
            @Override
            public void onInvalidate(IPair pair) {
                adapter.notifyItemChanged(pairs.indexOf(pair));
            }
        });
        return adapter;
    }
}
