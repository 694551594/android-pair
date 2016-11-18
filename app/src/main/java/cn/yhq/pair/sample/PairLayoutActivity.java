package cn.yhq.pair.sample;

import android.content.Context;
import android.os.Bundle;

import cn.yhq.base.BaseActivity;
import cn.yhq.dialog.core.DialogBuilder;
import cn.yhq.dialog.core.IDialog;
import cn.yhq.pair.action.PairDialogAction;
import cn.yhq.pair.interceptor.DateFormatInterceptor;
import cn.yhq.pair.item.FieldPairItem;
import cn.yhq.pair.item.IPair;
import cn.yhq.pair.item.Interceptor;
import cn.yhq.pair.item.OnPairCreateListener;
import cn.yhq.pair.item.PairManager;
import cn.yhq.pair.item.TextPairItem;
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
                .setOnPairCreateListener(new OnPairCreateListener() {
                    @Override
                    public void onCreate(int index, IPair pair) {
                        switch (index) {
                            case 5:
                                ((TextPairItem) pair).addInterceptor(new Interceptor<TextPairItem>() {
                                    @Override
                                    public TextPairItem intercept(Chain<TextPairItem> chain) throws Exception {
                                        chain.getPair().setText("我是拦截器设置的文本");
                                        return chain.handle(chain.getPair());
                                    }
                                });
                                break;
                            case 6:
                                ((TextPairItem) pair).setAction(new PairDialogAction(getContext()) {

                                    @Override
                                    protected IDialog onCreateDialog(Context context) {
                                        return DialogBuilder
                                                .messageDialog(context)
                                                .setMessage("我是对话框哦")
                                                .create();
                                    }
                                });
                                break;
                            case 15:
                                ((TextPairItem) pair).setText(System.currentTimeMillis()).addInterceptor(new DateFormatInterceptor<TextPairItem>());
                                break;
                            case 18:
                            case 19:
                                RecyclerActivity.User mUser = new RecyclerActivity.User();
                                mUser.password = "123456";
                                mUser.username = "694551594";
                                ((FieldPairItem) pair).setEntity(mUser);
                                break;
                        }
                    }
                }).attach(pairView);

    }

    @Override
    protected int getContentViewLayoutId() {
        return R.layout.activity_recycler;
    }
}
