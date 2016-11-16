package cn.yhq.pair.item;

import android.content.Context;

import cn.yhq.pair.PairIntercept;
import cn.yhq.pair.PairItemType;
import cn.yhq.pair.action.PairAction;

/**
 * Created by Administrator on 2016/11/15.
 */

public class TwoValuePairItem<T> extends PairItem<TwoValuePairItem<T>> {
    private boolean checked;

    public TwoValuePairItem(PairItemType type) {
        super(type);
    }

    public boolean isChecked() {
        return checked;
    }

    public T setChecked(boolean checked) {
        if (this.checked == checked) {
            return (T) this;
        }
        this.checked = checked;
        onCheckedChange();
        return (T) this;
    }

    private void onCheckedChange() {
        this.onSavePreference(checked);
    }

    public T toggle() {
        return this.setChecked(!this.isChecked());
    }

    @Override
    public boolean onClick(Context context) {
        boolean handle = super.onClick(context);
        if (!handle) {
            this.toggle();
        }
        this.invalidate();
        return handle;
    }

    @Override
    public TwoValuePairItem<T> setAction(final PairAction action) {
        this.addIntercept(new PairIntercept<TwoValuePairItem<T>>() {
            @Override
            public TwoValuePairItem<T> intercept(Chain<TwoValuePairItem<T>> chain) throws Exception {
                boolean checked = (boolean) getPreference();
                chain.getItem().setChecked(checked);
                return chain.getItem();
            }
        });
        return super.setAction(action);
    }
}
