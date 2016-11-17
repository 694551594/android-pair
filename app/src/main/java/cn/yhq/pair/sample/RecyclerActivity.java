package cn.yhq.pair.sample;

import android.content.Context;
import android.os.Bundle;

import cn.yhq.base.BaseActivity;
import cn.yhq.dialog.core.DialogBuilder;
import cn.yhq.dialog.core.IDialog;
import cn.yhq.pair.Pair;
import cn.yhq.pair.action.PairActivityAction;
import cn.yhq.pair.action.PairDialogAction;
import cn.yhq.pair.action.PairPreferenceAction;
import cn.yhq.pair.intercept.DateFormatIntercept;
import cn.yhq.pair.item.CheckPairItem;
import cn.yhq.pair.item.FieldPairItem;
import cn.yhq.pair.item.ImagePairItem;
import cn.yhq.pair.item.PairCatalog;
import cn.yhq.pair.item.PairIntercept;
import cn.yhq.pair.item.SwitchPairItem;
import cn.yhq.pair.item.TextPairItem;
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

        new Pair.Builder(this)
                .addCatalog(
                        new PairCatalog()
                                .setTitle("我是一组有图标的")
                                .addItems(
                                        new TextPairItem()
                                                .setIcon(R.drawable.find_more_friend_scan)
                                                .setKey("扫一扫"),
                                        new TextPairItem()
                                                .setIcon(R.drawable.find_more_friend_photograph_icon)
                                                .setKey("朋友圈"),
                                        new TextPairItem()
                                                .setIcon(R.drawable.find_more_friend_shake)
                                                .setKey("摇一摇")
                                )
                ).addCatalog(
                    new PairCatalog()
                            .setTitle("拦截器测试")
                            .addItems(
                                    new TextPairItem()
                                            .setIcon(R.drawable.find_more_friend_photograph_icon)
                                            .setText("朋友圈")
                                            .setKey("拦截器设置的文本")
                                            .setDescription("之前的文本为：朋友圈")
                                            .addIntercept(new PairIntercept<TextPairItem>() {
                                                @Override
                                                public TextPairItem intercept(Chain<TextPairItem> chain) throws Exception {
                                                    chain.getItem().setText("我是拦截器设置的文本");
                                                    return chain.getItem();
                                                }
                                            })
                            )
                ).addCatalog(
                    new PairCatalog()
                            .setTitle("action测试")
                            .addItems(
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
                                    new TextPairItem()
                                            .setText("进入新的activity")
                                            .setKey("activity")
                                            .setAction(new PairActivityAction(this, RecyclerActivity.class))
                                )
                ).addCatalog(
                        new PairCatalog()
                                .setTitle("Preference测试")
                                .addItems(
                                        new SwitchPairItem()
                                                .setKey("测试Preference")
                                                .setAction(new PairPreferenceAction(this, "test")),
                                        new SwitchPairItem().setChecked(true).setKey("开关"),
                                        new CheckPairItem().setChecked(false).setKey("选择框")
                                )
                ).addCatalog(
                        new PairCatalog()
                                .setTitle("没图标的")
                                .addItems(
                                        new TextPairItem()
                                                .setKey("没有图标")
                                                .setText("没有图标的文本哦"),
                                        new TextPairItem()
                                                .setKey("这是一个日期格式化的例子")
                                                .setText(System.currentTimeMillis())
                                                .addIntercept(new DateFormatIntercept<>()),
                                        new ImagePairItem().setKey("后面是一张图片")
                                                .setResId(R.drawable.ic_discovery_templet_shop)
                                )
                ).addCatalog(
                        new PairCatalog()
                                .setTitle("这是一个数据绑定的例子")
                                .addItems(
                                        new FieldPairItem()
                                                .setKey("用户名")
                                                .setEntity(mUser)
                                                .setExp("${user.username}"),
                                        new FieldPairItem()
                                                .setExp("${user.password}")
                                                .setEntity(mUser)
                                                .setKey("密码")
                                )
                )
                .build().setup(pairView);
    }

    @Override
    protected int getContentViewLayoutId() {
        return R.layout.activity_recycler;
    }
}
