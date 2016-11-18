package cn.yhq.pair.item;

import android.content.Context;
import android.view.View;

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
    private PairGroup pairGroup;

    PairManager(Context context, PairFactory factory) {
        this.context = context;
        this.factory = factory;
    }

    public static PairManager create(Context context, PairFactory factory) {
        return new PairManager(context, factory);
    }

    public void attach(PairView pairView) {
        this.pairGroup = this.factory.create();
        this.pairGroup.setOnInvalidateListener(new OnInvalidateListener() {
            @Override
            public void onInvalidate(IPair pair) {
                int index = adapter.getListData().indexOf(pair);
                adapter.notifyItemChanged(index);
            }
        });
        this.adapter = new PairAdapter(context, this.pairGroup.getPairs());
        this.adapter.setOnRecyclerViewItemClickListener(new OnRecyclerViewItemClickListener() {
            @Override
            public void onRecyclerViewItemClick(View itemView, int position) {
                IPair pair = adapter.getItem(position);
                if (pair.getType() != BasePair.Type.CATALOG) {
                    PairItem<?> item = (PairItem<?>) pair;
                    item.performClick();
                }
            }
        });
        pairView.setAdapter(adapter);
    }

    public PairManager refresh() {
        pairGroup.refresh();
        return this;
    }

    public PairManager refresh(int id) {
        pairGroup.refresh(id);
        return this;
    }

    public PairManager setOnPairCreateListener(OnPairCreateListener listener) {
        this.factory.setOnPairCreateListener(listener);
        return this;
    }

}
