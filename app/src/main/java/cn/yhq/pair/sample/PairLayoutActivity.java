package cn.yhq.pair.sample;

import android.content.Context;
import android.os.Bundle;

import cn.yhq.dialog.core.DialogBuilder;
import cn.yhq.dialog.core.IDialog;
import cn.yhq.pair.action.PairDialogAction;
import cn.yhq.pair.interceptor.DateFormatInterceptor;
import cn.yhq.pair.item.IPair;
import cn.yhq.pair.item.Interceptor;
import cn.yhq.pair.item.OnPairCreateListener;
import cn.yhq.pair.item.TextPairItem;
import cn.yhq.pair.ui.PairActivity;

public class PairLayoutActivity extends PairActivity {

    @Override
    protected void onViewCreated(Bundle savedInstanceState) {
        super.onViewCreated(savedInstanceState);
        this.setPairXmlResId(R.xml.sample, new OnPairCreateListener() {
            @Override
            public void onCreate(int id, IPair pair) {
                switch (id) {
                    case R.id.pair_interceptor:
                        this.getTextPairItem(pair).addInterceptor(new Interceptor<TextPairItem>() {
                            @Override
                            public TextPairItem intercept(Chain<TextPairItem> chain) throws Exception {
                                chain.getPair().setText("我是拦截器设置的文本");
                                return chain.handle(chain.getPair());
                            }
                        });
                        break;
                    case R.id.pair_dialog:
                        this.getTextPairItem(pair).setAction(new PairDialogAction(getContext()) {

                            @Override
                            protected IDialog onCreateDialog(Context context) {
                                return DialogBuilder
                                        .messageDialog(context)
                                        .setMessage("我是对话框哦")
                                        .create();
                            }
                        });
                        break;
                    case R.id.pair_date_format:
                        this.getTextPairItem(pair).setText(System.currentTimeMillis()).addInterceptor(new DateFormatInterceptor<TextPairItem>());
                        break;
                    case R.id.pair_field_username:
                    case R.id.pair_field_password:
                        RecyclerActivity.User mUser = new RecyclerActivity.User();
                        mUser.password = "123456";
                        mUser.username = "694551594";
                        this.getFieldPairItem(pair).setEntity(mUser);
                        break;
                }
            }
        });
        TextPairItem item = this.getPairById(R.id.pair_dialog);
        item.setText("我改变了哦");
    }

}
