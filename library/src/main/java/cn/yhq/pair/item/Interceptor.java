package cn.yhq.pair.item;

/**
 * Created by Administrator on 2016/11/17.
 */

public interface Interceptor<T extends BasePair<T>> {

    T intercept(Interceptor.Chain<T> chain) throws Exception;

    interface Chain<T extends BasePair<T>> {
        T getPair();

        T handle(T pair) throws Exception;
    }
}

