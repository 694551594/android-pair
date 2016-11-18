package cn.yhq.pair.item;

/**
 * Created by Administrator on 2016/11/17.
 */

public interface IPair {

    Type getType();

    void intercept();

    void invalidate();

    enum Type {
        GROUP, CATALOG, TEXT, SWITCH, FIELD, IMAGE, CHECKBOX
    }
}
