package cn.yhq.pair.item;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/11/17.
 */

public class PairFactory {
    private List<IPair> pairs = new ArrayList<>();
    private Context context;
    private OnPairCreateListener listener;

    public PairFactory(Context context) {
        this.context = context;
    }

    protected PairGroup create() {
        PairGroup pairGroup = onCreatePairGroup(context);
        pairGroup.addAllPair(pairs);
        postPairCreate(pairGroup);
        pairGroup.refresh();
        return pairGroup;
    }

    private void postPairCreate(PairGroup pairGroup) {
        for (IPair pair : pairGroup.getPairs()) {
            if (this.listener != null) {
                this.listener.onCreate(pair.getId(), pair);
            }
        }
    }

    protected void setOnPairCreateListener(OnPairCreateListener listener) {
        this.listener = listener;
    }

    protected PairGroup onCreatePairGroup(Context context) {
        return new PairGroup(context, null);
    }

    public PairCatalog newCatalog() {
        PairCatalog catalog = new PairCatalog(context);
        pairs.add(catalog);
        return catalog;
    }

    public CheckPairItem newCheckboxItem() {
        CheckPairItem item = new CheckPairItem(context);
        pairs.add(item);
        return item;
    }

    public SwitchPairItem newSwitchItem() {
        SwitchPairItem item = new SwitchPairItem(context);
        pairs.add(item);
        return item;
    }

    public TextPairItem newTextItem() {
        TextPairItem item = new TextPairItem(context);
        pairs.add(item);
        return item;
    }

    public FieldPairItem newFieldItem() {
        FieldPairItem item = new FieldPairItem(context);
        pairs.add(item);
        return item;
    }

    public ImagePairItem newImageItem() {
        ImagePairItem item = new ImagePairItem(context);
        pairs.add(item);
        return item;
    }

}
