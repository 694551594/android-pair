package cn.yhq.pair.item;

/**
 * Created by Administrator on 2016/11/17.
 */

public interface IPair {

    Type getType();

    enum Type {
        CATALOG, TEXT, SWITCH, FIELD, IMAGE, CHECKBOX
    }
}
