package cn.yhq.pair.item;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;

import java.util.ArrayList;
import java.util.List;

import cn.yhq.adapter.recycler.ViewHolder;
import cn.yhq.pair.R;

/**
 * Created by Administrator on 2016/11/19.
 */

// 拦截器的顺序、group与catalog的问题，enable的问题

public abstract class Pair<T extends Pair<T>> implements IPair {
    private int mId;
    private boolean mEnable = true;
    private Context mContext;
    private OnPairChangeListener mOnPairChangeListener;
    private List<Interceptor<T>> interceptors = new ArrayList<>();
    private boolean isIntercept = true;

    public Pair(Context context, AttributeSet attrs) {
        this.mContext = context;

        final TypedArray a = context.obtainStyledAttributes(
                attrs, R.styleable.Pair);

        this.mId = a.getResourceId(R.styleable.Pair_id, 0);
        this.mEnable = a.getBoolean(R.styleable.Pair_enable, true);

        a.recycle();
    }

    public T setEnable(boolean enable) {
        this.mEnable = enable;
        this.notifyChange();
        return (T) this;
    }

    public boolean isEnable() {
        return mEnable;
    }

    @Override
    public int getId() {
        return mId;
    }

    public Context getContext() {
        return mContext;
    }

    public void setId(int id) {
        this.mId = id;
    }

    private void intercept() {
        try {
            this.getDataWithInterceptorChain((T) this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private T getDataWithInterceptorChain(T item) throws Exception {
        Interceptor.Chain<T> chain = new DefaultInterceptor(0, item);
        return chain.handle(item);
    }

    class DefaultInterceptor implements Interceptor.Chain<T> {
        private final int index;
        private T pair;

        DefaultInterceptor(int index, T pair) {
            this.index = index;
            this.pair = pair;
        }

        @Override
        public T getPair() {
            return pair;
        }

        @Override
        public T handle(T pair) throws Exception {
            try {
                if (index < interceptors.size()) {
                    Interceptor.Chain<T> chain = new DefaultInterceptor(index + 1, pair);
                    Interceptor<T> intercept = interceptors.get(index);
                    pair.setIntercept(false);
                    T interceptData = intercept.intercept(chain);
                    if (interceptData == null) {
                        throw new NullPointerException("intercept " + intercept + " returned null");
                    }
                    return interceptData;
                }
                return pair;
            } finally {
                pair.setIntercept(true);
            }
        }
    }

    public T addInterceptor(Interceptor<T> interceptor) {
        this.interceptors.add(interceptor);
        return (T) this;
    }

    protected void notifyChange() {
        if (isIntercept) {
            this.intercept();
        }
        if (mOnPairChangeListener != null) {
            mOnPairChangeListener.onPairChange(this);
        }
    }

    public void setIntercept(boolean isIntercept) {
        this.isIntercept = isIntercept;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder) {

    }

    @Override
    public void setOnPairChangeListener(OnPairChangeListener listener) {
        this.mOnPairChangeListener = listener;
    }

}
