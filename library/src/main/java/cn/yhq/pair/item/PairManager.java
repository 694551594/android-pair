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

    public <T extends IPair> PairManager attach(PairView pairView) {
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
        return this;
    }

    public void refresh() {
        pairGroup.refresh();
    }

    public void refresh(int index) {
        pairGroup.refresh(index);
    }

}