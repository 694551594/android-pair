package cn.yhq.pair.item;

import cn.yhq.pair.PairItemType;

/**
 * Created by Administrator on 2016/11/15.
 */

public class TwoValuePairItem extends PairItem {
    private boolean checked;

    public TwoValuePairItem(PairItemType type) {
        super(type);
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }
}
