package cn.yhq.pair.sample;

import android.os.Bundle;

import cn.yhq.base.BaseActivity;
import cn.yhq.pair.item.PairFactory;
import cn.yhq.pair.item.PairManager;
import cn.yhq.pair.ui.recyclerview.PairView;

public class RecyclerActivity extends BaseActivity {

    @Override
    protected void onConfig(Config config) {
        super.onConfig(config);
    }

    public static class User {
        public String username;
        public String password;
    }

    private User mUser;

    @Override
    protected void onViewCreated(Bundle savedInstanceState) {
        super.onViewCreated(savedInstanceState);

        mUser = new User();
        mUser.password = "123456";
        mUser.username = "694551594";

        PairView pairView = this.getView(R.id.pairview);

        new PairManager(this, new PairFactory() {
            @Override
            protected void onCreate(PairFactory factory) {
                this.newCatalog().setTitle("我是一组有图标的");
                this.newTextItem().setIcon(R.drawable.find_more_friend_scan).setKey("扫一扫");
                this.newTextItem().setIcon(R.drawable.find_more_friend_photograph_icon).setKey("朋友圈");
                this.newTextItem().setIcon(R.drawable.find_more_friend_shake).setKey("摇一摇");
            }
        }).attach(pairView);

    }

    @Override
    protected int getContentViewLayoutId() {
        return R.layout.activity_recycler;
    }
}
