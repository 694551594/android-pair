package cn.yhq.pair.item;

import cn.yhq.pair.action.PairAction;
import cn.yhq.pair.PairItemType;

/**
 * Created by Administrator on 2016/11/15.
 */

public class PairItem {
    private int icon;
    private String key;
    private PairAction action;
    private PairItemType type;
    private String description;

    public PairItem(PairItemType type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public PairAction getAction() {
        return action;
    }

    public void setAction(PairAction action) {
        this.action = action;
    }

    public PairItemType getType() {
        return type;
    }

    public void setType(PairItemType type) {
        this.type = type;
    }
}
