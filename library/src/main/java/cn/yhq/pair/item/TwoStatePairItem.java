package cn.yhq.pair.item;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;

import cn.yhq.pair.R;
import cn.yhq.pair.action.PairAction;
import cn.yhq.pair.action.PairPreferenceAction;

/**
 * Created by Administrator on 2016/11/15.
 */

public class TwoStatePairItem<T extends TwoStatePairItem<T>> extends PreferencePairItem<T> {
    private boolean checked;

    public TwoStatePairItem(Context context, Type type, AttributeSet attrs) {
        super(context, type, attrs);

        final TypedArray a = context.obtainStyledAttributes(
                attrs, R.styleable.TwoStatePairItem);

        this.checked = a.getBoolean(R.styleable.TwoStatePairItem_checked, false);

        a.recycle();
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
    public boolean performClick() {
        boolean handle = super.performClick();
        if (!handle) {
            this.toggle();
            this.invalidate();
        }
        return handle;
    }

    @Override
    public T setAction(PairAction action) {
        if (action instanceof PairPreferenceAction) {
            this.addInterceptor(new Interceptor<T>() {
                @Override
                public T intercept(Chain<T> chain) throws Exception {
                    boolean checked = (boolean) getPreference();
                    chain.getPair().setChecked(checked);
                    return chain.handle(chain.getPair());
                }
            });
        }
        return super.setAction(action);
    }
}
