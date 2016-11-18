package cn.yhq.pair.item;

/**
 * Created by Administrator on 2016/11/17.
 */

public interface Interceptor<T extends IPair> {

    T intercept(Interceptor.Chain<T> chain) throws Exception;

    interface Chain<T extends IPair> {
        T getPair();

        T handle(T pair) throws Exception;

    }
}

