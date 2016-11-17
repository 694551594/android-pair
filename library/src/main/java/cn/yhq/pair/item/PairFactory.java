package cn.yhq.pair.item;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/11/17.
 */

public abstract class PairFactory {
    private List<IPair> pairs = new ArrayList<>();

    private void intercept() {
        for (IPair pair : pairs) {
            if (pair instanceof BasePair<?>) {
                ((BasePair<?>) pair).intercept();
            }
        }
    }

    private void invalidate() {
        for (IPair pair : pairs) {
            if (pair instanceof BasePair<?>) {
                ((BasePair<?>) pair).invalidate();
            }
        }
    }

    List<IPair> create() {
        onCreate(this);
        refresh();
        return this.pairs;
    }

    void setOnInvalidateListener(OnInvalidateListener listener) {
        for (IPair pair : pairs) {
            if (pair instanceof BasePair<?>) {
                ((BasePair<?>) pair).setOnInvalidateListener(listener);
            }
        }
    }

    protected abstract void onCreate(PairFactory factory);

    public void refresh() {
        intercept();
        invalidate();
    }

    public PairCatalog newCatalog() {
        PairCatalog catalog = new PairCatalog();
        pairs.add(catalog);
        return catalog;
    }

    public CheckPairItem newCheckboxItem() {
        CheckPairItem item = new CheckPairItem();
        pairs.add(item);
        return item;
    }

    public SwitchPairItem newSwitchItem() {
        SwitchPairItem item = new SwitchPairItem();
        pairs.add(item);
        return item;
    }

    public TextPairItem newTextItem() {
        TextPairItem item = new TextPairItem();
        pairs.add(item);
        return item;
    }

    public FieldPairItem newFieldItem() {
        FieldPairItem item = new FieldPairItem();
        pairs.add(item);
        return item;
    }

    public ImagePairItem newImageItem() {
        ImagePairItem item = new ImagePairItem();
        pairs.add(item);
        return item;
    }

}
