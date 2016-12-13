package cn.yhq.pair.ui;


import android.os.Bundle;

import cn.yhq.base.BaseActivity;
import cn.yhq.pair.R;
import cn.yhq.pair.item.IPair;
import cn.yhq.pair.item.OnPairCreateListener;
import cn.yhq.pair.item.PairManager;
import cn.yhq.pair.xml.XmlPairFactory;

/**
 * Created by Yanghuiqiang on 2016/12/13.
 */

public class PairActivity extends BaseActivity {
    private PairView mPairView;
    private PairManager mPairManager;

    @Override
    protected int getContentViewLayoutId() {
        return R.layout.pair_layout;
    }

    @Override
    protected void onViewCreated(Bundle savedInstanceState) {
        super.onViewCreated(savedInstanceState);
        this.mPairView = this.getView(R.id.pairview);
    }

    public void setPairXmlResId(int resId) {
        this.mPairManager = PairManager.create(this, new XmlPairFactory(this, resId));
        this.mPairManager.attach(this.mPairView);
    }

    public void setOnPairCreateListener(OnPairCreateListener listener) {
        if (this.mPairManager != null) {
            this.mPairManager.setOnPairCreateListener(listener);
        }
    }

    public <T extends IPair> T getPairById(int id) {
        if (this.mPairManager != null) {
            return this.mPairManager.getPairById(id);
        }
        return null;
    }

    public PairManager getPairManager() {
        return mPairManager;
    }

    public PairView getPairView() {
        return mPairView;
    }
}
