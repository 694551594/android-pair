package cn.yhq.pair.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import cn.yhq.base.BaseFragment;
import cn.yhq.pair.R;
import cn.yhq.pair.item.IPair;
import cn.yhq.pair.item.OnPairCreateListener;
import cn.yhq.pair.item.PairManager;
import cn.yhq.pair.xml.XmlPairFactory;

/**
 * Created by yanghuijuan on 2017/1/28.
 */

public class PairFragment extends BaseFragment {

    private PairView mPairView;
    private PairManager mPairManager;

    @Override
    protected int getContentViewLayoutId() {
        return R.layout.pair_layout;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.mPairView = this.getView(view, R.id.pairview);
    }

    public void setPairXmlResId(int resId) {
        this.mPairManager = PairManager.create(this.getContext(), new XmlPairFactory(this.getContext(), resId));
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
