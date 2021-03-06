package cn.yhq.pair.item;

import android.content.Context;

import cn.yhq.pair.ui.PairAdapter;
import cn.yhq.pair.ui.PairView;
import cn.yhq.pair.xml.XmlPairFactory;


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

    public static PairManager create(Context context, int resId) {
        return new PairManager(context, new XmlPairFactory(context, resId));
    }

    public PairGroup getPairGroup() {
        return pairGroup;
    }

    public <T extends IPair> T getPairById(int id) {
        return pairGroup.getPairById(id);
    }

    public <T extends IPair> T getPairByIndex(int index) {
        return pairGroup.getPairByIndex(index);
    }

    public void attach(PairView pairView) {
        this.pairGroup = this.factory.create();
        this.adapter = new PairAdapter(context, this.pairGroup);
        pairView.setAdapter(adapter);
    }

    public PairManager setOnPairCreateListener(OnPairCreateListener listener) {
        this.factory.setOnPairCreateListener(listener);
        return this;
    }

}
