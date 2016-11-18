package cn.yhq.pair.xml;

import android.content.Context;

import cn.yhq.pair.item.PairFactory;
import cn.yhq.pair.item.PairGroup;

/**
 * Created by Yanghuiqiang on 2016/11/18.
 */

public class XmlPairFactory extends PairFactory {
    private int xmlResId;
    private PairInflater inflater;

    public XmlPairFactory(Context context, int xmlResId) {
        super(context);
        this.inflater = new PairInflater(context);
        this.xmlResId = xmlResId;
    }

    @Override
    protected PairGroup onCreatePairGroup(Context context) {
        return (PairGroup) inflater.inflate(this.xmlResId);
    }

}
