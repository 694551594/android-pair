package cn.yhq.pair.sample;

import android.os.Bundle;

import cn.yhq.base.BaseActivity;
import cn.yhq.pair.item.PairManager;
import cn.yhq.pair.ui.recyclerview.PairView;
import cn.yhq.pair.xml.XmlPairFactory;

public class PairLayoutActivity extends BaseActivity {

    @Override
    protected void onConfig(Config config) {
        super.onConfig(config);
    }

    @Override
    protected void onViewCreated(Bundle savedInstanceState) {
        super.onViewCreated(savedInstanceState);

        PairView pairView = this.getView(R.id.pairview);

        PairManager.create(this, new XmlPairFactory(this, R.xml.sample))
                .attach(pairView);

    }

    @Override
    protected int getContentViewLayoutId() {
        return R.layout.activity_recycler;
    }
}
