package cn.yhq.pair.item;

import cn.yhq.pair.item.PairItem;

/**
 * Created by Administrator on 2016/11/16.
 */

public interface PairIntercept<T extends PairItem<T>> {

    T intercept(Chain<T> chain) throws Exception;

    interface Chain<T extends PairItem<T>> {
        T getItem();

        T intercept(T item) throws Exception;
    }
}
