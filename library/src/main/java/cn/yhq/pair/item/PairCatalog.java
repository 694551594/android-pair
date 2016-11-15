package cn.yhq.pair.item;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Administrator on 2016/11/15.
 */

public class PairCatalog {
    private String title;
    private List<PairItem> items = new ArrayList<>();

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public EmptyPairItem newEmptyItem() {
        EmptyPairItem item = new EmptyPairItem();
        items.add(item);
        return item;
    }

    public CheckPairItem newCheckboxItem() {
        CheckPairItem item = new CheckPairItem();
        items.add(item);
        return item;
    }

    public SwitchPairItem newSwitchItem() {
        SwitchPairItem item = new SwitchPairItem();
        items.add(item);
        return item;
    }

    public TextPairItem newTextItem() {
        TextPairItem item = new TextPairItem();
        items.add(item);
        return item;
    }

    public FieldPairItem newFieldItem() {
        FieldPairItem item = new FieldPairItem();
        items.add(item);
        return item;
    }

    public List<PairItem> getItems() {
        return items;
    }

    public PairCatalog setItems(List<PairItem> items) {
        this.items = items;
        return this;
    }

    public PairCatalog addItems(PairItem... items) {
        this.items.addAll(Arrays.asList(items));
        return this;
    }
}
