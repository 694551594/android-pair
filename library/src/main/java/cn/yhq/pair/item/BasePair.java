package cn.yhq.pair.item;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/11/17.
 */

public abstract class BasePair<T extends BasePair<T>> implements IPair {
    private Type type;
    private List<Interceptor<T>> intercepts = new ArrayList<>();
    private OnInvalidateListener onInvalidateListener;

    public BasePair(Type type) {
        this.type = type;
    }

    protected void invalidate() {
        if (onInvalidateListener != null) {
            onInvalidateListener.onInvalidate(this);
        }
    }

    public void setOnInvalidateListener(OnInvalidateListener onInvalidateListener) {
        this.onInvalidateListener = onInvalidateListener;
    }

    public T intercept() {
        try {
            this.getDataWithInterceptorChain((T) this);
        } catch (Exception e) {
        }
        return (T) this;
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
            if (index < intercepts.size()) {
                Interceptor.Chain<T> chain = new DefaultInterceptor(index + 1, pair);
                Interceptor<T> intercept = intercepts.get(index);
                T interceptData = intercept.intercept(chain);

                if (interceptData == null) {
                    throw new NullPointerException("intercept " + intercept + " returned null");
                }

                return interceptData;
            }
            return pair;
        }
    }

    public T addIntercept(Interceptor<T> intercept) {
        this.intercepts.add(intercept);
        return (T) this;
    }

    @Override
    public Type getType() {
        return type;
    }
}
