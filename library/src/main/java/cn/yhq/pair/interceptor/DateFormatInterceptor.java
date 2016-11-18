package cn.yhq.pair.interceptor;

import android.text.TextUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import cn.yhq.pair.item.BaseTextPairItem;
import cn.yhq.pair.item.Interceptor;

/**
 * Created by Yanghuiqiang on 2016/11/17.
 */

public class DateFormatInterceptor<T extends BaseTextPairItem<T>> implements Interceptor<T> {
    private final static SimpleDateFormat mDateFormat = new SimpleDateFormat("yyyy年MM月dd日",
            Locale.getDefault());
    private SimpleDateFormat mSimpleDateFormat;

    public DateFormatInterceptor(SimpleDateFormat simpleDateFormat) {
        this.mSimpleDateFormat = simpleDateFormat;
    }

    public DateFormatInterceptor() {
        this(mDateFormat);
    }

    @Override
    public T intercept(Chain<T> chain) throws Exception {
        String text = chain.getPair().getText();
        if (TextUtils.isDigitsOnly(text)) {
            long time = Long.parseLong(text);
            chain.getPair().setText(mSimpleDateFormat.format(new Date(time)));
        }
        return chain.handle(chain.getPair());
    }

}
