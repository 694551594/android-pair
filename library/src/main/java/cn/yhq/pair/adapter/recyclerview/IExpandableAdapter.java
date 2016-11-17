package cn.yhq.pair.adapter.recyclerview;

/**
 * Created by Yanghuiqiang on 2016/11/17.
 */

public interface IExpandableAdapter<G, C> {

    G getGroup(int groupPosition);

    C getChild(int groupPosition, int childPosition);

    int getGroupCount();

    int getChildrenCount(int groupPosition);
}
