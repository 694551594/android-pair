package cn.yhq.pair.item;

/**
 * Created by Yanghuiqiang on 2016/11/16.
 */

public interface OnInvalidateListener<T extends IPair> {
    void onInvalidate(T pair);
}
