package cn.yhq.pair.item;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;

import java.util.ArrayList;
import java.util.List;

import cn.yhq.pair.R;

/**
 * Created by Administrator on 2016/11/17.
 */

public abstract class BasePair<T extends IPair> implements IPair {
    private int id;
    private Type type;
    private List<Interceptor<T>> interceptors = new ArrayList<>();
    private OnInvalidateListener onInvalidateListener;
    private Context context;

    public BasePair(Context context, Type type, AttributeSet attrs) {
        this.context = context;
        this.type = type;

        final TypedArray a = context.obtainStyledAttributes(
                attrs, R.styleable.Pair);

        this.id = a.getResourceId(R.styleable.Pair_id, 0);

        a.recycle();
    }

    public Context getContext() {
        return context;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public void invalidate() {
        if (onInvalidateListener != null) {
            onInvalidateListener.onInvalidate(this);
        }
    }

    protected void setOnInvalidateListener(OnInvalidateListener onInvalidateListener) {
        this.onInvalidateListener = onInvalidateListener;
    }

    @Override
    public void intercept() {
        try {
            this.getDataWithInterceptorChain((T) this);
        } catch (Exception e) {
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
            if (index < interceptors.size()) {
                Interceptor.Chain<T> chain = new DefaultInterceptor(index + 1, pair);
                Interceptor<T> intercept = interceptors.get(index);
                T interceptData = intercept.intercept(chain);

                if (interceptData == null) {
                    throw new NullPointerException("intercept " + intercept + " returned null");
                }

                return interceptData;
            }
            return pair;
        }
    }

    public T addInterceptor(Interceptor<T> interceptor) {
        this.interceptors.add(interceptor);
        return (T) this;
    }

    @Override
    public Type getType() {
        return type;
    }
}
