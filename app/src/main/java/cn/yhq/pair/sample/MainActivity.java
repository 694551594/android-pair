package cn.yhq.pair.sample;

import android.content.Context;
import android.os.Bundle;

import cat.ereza.customactivityoncrash.CustomActivityOnCrash;
import cn.yhq.base.BaseActivity;
import cn.yhq.dialog.core.DialogBuilder;
import cn.yhq.dialog.core.IDialog;
import cn.yhq.pair.Pair;
import cn.yhq.pair.action.PairActivityAction;
import cn.yhq.pair.action.PairDialogAction;
import cn.yhq.pair.action.PairPreferenceAction;
import cn.yhq.pair.intercept.DateFormatIntercept;
import cn.yhq.pair.item.FieldPairItem;
import cn.yhq.pair.item.PairCatalog;
import cn.yhq.pair.item.PairIntercept;
import cn.yhq.pair.item.SwitchPairItem;
import cn.yhq.pair.item.TextPairItem;
import cn.yhq.pair.ui.PairView;

public class MainActivity extends BaseActivity {

    static {
        CustomActivityOnCrash.setShowErrorDetails(true);
    }

    @Override
    protected void onConfig(Config config) {
        super.onConfig(config);
        config.setSwipeBackWrapper(false);
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
        mUser.password = "密码";
        mUser.username = "用户名";

        PairView pairView = this.getView(R.id.pairview);

        new Pair.Builder(this)
                .addCatalog(
                        new PairCatalog()
                                .setTitle("我是组标题")
                                .addItems(
                                        new TextPairItem()
                                                .setIcon(R.drawable.find_more_friend_photograph_icon)
                                                .setText("哈哈")
                                                .setKey("朋友圈")
                                                .setDescription("我是描述")
                                                .setAction(new PairActivityAction(this, MainActivity.class))
                                                .addIntercept(new PairIntercept<TextPairItem>() {
                                                    @Override
                                                    public TextPairItem intercept(Chain<TextPairItem> chain) throws Exception {
                                                        chain.getItem().setText("我是拦截器设置的文本");
                                                        return chain.getItem();
                                                    }
                                                }),
                                        new TextPairItem()
                                                .setIcon(R.drawable.find_more_friend_scan)
                                                .setKey("扫一扫"),
                                        new TextPairItem()
                                                .setIcon(R.drawable.find_more_friend_photograph_icon)
                                                .setKey("朋友圈"),
                                        new TextPairItem()
                                                .setIcon(R.drawable.find_more_friend_shake)
                                                .setKey("摇一摇"),
                                        new TextPairItem()
                                                .setKey("没有图标")
                                                .setText("没有图标的文本哦"),
                                        new TextPairItem()
                                                .setKey("这是一个日期格式化的例子")
                                                .setText(System.currentTimeMillis())
                                                .addIntercept(new DateFormatIntercept<>()),
                                        new SwitchPairItem()
                                                .setKey("测试Preference")
                                                .setAction(new PairPreferenceAction(this, "test")),
                                        new TextPairItem()
                                                .setText("对话框")
                                                .setKey("对话框")
                                                .setDescription("点我可以打开对话框")
                                                .setAction(new PairDialogAction(this) {

                                                    @Override
                                                    protected IDialog onCreateDialog(Context context) {
                                                        return DialogBuilder.messageDialog(context).setMessage("我是对话框哦").create();
                                                    }
                                                }),
                                        new FieldPairItem()
                                                .setKey("用户名哦")
                                                .setEntity(mUser)
                                                .setExp("${user.username}"),
                                        new FieldPairItem()
                                                .setExp("${user.password}")
                                                .setEntity(mUser)
                                                .setKey("密码哦")
                                        )
                )
                .addCatalog(
                        new PairCatalog()
                                .setTitle("我也是组标题")
                                .addItems(
                                        new SwitchPairItem().setChecked(true).setKey("哈哈")
                                )
                ).build().setup(pairView);
    }

    @Override
    protected int getContentViewLayoutId() {
        return R.layout.activity_main;
    }
}
