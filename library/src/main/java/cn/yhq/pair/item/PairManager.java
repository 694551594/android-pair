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
    private PairAdapter adapter;

    PairManager(Context context, PairFactory factory) {
        this.context = context;
        this.factory = factory;
    }

    public static PairManager create(Context context, PairFactory factory) {
        return new PairManager(context, factory);
    }

    public <T extends IPair>void attach(PairView pairView) {
        this.factory.setOnInvalidateListener(new OnInvalidateListener<T>() {
            @Override
            public void onInvalidate(T pair) {
                adapter.notifyItemChanged(adapter.getListData().indexOf(pair));
            }
        });
        List<IPair> pairs = factory.create();
        this.adapter = new PairAdapter(context, pairs);
        this.adapter.setOnRecyclerViewItemClickListener(new OnRecyclerViewItemClickListener() {
            @Override
            public void onRecyclerViewItemClick(View itemView, int position) {
                IPair pair = adapter.getItem(position);
                if (pair.getType() != BasePair.Type.CATALOG) {
                    PairItem<?> item = (PairItem<?>) pair;
                    item.onClick();
                }
            }
        });
        pairView.setAdapter(adapter);
    }

    public void refresh() {
        factory.refresh();
    }

}
