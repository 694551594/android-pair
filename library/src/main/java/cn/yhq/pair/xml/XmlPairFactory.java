package cn.yhq.pair.xml;

import cn.yhq.pair.item.PairFactory;

/**
 * Created by Yanghuiqiang on 2016/11/18.
 */

public class XmlPairFactory extends PairFactory {
    private int xmlResId;

    public XmlPairFactory(int xmlResId) {
        this.xmlResId = xmlResId;
    }

    @Override
    protected void onCreate(PairFactory factory) {

    }
}
