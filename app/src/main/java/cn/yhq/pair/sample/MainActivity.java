package cn.yhq.pair.sample;

import android.content.Context;
import android.os.Bundle;

import cat.ereza.customactivityoncrash.CustomActivityOnCrash;
import cn.yhq.base.BaseActivity;
import cn.yhq.dialog.core.DialogBuilder;
import cn.yhq.dialog.core.IDialog;
import cn.yhq.pair.Pair;
import cn.yhq.pair.PairIntercept;
import cn.yhq.pair.action.PairActivityAction;
import cn.yhq.pair.action.PairDialogAction;
import cn.yhq.pair.action.PairPreferenceAction;
import cn.yhq.pair.item.PairCatalog;
import cn.yhq.pair.item.SwitchPairItem;
import cn.yhq.pair.item.TextPairItem;
import cn.yhq.pair.ui.PairView;

public class MainActivity extends BaseActivity {

    static {
        CustomActivityOnCrash.setShowErrorDetails(true);
    }

    @Override
    protected void onViewCreated(Bundle savedInstanceState) {
        super.onViewCreated(savedInstanceState);
        PairView pairView = this.getView(R.id.pairview);

        Pair pair = new Pair.Builder(this)
                .addCatalog(
                        new PairCatalog()
                                .setTitle("我是组标题")
                                .addItems(
                                        new TextPairItem()
                                                .setIcon(R.drawable.ic_launcher)
                                                .setText("哈哈")
                                                .setKey("我是")
                                                .setDescription("我是描述")
                                                .setAction(new PairActivityAction(this, MainActivity.class))
                                                .addIntercept(new PairIntercept<TextPairItem>() {
                                                    @Override
                                                    public TextPairItem intercept(Chain<TextPairItem> chain) throws Exception {
                                                        chain.getItem().setText("我是拦截器设置的文本");
                                                        return chain.getItem();
                                                    }
                                                }),
                                        new SwitchPairItem()
                                                .setKey("测试Preference")
                                                .setAction(new PairPreferenceAction(this, "test")),
                                        new TextPairItem()
                                                .setText("哈哈")
                                                .setKey("你好")
                                                .setDescription("我是描述")
                                                .setAction(new PairDialogAction() {

                                                    @Override
                                                    protected IDialog onCreateDialog(Context context) {
                                                        return DialogBuilder.messageDialog(context).setMessage("我是对话框哦").create();
                                                    }
                                                })
                                )
                )
                .addCatalog(
                        new PairCatalog()
                                .setTitle("我也是组标题")
                                .addItems(
                                        new SwitchPairItem().setChecked(true).setKey("key2")
                                )
                ).build();
        pair.setup(pairView);
    }

    @Override
    protected int getContentViewLayoutId() {
        return R.layout.activity_main;
    }
}
